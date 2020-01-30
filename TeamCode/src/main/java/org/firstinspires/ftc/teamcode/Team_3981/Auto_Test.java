package org.firstinspires.ftc.teamcode.Team_3981;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;





@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")
@Disabled
public class Auto_Test extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware_Test RB = new Hardware_Test();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();



    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    private static final double     COUNTS_PER_MOTOR_HEX    = 288;
    private static final double     DRIVE_GEAR_REDUCTION    = 0.67;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) ;
    private static final double     DRIVE_SPEED             = 0.3;
    private static final double     TURN_SPEED              = 0.4;

    @Override
    public void runOpMode() {


        RB.init(hardwareMap);


        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          RB.Left.getCurrentPosition(),
                          RB.Right.getCurrentPosition());
        telemetry.update();


        waitForStart();


        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  68,  68);  // S1: Forward 47 Inches with 5 Sec timeout


        encoderDrive(DRIVE_SPEED,  -14,  14);
        encoderDrive(DRIVE_SPEED,  85,  85);
        encoderDrive(DRIVE_SPEED,  -14,  14);
        encoderDrive(DRIVE_SPEED,  50,  50);
        encoderDrive(DRIVE_SPEED,  14,  -14);
        encoderDrive(DRIVE_SPEED,  -40,  -40);
        // encoderDrive(TURN_SPEED,   1, 1);  // S2: Turn Right 12 Inches with 4 Sec timeout
        //encoderDrive(DRIVE_SPEED, 6, 6);  // S3: Reverse 24 Inches with 4 Sec timeout



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
        int newLeftTarget= (int)(leftRotationsNeeded*COUNTS_PER_INCH);
        int newRightTarget= (int)(rightRotationsNeeded*COUNTS_PER_INCH);



            // Determine new target position, and pass to motor controller
            //newLeftTarget =  (int)((leftInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_INCH);
            //newRightTarget =  (int)((rightInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_INCH);

            RB.Left.setTargetPosition(newLeftTarget);
            RB.Right.setTargetPosition(newRightTarget);

            // reset the timeout time and start motion.

            RB.Left.setPower(-speed);
            RB.Right.setPower(speed);

            // Turn On RUN_TO_POSITION
            RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);





            while ((RB.Left.isBusy() || RB.Right.isBusy())) {

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

              //sleep(100);   // optional pause after each move

    }

    public void lift(double speed,
                     double Inches){
        //double rotationsNeeded =Inches;
        int target = (int)(Inches*COUNTS_PER_MOTOR_HEX);
        RB.Claw1.setTargetPosition(target);
        RB.Claw2.setTargetPosition(target);
        RB.Claw1.setPower(-speed);
        RB.Claw2.setPower(speed);
        RB.Claw1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (
                (RB.Claw1.isBusy() || RB.Claw2.isBusy())) {

            // Display it for the driver.
            telemetry.addData("End",  "Running to %7d :%7d, %7d",  RB.Claw2.getCurrentPosition(),
                    RB.Claw1.getCurrentPosition(), target);

            telemetry.update();
        }

        RB.Claw1.setPower(0);
        RB.Claw2.setPower(0);

    }



}
