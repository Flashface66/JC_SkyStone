package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Team_6899", group="TeleOp")

public class Team_6899 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Hardware_6899 HW = new Hardware_6899();


    @Override
    public void runOpMode() {

        telemetry.addLine("Status: Initialised");
        waitForStart();

        telemetry.addLine("Robot: Team 6899");
        telemetry.addLine("Status: Running");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            WheelControl();
            Collection();
            LiftSystem();
            SecondaryLift();

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }


    //Function To Control Robot Movement
    private void WheelControl() {

        double PowerL, PowerR;
        PowerL = Range.clip(gamepad1.left_stick_y, -0.8, 0.8);
        PowerR = Range.clip(gamepad1.right_stick_y, -0.8, 0.8);


        //Power for Left Motors
        HW.FL.setPower(-PowerL);
        HW.BL.setPower(-PowerL);


        //Power for Right Motors
        HW.FR.setPower(-PowerR);
        HW.BR.setPower(-PowerR);


        telemetry.addData("Motors", "left (%.2f), right (%.2f)", PowerL, PowerR);
        telemetry.update();
    }


    //Function to control intake Servos
    private void Collection() {
        if (gamepad2.x) {
            HW.ServoR.setPosition(0);
            HW.ServoL.setPosition(1);
        } else if (gamepad2.a) {
            HW.ServoR.setPosition(1);
            HW.ServoL.setPosition(0);
        } else {
            HW.ServoR.setPosition(0.5);
            HW.ServoL.setPosition(0.5);
        }
    }


    //Function to Control both Lift   HW.SubLifts
    private void LiftSystem() {
        double PowerLift;
        PowerLift = Range.clip(gamepad2.left_stick_y, -1, 1);
        HW.LiftL.setPower(PowerLift);
        HW.LiftR.setPower(PowerLift);
    }


    //Function to control Secondary Lift Motor
    private void SecondaryLift() {
        double PowerSL;
        PowerSL = Range.clip(gamepad2.right_stick_y, -0.9, 0.9);
        HW.SubLift.setPower(PowerSL);

    }
}



