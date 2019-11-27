package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressWarnings("WeakerAccess")
public class Team_2_Hardware {

    public DcMotor FR = null;
    public DcMotor FL = null;
    public DcMotor BR = null;
    public DcMotor BL = null;
    public DcMotor Lift = null;
    public Servo servo1 = null;
    public Servo servo2 = null;

    public HardwareMap hwmap2;

    Team_2_Hardware(){}

    public void init (HardwareMap thehwmap2){

        hwmap2    = thehwmap2;

        //Motor and Servo names to be used when configuring the Robot
        FR = hwmap2.get(DcMotor.class, "FR");
        FL = hwmap2.get(DcMotor.class, "FL");
        BR = hwmap2.get(DcMotor.class, "BR");
        BL = hwmap2.get(DcMotor.class, "BL");
        Lift = hwmap2.get(DcMotor.class, "Lift");
        servo1 = hwmap2.get(Servo.class,"Servo1");
        servo2 = hwmap2.get(Servo.class,"Servo2");
        //.


        //Motors programmed to Brake when not in use
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //.


        //Directions set for all drive Motors
        FR.setDirection(DcMotor.Direction.REVERSE);
        FL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        //.
}

}
