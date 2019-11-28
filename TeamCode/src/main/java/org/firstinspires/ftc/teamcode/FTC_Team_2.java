package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "FTC_Team_2", group="Teleop")

public class FTC_Team_2 extends LinearOpMode {
    private Team_2_Hardware HW = new Team_2_Hardware();

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
            LiftMotor();
        }
    }

    //Function To Control Robot Movement
    private void WheelControl()
    {
        double Power1, Power2;
        Power1 = Range.clip(gamepad1.left_stick_y, -1, 1);
        Power2 = Range.clip(gamepad1.right_stick_y, -1, 1);


        //Gamepad Control for Left Motors
        HW.FL.setPower(Power1);
        HW.BL.setPower(Power1);
        //.

        //Gamepad Control for Right Motors
        HW.FR.setPower(Power2);
        HW.BR.setPower(Power2);
        //.
    }

    private void Collection()
    {
        if (gamepad1.x) {
            HW.servo1.setPosition(0);
            HW.servo2.setPosition(1);
        } else if (gamepad1.b) {
            HW.servo1.setPosition(1);
            HW.servo2.setPosition(0);
        } else if (gamepad1.a){
            HW.servo1.setPosition(0.5);
            HW.servo2.setPosition(0.5);
        }
    }


    //Function to Control Lift Motor
    private void LiftMotor()
    {
        if (gamepad1.dpad_up)
            HW.Lift.setPower(1);
        else if (gamepad1.dpad_down)
            HW.Lift.setPower(-1);
        else
            HW.Lift.setPower(0);
    }
}