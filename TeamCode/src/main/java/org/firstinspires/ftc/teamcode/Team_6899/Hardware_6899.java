package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressWarnings("WeakerAccess")
public class Hardware_6899 {

    public DcMotor FR = null;
    public DcMotor FL = null;
    public DcMotor BR = null;
    public DcMotor BL = null;
    public DcMotor LiftR = null;
    public DcMotor LiftL = null;
    public DcMotor SubLift = null;
    public Servo ServoR = null;
    public Servo ServoL = null;

    public HardwareMap HwMap;

    Hardware_6899(){}

    public void init (HardwareMap theHwMap){

        HwMap = theHwMap;

        //Motor and Servo names to be used when configuring the Robot
        FR      = HwMap.get(DcMotor.class, "FR");
        FL      = HwMap.get(DcMotor.class, "FL");
        BR      = HwMap.get(DcMotor.class, "BR");
        BL      = HwMap.get(DcMotor.class, "BL");
        LiftR   = HwMap.get(DcMotor.class, "LiftR");
        LiftL   = HwMap.get(DcMotor.class, "LiftL");
        SubLift = HwMap.get(DcMotor.class, "SubLift");
        ServoR  = HwMap.get(Servo.class,   "ServoL");
        ServoL  = HwMap.get(Servo.class,   "ServoR");
        //.


        //Motors programmed to Brake when not in use
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LiftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LiftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        SubLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //Directions set for all drive Motors
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);
        LiftR.setDirection(DcMotor.Direction.REVERSE);
        LiftL.setDirection(DcMotor.Direction.FORWARD);
        SubLift.setDirection(DcMotor.Direction.FORWARD);
    }
}
