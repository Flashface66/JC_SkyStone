package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous
@Disabled
public class Another extends LinearOpMode {

    private static final double CPMR = 1440;    // TETRIX Motor Encoder






    private Hardware_Test RB = new Hardware_Test();



    @Override
    public void runOpMode() {


        RB.init(hardwareMap);


        waitForStart();

        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        double Circumfrence = (3.14 * 2.938);

        double rotationsneeded = 12 / Circumfrence;


        int drivingTarget = (int) (rotationsneeded*1440);

        RB.Left.setTargetPosition(drivingTarget);
        RB.Right.setTargetPosition(-drivingTarget);


        RB.Left.setPower(0.5);
        RB.Right.setPower(0.5);

        RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (RB.Left.isBusy() || RB.Right.isBusy()){

        }

        RB.Left.setPower(0);
        RB.Right.setPower(0);

        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }





    }

