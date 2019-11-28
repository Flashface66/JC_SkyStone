package org.firstinspires.ftc.teamcode.Club_Meetings;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "TollBot")

public class TollDay_Bot extends LinearOpMode {

    public DcMotor Left  = null;
    public DcMotor Right = null;
    public DcMotor Collect = null;




    @Override
    public void runOpMode(){
        Left    = hardwareMap.get(DcMotor.class,"Left");
        Right   = hardwareMap.get(DcMotor.class,"Right");
        Collect = hardwareMap.get(DcMotor.class,"Collect");

        Left.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);




        waitForStart();
        while (opModeIsActive()){
            DriveTrain();
            Box();

        }
    }


    public void DriveTrain() {
        /*double Power1, Power2;
        Power1 = Range.clip(gamepad1.left_stick_y, -1,1);
        Power2 = Range.clip(gamepad1.right_stick_y, -1,1);
        Left.setPower(Power1);
        Right.setPower(Power2);

         */
        double leftPower = gamepad1.left_stick_y ;
        double rightPower = gamepad1.right_stick_y;


            Left.setPower(leftPower);
            Right.setPower(rightPower);


    }

    public void Box(){
       if (gamepad1.right_trigger > 0){
           Collect.setPower(gamepad1.right_trigger);
       }
       else if (gamepad1.left_trigger > 0){
           Collect.setPower(-gamepad1.left_trigger);
       }
       else
           Collect.setPower(0);

    }
}
