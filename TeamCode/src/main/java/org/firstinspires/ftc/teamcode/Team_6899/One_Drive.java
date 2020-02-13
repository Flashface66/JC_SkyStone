package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "One_Drive", group="Teleop")

public class One_Drive extends LinearOpMode {

    private Hardware_6899 HW = new Hardware_6899();

    @Override
    public void runOpMode() {

        HW.init(hardwareMap); //inititalizing Hardware before start

        waitForStart();

        telemetry.addLine("           Robot: Team 6899");
        telemetry.addLine("************Status: Running***********");
        telemetry.update();

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
        Power1 = Range.clip(gamepad1.left_stick_y, -0.9, 0.9);
        Power2 = Range.clip(gamepad1.right_stick_y,-0.9, 0.9);


        //Power for Left Motors
        HW.FL.setPower(-Power1);
        HW.BL.setPower(-Power1);


        //Power for Right Motors
        HW.FR.setPower(-Power2);
        HW.BR.setPower(-Power2);

    }


    //Function to control intake Servos
    private void Collection() {
        if (gamepad1.x) {
            HW.ServoR.setPosition(0);
            HW.ServoL.setPosition(1);
        } else if (gamepad1.a) {
            HW.ServoR.setPosition(1);
            HW.ServoL.setPosition(0);
        }else{
            HW.ServoR.setPosition(0.5);
            HW.ServoL.setPosition(0.5);
        }
    }


    //Function to Control both Lift Motors
    private void LiftSystem(){

    if (gamepad1.left_trigger > 0) {
        HW.LiftL.setPower(gamepad1.left_trigger);
        HW.LiftR.setPower(gamepad1.left_trigger);

    }
        if (gamepad1.right_trigger > 0) {
            HW.LiftL.setPower(-gamepad1.right_trigger);
            HW.LiftR.setPower(-gamepad1.right_trigger);

        }
        else{
            HW.LiftL.setPower(0);
            HW.LiftR.setPower(0);
        }
    }


    //Function to control Secondary Lift Motor
    private void SecondaryLift() {
        if (gamepad1.dpad_up) {
            HW.SubLift.setPower(0.8);
        }
        if (gamepad1.dpad_down) {
            HW.SubLift.setPower(-0.8);
        } else {

            HW.SubLift.setPower(0);
        }
    }


}

