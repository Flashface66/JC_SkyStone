package org.firstinspires.ftc.teamcode.Club_Meetings;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Clarke Test")
@Disabled

public class Clarke_test  extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Clarke's Robot", "Loading");
        waitForStart();
        while (opModeIsActive()) {
            DriveFunction();
        }
    }
    private void DriveFunction(){

    }

}

