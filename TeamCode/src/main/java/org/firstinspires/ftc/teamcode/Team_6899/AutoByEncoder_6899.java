package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Team2 AutoByEncoder")

public class AutoByEncoder_6899 extends LinearOpMode {

    private Hardware_6899 HWA                    = new Hardware_6899();   // Uses my hardware
    private ElapsedTime     runtime                = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV   = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION   = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES  = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH        = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/(WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED            = 0.6;
    private static final double     TURN_SPEED             = 0.5;
    private static final double     LIFT_SPEED             = 0.5;

    @Override
    public void runOpMode() {

        HWA.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();


        HWA.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        HWA.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  30,  30, 3.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   10, -10, 3.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderLift (LIFT_SPEED,   6, 4.0);


        HWA.servo1.setPosition(1.0);
        HWA.servo2.setPosition(0.0);


        sleep(1000);     // pause for servos to move

        telemetry.addData("Path0", "Complete");
        telemetry.update();
    }

    private void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newLeftTarget2;
        int newRightTarget2;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget2  = HWA.FL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget   = HWA.BL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget2 = HWA.FR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget  = HWA.BR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            HWA.FL.setTargetPosition(newLeftTarget2);
            HWA.BL.setTargetPosition(newLeftTarget);
            HWA.FR.setTargetPosition(newRightTarget2);
            HWA.BR.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            HWA.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            HWA.FL.setPower(Math.abs(speed));
            HWA.BL.setPower(Math.abs(speed));
            HWA.FR.setPower(Math.abs(speed));
            HWA.BR.setPower(Math.abs(speed));


            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (HWA.FL.isBusy() && HWA.BL.isBusy() && HWA.FR.isBusy() && HWA.BR.isBusy())) {
                telemetry.update();
            }

            // Stop all motion;
            HWA.FR.setPower(0);
            HWA.BR.setPower(0);
            HWA.FL.setPower(0);
            HWA.BL.setPower(0);
            //.

            // Turn off RUN_TO_POSITION
            HWA.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //.

             sleep(250);   // optional pause after each move
        }
    }

    private void encoderLift(double LIFT_SPEED, double upInches, double timeoutS){
        int LiftTarget;

        if (opModeIsActive()){
            LiftTarget = HWA.Lift.getCurrentPosition() + (int)(upInches * COUNTS_PER_INCH);

            HWA.Lift.setTargetPosition(LiftTarget);

            HWA.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            HWA.Lift.setPower(Math.abs(LIFT_SPEED));

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (HWA.Lift.isBusy())) {
                telemetry.update();
            }

            HWA.Lift.setPower(0);

            HWA.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
        }
    }
}
