package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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


        encoderDrive(DRIVE_SPEED,DRIVE_SPEED,  6,  6, 3);  // S1: Forward 47 Inches with 5 Sec timeout


    }

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
