package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RedTray6899 - Auto", group = "TraySide")
public class RedTray6899 extends LinearOpMode {

    private Hardware_6899 HWA       = new Hardware_6899();   // Uses my hardware
    private ElapsedTime   runtime   = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV   = 1120;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION   = 1.0 ;    // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES  = 4.0 ;    // For figuring circumference

    private static final double     COUNTS_PER_INCH        = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/(WHEEL_DIAMETER_INCHES * 3.1415);

    private static final double     DRIVE_SPEED            = 0.8;
    private static final double     TURN_SPEED             = 0.7;
    private static final double     LIFT_SPEED             = 0.6;

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
        HWA.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.LiftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        HWA.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.LiftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.LiftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        encoderDrive(DRIVE_SPEED,  47, 47);
        encoderDrive(TURN_SPEED,   -12, 12);
        encoderLift(LIFT_SPEED, 12);
        encoderDrive(DRIVE_SPEED,  6, 6);
        encoderLift(LIFT_SPEED, -10);
        encoderDrive(DRIVE_SPEED, -8, -8);
        encoderDrive(TURN_SPEED,   -12, 12);
        encoderDrive(DRIVE_SPEED,  35, 35);
        encoderLift(LIFT_SPEED, 12);
        encoderDrive(DRIVE_SPEED, -12, -12);
        encoderDrive(TURN_SPEED,   15, -15);
        encoderDrive(DRIVE_SPEED,  35, 35);

        telemetry.addData("Path0", "Complete");
        telemetry.update();

        sleep(3000);     // pause for servos to move
    }

    private void encoderDrive(double speed, double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newLeftTarget   = HWA.FL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget  = HWA.FR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            HWA.FL.setTargetPosition(newLeftTarget);
            HWA.BL.setTargetPosition(newLeftTarget);
            HWA.FR.setTargetPosition(newRightTarget);
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


            while (opModeIsActive() && (runtime.seconds() < 3.0) && (HWA.FL.isBusy() && HWA.BL.isBusy() && HWA.FR.isBusy() && HWA.BR.isBusy())) {
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

            sleep(200);   // optional pause after each move
        }
    }

    private void encoderLift(double LIFT_SPEED, double upInches){
        int LiftTargetL;
        int LiftTargetR;

        if (opModeIsActive()){
            LiftTargetL = HWA.LiftR.getCurrentPosition() + (int)(upInches * COUNTS_PER_INCH);
            LiftTargetR = HWA.LiftR.getCurrentPosition() + (int)(upInches * COUNTS_PER_INCH);

            HWA.LiftL.setTargetPosition(LiftTargetL);
            HWA.LiftR.setTargetPosition(LiftTargetR);

            HWA.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.LiftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            HWA.LiftL.setPower(Math.abs(LIFT_SPEED));
            HWA.LiftR.setPower(Math.abs(LIFT_SPEED));

            while (opModeIsActive() && (runtime.seconds() < 1.0) && (HWA.LiftL.isBusy() || HWA.LiftR.isBusy())) {
                telemetry.update();
            }

            HWA.LiftL.setPower(0);
            HWA.LiftR.setPower(0);

            HWA.LiftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.LiftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(1000);
        }
    }
}
