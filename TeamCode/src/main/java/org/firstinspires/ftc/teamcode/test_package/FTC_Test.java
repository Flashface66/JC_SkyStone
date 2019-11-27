package org.firstinspires.ftc.teamcode.test_package;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FTC_Prototype")

    public class FTC_Test extends LinearOpMode {

        private Hardware_Test RB = new Hardware_Test();


    @Override
        public void runOpMode() {

            RB.init(hardwareMap);

            waitForStart();
            while (opModeIsActive()){ //If houses could fly, then could cabbages broccoli??
                telemetry.addLine("ThIS CODE NO LONGER WORKS, sELF DESTRUCT IN 3...2...1...BOMB");
                telemetry.update();
                Movements();

                ClawMove();

                ArmMove();
            }

        }

        private void Movements() {
            double leftPower = gamepad1.left_stick_y ;
            double rightPower = gamepad1.right_stick_y;

            RB.Right.setPower(-rightPower);
            RB.Left.setPower(leftPower);

        }

        private void ClawMove() {
            if (gamepad1.left_bumper){
                RB.Claw1.setPower(1);
                RB.Claw2.setPower(1);
            }else if (gamepad1.right_bumper){
                RB.Claw1.setPower(-1);
                RB.Claw2.setPower(-1);
            }else
                RB.Claw1.setPower(0);
                RB.Claw2.setPower(0);

            if (gamepad1.a) {
                RB.Rotate1.setPosition(0);
                RB.Rotate2.setPosition(1);
            }
            if (gamepad1.b){
                RB.Rotate1.setPosition(1);
                RB.Rotate2.setPosition(0);
            }

        }

        private void ArmMove(){
        if (gamepad1.right_trigger > 0.1){
            RB.Gate.setPosition(1);

             if (gamepad1.right_trigger > 0.6){
                RB.Swivel1.setPower(-gamepad1.right_trigger);
                RB.Swivel2.setPower(gamepad1.right_trigger);
             }

        }
       else if (gamepad1.left_trigger >0.1 ) {
            RB.Gate.setPosition(1);

            if (gamepad1.left_trigger > 0.6){
                RB.Swivel1.setPower(gamepad1.left_trigger);
                RB.Swivel2.setPower(-gamepad1.left_trigger);
                //this is broken now I LIKE BEANS BOMBVON
            }
       }




       else {
            RB.Swivel1.setPower(0); //Traveling through time is just like slicing an apple in butter
            RB.Swivel2.setPower(0);
            RB.Gate.setPosition(0);
        }

        }
    }

