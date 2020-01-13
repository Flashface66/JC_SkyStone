package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Team_6899", group="Teleop")

public class Team_6899 extends LinearOpMode {

    private Hardware_6899 HW = new Hardware_6899();

    @Override
    public void runOpMode() {
        telemetry.addLine("           Robot: Team 6899");
        telemetry.addLine("************Status: Running***********");

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
        Power1 = Range.clip(gamepad1.left_stick_y, -0.7, 0.7);
        Power2 = Range.clip(gamepad1.right_stick_y,-0.7, 0.7);


        //Power for Left Motors
        HW.FL.setPower(-Power1);
        HW.BL.setPower(-Power1);


        //Power for Right Motors
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


    //Function to Control both Lift Motors
    private void LiftSystem(){
        double PowerL;
        PowerL = Range.clip(gamepad2.left_stick_y, -1, 1);
        HW.LiftL.setPower(PowerL);
        HW.LiftR.setPower(PowerL);
    }

    //Function to control Secondary Lift Motor
    private void SecondaryLift(){
        double PowerSL;
        PowerSL = Range.clip(gamepad2.right_stick_y, -0.8, 0.8);
        HW.SubLift.setPower(PowerSL);
    }

}

