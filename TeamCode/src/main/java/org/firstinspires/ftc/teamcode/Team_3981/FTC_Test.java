package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FTC_Prototype")
@Disabled
    public class FTC_Test extends LinearOpMode {

        private Hardware_Test RB = new Hardware_Test();


    @Override
        public void runOpMode() {

            RB.init(hardwareMap);

            waitForStart();
            while (opModeIsActive()){ //If houses could fly, then could cabbages broccoli??
                telemetry.addLine("THIS CODE NO LONGER WORKS, SELF DESTRUCT IN 3...2...1...BOOB");
                telemetry.update();
                Movements();

                ClawMove();

            }

        }

        private void Movements() {
            double leftPower = gamepad1.left_stick_y ;
            double rightPower = gamepad1.right_stick_y;

            RB.Right.setPower(-rightPower);
            RB.Left.setPower(-leftPower );

        }

        private void ClawMove() {
            if (gamepad1.left_bumper){
                RB.Claw1.setPower(1);
                RB.Claw2.setPower(1);
            }else if (gamepad1.right_bumper){
                RB.Claw1.setPower(-1);
                RB.Claw2.setPower(-1);
            }else {
                RB.Claw1.setPower(0);
                RB.Claw2.setPower(0);
            }
            if (gamepad1.a) {
                RB.Rotate1.setPosition(0.5);
            }
            if (gamepad1.b){
                RB.Rotate1.setPosition(1);
            }

            if (gamepad1.y){
                RB.Rotate2.setPosition(1);
            }
            else if (gamepad1.x){
                RB.Rotate2.setPosition(0);
            }
            else{
                RB.Rotate2.setPosition(0.5);
            }

        }



    }


