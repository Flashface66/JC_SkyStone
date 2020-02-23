package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "FrittazMec")
public class MecanumDrive extends LinearOpMode {


    private Hardware_Test_V2 RB = new Hardware_Test_V2();

        private final boolean shouldMecanumDrive = true;

        @Override
        public void runOpMode() {
            RB.init(hardwareMap);

            waitForStart();
            while (opModeIsActive()){

                Movement_System();

                ClawMove();
                telemetry.update();
            }

        }

    private void  Movement_System() {


        if (shouldMecanumDrive) {
            // Convert joysticks to desired motion.
            Mecanum.Motion motion = Mecanum.joystickToMotion(
                    gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.right_stick_y);

            // Convert desired motion to wheel powers, with power clamping.
            Mecanum.Wheels wheels = Mecanum.motionToWheels(motion);
            RB.Left .setPower(wheels.frontLeft);
            RB.Right.setPower(wheels.frontRight);
            RB.LeftB.setPower(wheels.backLeft);
            RB.RightB.setPower(wheels.backRight);

        }

    }

    private void ClawMove() {


        if (gamepad2.left_bumper){
            RB.Claw2.setPower(1);
        }else if (gamepad2.right_bumper){

            RB.Claw2.setPower(-1);
        }else {

            RB.Claw2.setPower(0);
        }




        if (gamepad2.a) {
            RB.Rotate1.setPosition(0.5);
        }
        if (gamepad2.b){
            RB.Rotate1.setPosition(1);
        }

        if (gamepad2.y){
            RB.Rotate2.setPosition(1);
            RB.Rotate1.setPosition(0);

        }
        else if (gamepad2.x){
            RB.Rotate2.setPosition(0);
            RB.Rotate1.setPosition(1);
        }
        else{
            RB.Rotate2.setPosition(0.5);
            RB.Rotate1.setPosition(0.5);
        }

    }


}
