package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FTC_Team_2")

public class FTC_Team_2 extends LinearOpMode {

    private Team_2_Hardware HW = new Team_2_Hardware();

    @Override
    public void runOpMode() {
        telemetry.addData("Robot: J's Team", "Running!");

        //inititalizing Hardware before start
        HW.init(hardwareMap);


        waitForStart();
        while (opModeIsActive()) {
            WheelControl();
        }
    }

    //Function To Control Robot Movement
    private void WheelControl() {


        //Gamepad Control for Left Motors
        if (gamepad1.left_stick_y > 0) {
            HW.FL.setPower(1);
            HW.BL.setPower(1);
        } else if (gamepad1.left_stick_y < 0) {
            HW.FL.setPower(-1);
            HW.BL.setPower(-1);
        } else {
            HW.FL.setPower(0);
            HW.BL.setPower(0);
        }
        //.


        //Gamepad Control for Right Motors
        if (gamepad1.right_stick_y > 0) {
            HW.FR.setPower(1);
            HW.BR.setPower(1);
        } else if (gamepad1.right_stick_y < 0) {
            HW.FR.setPower(-1);
            HW.BR.setPower(-1);
        } else {
            HW.FR.setPower(0);
            HW.BR.setPower(0);
        }
        //.
    }
}