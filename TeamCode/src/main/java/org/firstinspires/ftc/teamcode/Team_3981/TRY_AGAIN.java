package org.firstinspires.ftc.teamcode.Team_3981;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Team_3981.Auto_Test;


@Autonomous
public class TRY_AGAIN extends LinearOpMode {





    private Hardware_Test RB = new Hardware_Test();

    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 0.67;     // This is < 1.0 if geared UP
    private static final double     COUNTS_PER_MOTOR_HEX    = 288;
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) ;




    @Override
    public void runOpMode() throws InterruptedException {


        RB.init(hardwareMap);

        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                RB.Left.getCurrentPosition(),
                RB.Right.getCurrentPosition());
        telemetry.update();



        waitForStart();


        double rotationsNeeded =12/ CIRCUMFRENCE_INCHES;
        int target = (int)(rotationsNeeded*COUNTS_PER_INCH);
        RB.Left.setTargetPosition(target);
        RB.Right.setTargetPosition(target);
        RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // reset the timeout time and start motion.


        RB.Left.setPower(-0.2);
        RB.Right.setPower(0.2);

        // Turn On RUN_TO_POSITION


        while (
                (RB.Left.isBusy() || RB.Right.isBusy())) {

            // Display it for the driver.
            telemetry.addData("End",  "Running to %7d :%7d, %7d",  RB.Left.getCurrentPosition(),
                    RB.Right.getCurrentPosition(), target);

            telemetry.update();
        }

        RB.Left.setPower(0);
        RB.Right.setPower(0);


        int lifttarget = (int)(1*COUNTS_PER_MOTOR_HEX);
        RB.Claw1.setTargetPosition(lifttarget);
        RB.Claw2.setTargetPosition(lifttarget);
        RB.Claw1.setPower(-.8);
        RB.Claw2.setPower(.8);
        RB.Claw1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (
                (RB.Claw1.isBusy() || RB.Claw2.isBusy())) {

            // Display it for the driver.
            telemetry.addData("End",  "Running to %7d :%7d, %7d",  RB.Claw2.getCurrentPosition(),
                    RB.Claw1.getCurrentPosition(), lifttarget);

            telemetry.update();
        }

        RB.Claw1.setPower(0);
        RB.Claw2.setPower(0);





    }

        




    



}

