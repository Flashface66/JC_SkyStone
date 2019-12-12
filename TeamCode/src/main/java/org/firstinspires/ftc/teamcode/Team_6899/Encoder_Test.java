

package org.firstinspires.ftc.teamcode.Team_6899;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the HW.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Encoder", group="Pushbot")
public class Encoder_Test extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware_6899 HW = new Hardware_6899(); // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();



    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            CIRCUMFRENCE_INCHES;
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException{



        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        HW.init(hardwareMap);


        // Send telemetry message to signify HW waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        HW.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HW.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HW.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HW.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                HW.FL.getCurrentPosition(),
                HW.FR.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,DRIVE_SPEED,  6,  6, 3);  // S1: Forward 47 Inches with 5 Sec timeout
       // encoderDrive(TURN_SPEED,TURN_SPEED,   1, 1, 3);  // S2: Turn Right 12 Inches with 4 Sec timeout
       // encoderDrive(DRIVE_SPEED,DRIVE_SPEED, 6, 6, 3);  // S3: Reverse 24 Inches with 4 Sec timeout

       /* HW.leftClaw.setPosition(1.0);            // S4: Stop and close the claw.
        HW.rightClaw.setPosition(0.0);
        sleep(1000);     // pause for servos to move

        */

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
    public void encoderDrive(double Lspeed, double Rspeed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Turn On RUN_TO_POSITION


            // Determine new target position, and pass to motor controller
            newLeftTarget = (int)((leftInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_MOTOR_REV);
            newRightTarget =  (int)((rightInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_MOTOR_REV);
            HW.FL.setTargetPosition(newLeftTarget);
            HW.BL.setTargetPosition(newLeftTarget);
            HW.FR.setTargetPosition(newRightTarget);
            HW.BR.setTargetPosition(newRightTarget);

            HW.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HW.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            HW.FL.setPower((Lspeed));
            HW.BL.setPower((Lspeed));
            HW.FR.setPower((Rspeed));
            HW.BR.setPower((Rspeed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the HW will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the HW continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (HW.FL.isBusy() || HW.FR.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        HW.FL.getCurrentPosition(),
                        HW.FR.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            HW.FL.setPower(0);
            HW.BL.setPower(0);
            HW.FR.setPower(0);
            HW.BR.setPower(0);

            // Turn off RUN_TO_POSITION
            HW.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            HW.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            HW.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            HW.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }



}
