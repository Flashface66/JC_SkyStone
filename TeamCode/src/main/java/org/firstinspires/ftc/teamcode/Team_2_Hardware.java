package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressWarnings("WeakerAccess")
public class Team_2_Hardware {

    public DcMotor FR = null;
    public DcMotor FL = null;
    public DcMotor BR = null;
    public DcMotor BL = null;

    public HardwareMap hwmap2;

    Team_2_Hardware(){}

    public void init (HardwareMap thehwmap2){

        hwmap2    = thehwmap2;

        //Motor names to be used when configuring the Robot
        FR = hwmap2.get(DcMotor.class,"FR");
        FL = hwmap2.get(DcMotor.class,"FL");
        BR = hwmap2.get(DcMotor.class, "BR");
        BL = hwmap2.get(DcMotor.class, "BL");
        //.


        //Motors programmed to Brake when not in use
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //.


        /*
        *  Motors on the Right rotate forward(used to judge natural forward)
        *  Motors on the Left rotate forward(they are inverted to the right motors, hence use of reverse)
        */
        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.REVERSE);
        //.
}

}
