package org.firstinspires.ftc.teamcode.Club_Meetings;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test OP")
@Disabled
public class TeleOp_Test extends LinearOpMode {
    private DcMotor FrontL = null;
    private DcMotor BackL = null;
    private DcMotor FrontR = null;
    private DcMotor BackR = null;


    @Override
    public void runOpMode(){

        FrontR = hardwareMap.get(DcMotor.class,"FrontR");
        FrontL = hardwareMap.get(DcMotor.class,"FrontL");
        BackR = hardwareMap.get(DcMotor.class,"BackR");
        BackL = hardwareMap.get(DcMotor.class,"BackL");

        FrontR.setDirection(DcMotor.Direction.FORWARD);
        FrontL.setDirection(DcMotor.Direction.REVERSE);
        BackR.setDirection(DcMotor.Direction.FORWARD);
        BackL.setDirection(DcMotor.Direction.REVERSE);

        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


             while (opModeIsActive()){
                 if (gamepad1.left_stick_y > 0){
                     FrontL.setPower(0.6);
                     BackL.setPower(0.6);
                 }else if (gamepad1.right_stick_y > 0){
                     FrontR.setPower(0.6);
                     BackR.setPower(0.6);
                 }else{
                     FrontR.setPower(0.0);
                     FrontL.setPower(0.0);
                     BackR.setPower(0.0);
                     BackL.setPower(0.0);
                 }
             }
     }


}
