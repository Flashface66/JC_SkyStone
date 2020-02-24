package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "FTC_Prototype_V2", group = "Teleop")
    public class FTC_Test_V2 extends LinearOpMode {
        enum state{ Slow, Fast }state Motion_Variable;
        private Hardware_Test_V2 RB = new Hardware_Test_V2();








    @Override
        public void runOpMode() {



        telemetry.update();
        RB.init(hardwareMap);
            waitForStart();
            while (opModeIsActive()){

                if (gamepad1.a){
                    Motion_Variable = state.Fast;
                }else if (gamepad1.b){
                    Motion_Variable = state.Slow;
                }


                Advanced_Movement();
                ClawMove();

            }

        }



        private void Advanced_Movement(){


            if (Motion_Variable == state.Fast){
                RB.Right.setPower(-Range.clip(gamepad1.right_stick_y, -0.9, 0.9));
                RB.Left.setPower(-Range.clip(gamepad1.left_stick_y, -0.9, 0.9));
                RB.RightB.setPower(-Range.clip(gamepad1.right_stick_y, -0.9, 0.9));
                RB.LeftB.setPower(-Range.clip(gamepad1.left_stick_y, -0.9, 0.9));
                telemetry.addLine("Speed");
            }
            if (Motion_Variable == state.Slow) {
                RB.Right.setPower(-gamepad1.right_stick_y * 0.5);
                RB.Left.setPower(-gamepad1.left_stick_y * 0.5);
                RB.RightB.setPower(-gamepad1.right_stick_y * 0.5);
                RB.LeftB.setPower(-gamepad1.left_stick_y * 0.5);
                telemetry.addLine("Slow");
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


/*
            telemetry.addData("Current", "%7d ",Claw2 .getCurrentPosition());


            if(gamepad2.left_bumper && Claw2.getCurrentPosition() < max) {

                Claw2.setPower(0.8);
                telemetry.addData("End", "Running to %7d ", Claw2.getCurrentPosition());
                ;
                telemetry.update();

            }

            if(gamepad2.right_bumper && Claw2.getCurrentPosition() > min ) {

                Claw2.setPower(-0.8);
                telemetry.addData("End", "Running to %7d ", Claw2.getCurrentPosition());

                telemetry.update();

            }
            else{
                Claw2.setPower(0);
            }

 */












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


