package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name = "Pushbot: Red Plate", group = "Auto")
public class RD_Plate extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware_Test_V2 RB = new Hardware_Test_V2();   // Use a Pushbot's hardware



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


        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        lift(1, 1200);
        encoderDrive(0.2, 47, 47);
        encoderDrive(DRIVE_SPEED, -12, 12);
        encoderDrive(DRIVE_SPEED, 6, 6);
        lift(0.5, -1200);
        encoderDrive(DRIVE_SPEED, -7, -7);
        encoderDrive(0.5, -55, 55);
        encoderDrive(1, 54, 54);
        lift(1, 600);
        encoderDrive(DRIVE_SPEED, -5, -5);
        encoderDrive(DRIVE_SPEED, 35, -35);
        encoderDrive(DRIVE_SPEED, -39, -39);


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
        double rightRotationsNeeded = rightInches / CIRCUMFRENCE_INCHES;
        double leftRotationsNeeded = leftInches / CIRCUMFRENCE_INCHES;

        int newLeftTarget = RB.Left.getCurrentPosition() + (int) (leftRotationsNeeded * COUNTS_PER_INCH);
        int newRightTarget = RB.Right.getCurrentPosition() + (int) (rightRotationsNeeded * COUNTS_PER_INCH);
        int newLeftTargetTETRIX = RB.LeftB.getCurrentPosition() + (int) (leftRotationsNeeded * COUNTS_PER_INCH_TETRIX);
        int newRightTargetTETRIX = RB.RightB.getCurrentPosition() + (int) (rightRotationsNeeded * COUNTS_PER_INCH_TETRIX);


        // Determine new target position, and pass to motor controller


        RB.Left.setTargetPosition(newLeftTargetTETRIX);
        RB.Right.setTargetPosition(newRightTargetTETRIX);
        RB.LeftB.setTargetPosition(newLeftTargetTETRIX);
        RB.RightB.setTargetPosition(newRightTargetTETRIX);


        RB.Left.setPower(speed);
        RB.Right.setPower(-speed);
        RB.LeftB.setPower(speed);
        RB.RightB.setPower(-speed);

        // Turn On RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while ((RB.Left.isBusy() && RB.Right.isBusy() && RB.LeftB.isBusy() && RB.RightB.isBusy())) {

            // Display it for the driver.
           // telemetry.addData("AndyMsrk Target", "Running to %7d :%7d", newLeftTarget, newRightTarget);
            telemetry.addData("TETRIX Target", "Running to %7d :%7d", newLeftTargetTETRIX, newRightTargetTETRIX);
            telemetry.addData("Current Values", "Running at %7d :%7d :  %7d :%7d",
                    RB.Left.getCurrentPosition(),
                    RB.Right.getCurrentPosition(),
                    RB.LeftB.getCurrentPosition(),
                    RB.RightB.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        RB.Left.setPower(0);
        RB.Right.setPower(0);
        RB.LeftB.setPower(0);
        RB.RightB.setPower(0);

        // Turn off RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(200);   // optional pause after each move

    }

    public void lift(double speed,
                     double Ticks) {
        int target = (int) (Ticks);
        RB.Claw2.setTargetPosition(target);
        RB.Claw2.setPower(speed);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (
                (RB.Claw2.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Ticks", "Running to %7d :%7d", RB.Claw2.getCurrentPosition(), target);

            telemetry.update();
        }


        RB.Claw2.setPower(0);

        RB.Claw2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


}