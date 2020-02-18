package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "Mecanaum")
public class MecanumDrive extends LinearOpMode {


    private HardwareWorlds RB  = new HardwareWorlds();

        private final boolean shouldMecanumDrive = true;

        @Override
        public void runOpMode() {
            RB.init(hardwareMap);

            waitForStart();
            while (opModeIsActive()){
                telemetry.addLine("Chav is the best");

                Movement_System();

                Lift();
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
            RB.FrontLeft.setPower(wheels.frontLeft);
            RB.FrontRight.setPower(wheels.frontRight);
            RB.BackLeft.setPower(wheels.backLeft);
            RB.BackRight.setPower(wheels.backRight);

        }

    }

    private void Lift(){
            if (gamepad1.left_bumper){
                RB.Lift1.setPower(1);
                RB.Lift2.setPower(1);
            }
            if (gamepad1.right_bumper){
                RB.Lift1.setPower(-0.4);
                RB.Lift2.setPower(-0.4);
            }
            else{
                RB.Lift1.setPower(0);
                RB.Lift2.setPower(0);
            }

        if (gamepad1.y){
            RB.Rotate.setPosition(1);


        }
        else if (gamepad1.x){
            RB.Rotate.setPosition(0);

        }
        else{
            RB.Rotate.setPosition(0.5);

        }
    }


}
