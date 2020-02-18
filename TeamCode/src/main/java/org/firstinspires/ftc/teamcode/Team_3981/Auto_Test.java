package org.firstinspires.ftc.teamcode.Team_3981;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")

public class Auto_Test extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware_Test_V2 RB = new Hardware_Test_V2();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
  //  private DcMotor Claw2 =null;



    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: REV Motor Encoder
    private static final double     COUNTS_PER_MOTOR_TETRIX = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 0.3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333;    // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) ;
    private static final double     DRIVE_SPEED             = 0.2;
    private static final double     TURN_SPEED              = 0.4;

    @Override
    public void runOpMode() {

        RB.init(hardwareMap);



        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Claw2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RB.RightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RB.LeftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          RB.Left.getCurrentPosition(),
                          RB.Right.getCurrentPosition());
        telemetry.update();


        waitForStart();


        // Note: Reverse movement is obtained by setting a negative distance (not speed)

       lift(0.5,400);
        encoderDrive(DRIVE_SPEED,43,43);
        encoderDrive(DRIVE_SPEED,  14,  -14);
        encoderDrive(DRIVE_SPEED,  5,  5);
       lift(0.5,-400);
        encoderDrive(DRIVE_SPEED,-5,-5);
        encoderDrive(DRIVE_SPEED,  14,  -14);
        encoderDrive(DRIVE_SPEED,10,10);
       lift(0.5,300);







        // send the info back to driver station using telemetry function.

    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches
                             ) {
        double rightRotationsNeeded =rightInches/ CIRCUMFRENCE_INCHES;
        double leftRotationsNeeded =leftInches/ CIRCUMFRENCE_INCHES;
        int newLeftTarget= RB.Left.getCurrentPosition () + (int)(leftRotationsNeeded*COUNTS_PER_INCH);
        int newRightTarget=RB.Right.getCurrentPosition () + (int)(rightRotationsNeeded*COUNTS_PER_INCH);



            // Determine new target position, and pass to motor controller


            RB.Left.setTargetPosition(newLeftTarget);
            RB.Right.setTargetPosition(newRightTarget);

            // reset the timeout time and start motion.

            RB.Left.setPower(-speed);
            RB.Right.setPower(speed);

            // Turn On RUN_TO_POSITION
            RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);





            while ((RB.Left.isBusy() && RB.Right.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            RB.Left.getCurrentPosition(),
                                            RB.Right.getCurrentPosition());
                telemetry.update();


            }

            // Stop all motion;
            RB.Left.setPower(0);
            RB.Right.setPower(0);

            // Turn off RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       // sleep(200);   // optional pause after each move

    }

    public void lift(double speed,
                     double Ticks){
        //double rotationsNeeded =Inches;
        int target = (int)(Ticks);
        RB.Claw2.setTargetPosition(target);
        RB.Claw2.setPower(speed);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (
                (RB.Claw2.isBusy() )) {

            // Display it for the driver.
            telemetry.addData("End",  "Running to %7d :%7d",  RB.Claw2.getCurrentPosition(), target);

            telemetry.update();
        }


        RB.Claw2.setPower(0);

        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }





public void Clamp(long time,double pos){
    RB.Rotate2.setPosition(pos);
    sleep(time);
    }

}
