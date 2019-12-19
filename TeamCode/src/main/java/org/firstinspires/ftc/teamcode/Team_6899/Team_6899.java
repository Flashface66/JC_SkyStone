package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Team_6899", group="Teleop")

public class Team_6899 extends LinearOpMode {
    private Hardware_6899 HW = new Hardware_6899();

    @Override
    public void runOpMode()
    {
        telemetry.addLine("Robot: J's Team");
        telemetry.addLine("************Status: Running***********");

        HW.init(hardwareMap); //inititalizing Hardware before start


        waitForStart();


        while (opModeIsActive())
        {
            WheelControl();

            Collection();

            LiftSystem();

            SecondaryLift();
        }
    }

    //Function To Control Robot Movement
    private void WheelControl()
    {
        double Power1, Power2;
        Power1 = Range.clip(gamepad1.left_stick_y, -1, 1);
        Power2 = Range.clip(gamepad1.right_stick_y,-1, 1);


        //Gamepad Control for Left Motors
        HW.FL.setPower(-Power1);
        HW.BL.setPower(-Power1);
        //.

        //Gamepad Control for Right Motors
        HW.FR.setPower(-Power2);
        HW.BR.setPower(-Power2);
        //.
    }

    //Function to control intake Servos
    private void Collection()
    {
        if (gamepad1.x) {
            HW.ServoR.setPosition(0.0);
            HW.ServoL.setPosition(1.0);
        } else if (gamepad1.b) {
            HW.ServoR.setPosition(1);
            HW.ServoL.setPosition(0);
        } else if (gamepad1.a){
            HW.ServoR.setPosition(0.5);
            HW.ServoL.setPosition(0.5);
        }
    }


    //Function to Control Lift Motor
    private void LiftSystem()
    {
        if (gamepad1.dpad_up) {
            HW.LiftL.setPower(1);
            HW.LiftR.setPower(1);
        }
        else if (gamepad1.dpad_down) {
            HW.LiftL.setPower(-1);
            HW.LiftR.setPower(-1);
        }
        else {
            HW.LiftL.setPower(0);
            HW.LiftR.setPower(0);
        }
    }

    //Function to control Secondary Lift
    private void SecondaryLift()
    {
        if (gamepad1.right_trigger > 0)
            HW.SubLift.setPower(1);
        else if (gamepad1.left_trigger > 0)
            HW.SubLift.setPower(-1);
        else
            HW.SubLift.setPower(0);
    }
}