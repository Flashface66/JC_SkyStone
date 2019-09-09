package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "MemeBot")

    public class FTC_Test extends LinearOpMode {
        private DcMotor Right = null;
        private DcMotor Left = null;
        private DcMotor Claw = null;
        private Servo Rotate1 = null;
        private Servo Rotate2 = null;

        double leftPower;
        double rightPower;



        @Override
        public void runOpMode() {
            telemetry.addData("Robot: Memebot", " Initialized");

        /*
          Mapping each hardware device to the phone configuration file
         */

            Right = hardwareMap.get(DcMotor.class,"Right");
            Left = hardwareMap.get(DcMotor.class,"Left");
            Claw = hardwareMap.get(DcMotor.class, "Claw");
            Rotate1 = hardwareMap.get(Servo.class, "Rotate1");
            Rotate2 = hardwareMap.get(Servo.class, "Rotate2");



         /*+
        Setting the stops for the Robot.
            This makes the motor's activity, once their value is zero, to act as a brake.
        */
            Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Claw.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Claw.setDirection(DcMotor.Direction.FORWARD);
            Right.setDirection(DcMotor.Direction.REVERSE);
            Left.setDirection(DcMotor.Direction.FORWARD);
            Rotate1.setPosition(Servo.MAX_POSITION);





            telemetry.update();

            waitForStart();
            while (opModeIsActive()){

                Movements();
                ClawMove();
                telemetry.update();
            }

        }

        private void Movements() {
            leftPower  = gamepad1.left_stick_y  ;
            rightPower = gamepad1.right_stick_y ;


            Right.setPower(rightPower);
            Left.setPower(leftPower);

        }

        private void ClawMove() {
            if (gamepad1.right_trigger > 0 ) {
                Claw.setPower(1);
            } else if (gamepad1.left_trigger > 0){
                        Claw.setPower(-1);
            } else
                Claw.setPower(0);


            if (gamepad1.a) {
                Rotate1.setPosition(0);
                Rotate2.setPosition(1);
            }
            if (gamepad1.b){
                Rotate1.setPosition(1);
                Rotate2.setPosition(0);
            }
            if (gamepad1.x){
                Rotate1.setPosition(0.5);
                Rotate2.setPosition(0.5);
            }

        }
    }

