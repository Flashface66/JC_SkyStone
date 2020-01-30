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
    public DcMotor SubLift;
    public Servo ServoR = null;
    public Servo ServoL = null;

    public HardwareMap hwmap2;

    Hardware_6899(){}

    public void init (HardwareMap thehwmap2){

        hwmap2    = thehwmap2;

        //Motor and Servo names to be used when configuring the Robot
        FR      = hwmap2.get(DcMotor.class, "FR");
        FL      = hwmap2.get(DcMotor.class, "FL");
        BR      = hwmap2.get(DcMotor.class, "BR");
        BL      = hwmap2.get(DcMotor.class, "BL");
        LiftR   = hwmap2.get(DcMotor.class, "LiftR");
        LiftL   = hwmap2.get(DcMotor.class, "LiftL");
        SubLift = hwmap2.get(DcMotor.class, "SubLift");
        ServoR  = hwmap2.get(Servo.class,   "ServoL");
        ServoL  = hwmap2.get(Servo.class,   "ServoR");
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
        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.REVERSE);
        LiftR.setDirection(DcMotor.Direction.FORWARD);
        LiftL.setDirection(DcMotor.Direction.REVERSE);
        SubLift.setDirection(DcMotor.Direction.REVERSE);
    }
}
