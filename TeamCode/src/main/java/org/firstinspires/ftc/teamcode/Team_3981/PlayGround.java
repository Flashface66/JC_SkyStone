package org.firstinspires.ftc.teamcode.Team_3981;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous
public class PlayGround extends LinearOpMode {


    private Hardware_Test_V2 RB = new Hardware_Test_V2();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();


    private static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: REV Motor Encoder
    private static final double COUNTS_PER_MOTOR_TETRIX = 1440;    // eg: TETRIX Motor Encoder
    private static final double COUNTS_PER_MOTOR_HEX = 288;
    private static final double DRIVE_GEAR_REDUCTION = 0.3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    private static final double CIRCUMFRENCE_INCHES = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION);
    private static final double COUNTS_PER_INCH_TETRIX = (COUNTS_PER_MOTOR_TETRIX * DRIVE_GEAR_REDUCTION);
    private static final double DRIVE_SPEED = 0.3;
    private static final double TURN_SPEED = 0.4;

    @Override
    public void runOpMode() {


        RB.init(hardwareMap);


        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Claw2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Encoder Values", "Starting at %7d :%7d : %7d :%7d",
                RB.Left.getCurrentPosition(),
                RB.Right.getCurrentPosition(),
                RB.LeftB.getCurrentPosition(),
                RB.RightB.getCurrentPosition());
        telemetry.update();


        waitForStart();

        encoderDrive(DRIVE_SPEED,47,47,2);

    }


        public void encoderDrive(double speed, double leftInches, double rightInches, double rampup)  {


        //initialise some variables for the subroutine
        double rightRotationsNeeded = rightInches / CIRCUMFRENCE_INCHES;
        double leftRotationsNeeded = leftInches / CIRCUMFRENCE_INCHES;
        // Ensure that the opmode is still active
        // Determine new target position, and pass to motor controller we only do this in case the encoders are not totally zero'd
        int newLeftTarget = RB.Left.getCurrentPosition() + (int) (leftRotationsNeeded * COUNTS_PER_INCH);
        int newRightTarget = RB.Right.getCurrentPosition() + (int) (rightRotationsNeeded * COUNTS_PER_INCH);
        int newLeftTargetTETRIX = RB.LeftB.getCurrentPosition() + (int) (leftRotationsNeeded * COUNTS_PER_INCH_TETRIX);
        int newRightTargetTETRIX = RB.RightB.getCurrentPosition() + (int) (rightRotationsNeeded * COUNTS_PER_INCH_TETRIX);


        // reset the timeout time and start motion.
        runtime.reset();
        // keep looping while we are still active, and there is time left, and neither set of motors have reached the target
        while (
                (RB.Left.isBusy() && RB.Right.isBusy() && RB.LeftB.isBusy() && RB.RightB.isBusy())) {
            int rem = (Math.abs(RB.Left.getCurrentPosition()) + Math.abs( RB.Right.getCurrentPosition()) + Math.abs(RB.LeftB.getCurrentPosition()) + Math.abs( RB.RightB.getCurrentPosition())) / 4;
            double Nspeed;
            //To Avoid spinning the wheels, this will "Slowly" ramp the motors up over
            //the amount of time you set for this SubRun
            double R = runtime.seconds();
            if (R < rampup) {
                double ramp = R / rampup;
                Nspeed = speed * ramp;

            }
//Keep running until you are about two rotations out
            else if (rem > (1000)) {
                Nspeed = speed;

            }
            //start slowing down as you get close to the target
            else if (rem > (200) && (speed * .2) > .1 ) {
                Nspeed =(int)(speed * (rem / 1000));

            }
            //minimum speed
            else {
                Nspeed = speed * .2;


            }
            //Pass the seed values to the motors
            RB.Left.setPower(-Nspeed);
            RB.Right.setPower(Nspeed);
            RB.LeftB.setPower(Nspeed);
            RB.RightB.setPower(-Nspeed);
        }
        // Stop all motion;
        //Note: This is outside our while statement, this will only activate once the time, or distance has been met
        RB.Left.setPower(0);
        RB.Right.setPower(0);
        RB.LeftB.setPower(0);
        RB.RightB.setPower(0);
        // show the driver how close they got to the last target
        telemetry.addData("AndyMsrk Target", "Running to %7d :%7d", newLeftTarget, newRightTarget);
        telemetry.addData("TETRIX Target", "Running to %7d :%7d", newLeftTargetTETRIX, newRightTargetTETRIX);
        telemetry.addData("Current Values", "Running at %7d :%7d :  %7d :%7d",
                RB.Left.getCurrentPosition(),
                RB.Right.getCurrentPosition(),
                RB.LeftB.getCurrentPosition(),
                RB.RightB.getCurrentPosition());
        telemetry.update();
        //setting resetC as a way to check the current encoder values easily
        double resetC = (Math.abs(RB.Left.getCurrentPosition()) + Math.abs( RB.Right.getCurrentPosition()) + Math.abs(RB.LeftB.getCurrentPosition()) + Math.abs( RB.RightB.getCurrentPosition())) ;
        //Get the motor encoder resets in motion

        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //keep waiting while the reset is running
        while (Math.abs(resetC) > 0) {
            resetC = (Math.abs(RB.Left.getCurrentPosition()) + Math.abs( RB.Right.getCurrentPosition()) + Math.abs(RB.LeftB.getCurrentPosition()) + Math.abs( RB.RightB.getCurrentPosition())) ;
            idle();
        }
        // switch the motors back to RUN_USING_ENCODER mode
        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//give the encoders a chance to switch modes.
        //  sleep(250);   // optional pause after each move
    }


}

