package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Team_6899", group="Teleop")

public class Team_6899 extends LinearOpMode {

    private Hardware_6899 HW = new Hardware_6899();
    private DcMotor SubLift =null;
    private int max = 750;
    private int min = 0;

    @Override
    public void runOpMode() {
        SubLift = hardwareMap.get(DcMotor.class, "SubLift");

        SubLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SubLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addLine("           Robot: Team 6899");
        telemetry.addLine("************Status: Running***********");




        SubLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        SubLift.setDirection(DcMotor.Direction.REVERSE);

        HW.init(hardwareMap); //inititalizing Hardware before start

        waitForStart();

        while (opModeIsActive()) {
            WheelControl();

            Collection();

            LiftSystem();

            SecondaryLift();
        }
    }

    //Function To Control Robot Movement
    private void WheelControl() {
        double Power1, Power2;
        Power1 = Range.clip(gamepad1.left_stick_y, -0.8, 0.8);
        Power2 = Range.clip(gamepad1.right_stick_y,-0.8, 0.8);


        //Power for Left   HW.SubLifts
        HW.FL.setPower(-Power1);
        HW.BL.setPower(-Power1);


        //Power for Right   HW.SubLifts
        HW.FR.setPower(-Power2);
        HW.BR.setPower(-Power2);

    }


    //Function to control intake Servos
    private void Collection() {
        if (gamepad2.x) {
            HW.ServoR.setPosition(0);
            HW.ServoL.setPosition(1);
        } else if (gamepad2.a) {
            HW.ServoR.setPosition(1);
            HW.ServoL.setPosition(0);
        }else{
            HW.ServoR.setPosition(0.5);
            HW.ServoL.setPosition(0.5);
        }
    }


    //Function to Control both Lift   HW.SubLifts
    private void LiftSystem(){
        double PowerL;
        PowerL = Range.clip(gamepad2.left_stick_y, -1, 1);
        HW.LiftL.setPower(PowerL);
        HW.LiftR.setPower(PowerL);
    }

    //Function to control Secondary Lift   HW.SubLift
    private void SecondaryLift(){
       // double PowerSL;
       // PowerSL = Range.clip(gamepad2.right_stick_y, -0.8, 0.8);
       // HW.SubLift.setPower(PowerSL);

        telemetry.addData("Current", "%7d ", SubLift.getCurrentPosition());


            if(gamepad2.left_bumper && SubLift.getCurrentPosition() < max) {

                SubLift.setPower(0.8);
                telemetry.addData("End", "Running to %7d ", SubLift.getCurrentPosition());
;
                telemetry.update();

            }

            if(gamepad2.right_bumper && SubLift.getCurrentPosition() < min ) {

                SubLift.setPower(-0.8);
                telemetry.addData("End", "Running to %7d ", SubLift.getCurrentPosition());

                telemetry.update();

            }
            else{
                SubLift.setPower(0);
            }


        }

    }



