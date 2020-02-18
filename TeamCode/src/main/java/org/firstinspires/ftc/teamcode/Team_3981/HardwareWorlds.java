package org.firstinspires.ftc.teamcode.Team_3981;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is the hardware for the 3981 teams robot or Worlds' bot.
 * This stroes the each harware component for the program and the configuration of them
 * Do not edit unless you are sure of what you're doing - Chavaughn
 */

public class HardwareWorlds {


    public DcMotor FrontLeft  = null;
    public DcMotor FrontRight = null;
    public DcMotor BackRight  = null;
    public DcMotor BackLeft   = null;
    public DcMotor Lift1      = null;
    public DcMotor Lift2      = null;
    public Servo   Rotate     = null;

    
    public HardwareMap hwmap;


    HardwareWorlds(){

    }

    public void init(HardwareMap thehwmap){
        hwmap = thehwmap;
        //Initializes the motors and servos for the robot.
        FrontLeft    = hwmap.get(DcMotor.class, "FrontLeft");
        FrontRight   = hwmap.get(DcMotor.class, "FrontRight");
        BackRight    = hwmap.get(DcMotor.class, "BackRight");
        BackLeft     = hwmap.get(DcMotor.class, "BackLeft");
        Lift1        = hwmap.get(DcMotor.class, "Lift1");
        Lift2        = hwmap.get(DcMotor.class, "Lift2");
        Rotate       = hwmap.get(Servo.class,   "Rotate");






        //Sets the right direction for the Motors in the drivetrain.
        FrontRight.setDirection(DcMotor.Direction.REVERSE);//Reverse
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);//Forward
        BackLeft.setDirection(DcMotor.Direction.FORWARD);//Forward
        BackRight.setDirection(DcMotor.Direction.REVERSE);//Reverse
        Lift2.setDirection(DcMotor.Direction.REVERSE);//Reverse


        //For autonomous make sure to set to Run using encoders.
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //The behaviour of the motors when it is set to 0 power.
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
