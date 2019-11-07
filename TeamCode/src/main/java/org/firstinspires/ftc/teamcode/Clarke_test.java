package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Clarke Test")

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

