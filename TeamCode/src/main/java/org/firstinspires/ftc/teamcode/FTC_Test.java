package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestBot")
@Disabled

    public class FTC_Test extends LinearOpMode {

        private Hardware_Test RB = new Hardware_Test();


    @Override
        public void runOpMode() {
            telemetry.addData("Robot: Testbot", " Initialized");

            RB.init(hardwareMap);

            waitForStart();
            while (opModeIsActive()){

                Movements();

                ClawMove();
            }

        }

        private void Movements() {
            double leftPower = gamepad1.left_stick_y ;
            double rightPower = gamepad1.right_stick_y;


            RB.Right.setPower(rightPower);
            RB.Left.setPower(leftPower);

        }

        private void ClawMove() {
            if (gamepad1.right_trigger > 0 ) {
               RB.Claw.setPower(1);
            } else if (gamepad1.left_trigger > 0){
               RB.Claw.setPower(-1);
            } else
               RB.Claw.setPower(0);


            if (gamepad1.a) {
                RB.Rotate1.setPosition(0);
                RB.Rotate2.setPosition(1);
            }
            if (gamepad1.b){
                RB.Rotate1.setPosition(1);
                RB.Rotate2.setPosition(0);
            }
            if (gamepad1.x){
                RB.Rotate1.setPosition(0.5);
                RB.Rotate2.setPosition(0.5);
            }

        }
    }

