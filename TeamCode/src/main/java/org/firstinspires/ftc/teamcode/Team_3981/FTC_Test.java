package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "FTC_Prototype", group = "Teleop")

    public class FTC_Test extends LinearOpMode {

        private Hardware_Test RB = new Hardware_Test();
        private static final double     COUNTS_PER_MOTOR_HEX    = 288;



    @Override
        public void runOpMode() {
      //  RB.Claw1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // RB.Claw2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // RB.Claw1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       // RB.Claw2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            RB.init(hardwareMap);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                RB.Claw1.getCurrentPosition(),
                RB.Claw2.getCurrentPosition());
        telemetry.update();

            waitForStart();
            while (opModeIsActive()){

                Movements();

                ClawMove();

            }

        }

        private void Movements() {
            double leftPower ;
            double rightPower;

            leftPower = Range.clip(gamepad1.left_stick_y, -0.6, 0.6);
            rightPower= Range.clip(gamepad1.right_stick_y,-0.6, 0.6);

            RB.Right.setPower(-rightPower);
            RB.Left.setPower(-leftPower );

        }

        private void ClawMove() {


            if (gamepad2.left_bumper){
                RB.Claw1.setPower(1);
                RB.Claw2.setPower(1);
            }else if (gamepad2.right_bumper){
                RB.Claw1.setPower(-1);
                RB.Claw2.setPower(-1);
            }else {
                RB.Claw1.setPower(0);
                RB.Claw2.setPower(0);
            }












/*


            if (gamepad1.left_bumper) {
                int lifttarget1 = RB.Claw1.getCurrentPosition() ;
                int lifttarget2 = RB.Claw2.getCurrentPosition() ;

                RB.Claw1.setTargetPosition((-lifttarget1) + 76);
                RB.Claw2.setTargetPosition((-lifttarget2) + 76);

                RB.Claw1.setPower(0.5);
                RB.Claw2.setPower(0.5);

                RB.Claw1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                telemetry.addData("End",  "Running to %7d :%7d, %7d,%7d",  RB.Claw1.getCurrentPosition(),
                        RB.Claw2.getCurrentPosition(), lifttarget1,lifttarget2);
                telemetry.update();
            }
            if (gamepad1.right_bumper) {



                int lifttarget1 = RB.Claw1.getCurrentPosition() ;
                int lifttarget2 = RB.Claw2.getCurrentPosition() ;

                RB.Claw1.setTargetPosition(-lifttarget1 - 76);
                RB.Claw2.setTargetPosition(-lifttarget2 - 76);

                RB.Claw1.setPower(0.5);
                RB.Claw2.setPower(0.5);

                RB.Claw1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);



                telemetry.addData("End",  "Running to %7d :%7d, %7d,%7d",  RB.Claw1.getCurrentPosition(),
                        RB.Claw2.getCurrentPosition(), lifttarget1,lifttarget2);
                telemetry.update();
            }






 */


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


