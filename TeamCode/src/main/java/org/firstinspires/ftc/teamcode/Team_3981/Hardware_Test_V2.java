package org.firstinspires.ftc.teamcode.Team_3981;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Hardware_Test_V2 {

    public DcMotor Right   = null;
    public DcMotor Left    = null;
    public DcMotor RightB   = null;
    public DcMotor LeftB    = null;
    public DcMotor Claw1   = null;
    public DcMotor Claw2   = null;
    public Servo   Rotate1 = null;
    public Servo   Rotate2 = null;


    public HardwareMap hwmap;


    Hardware_Test_V2(){

    }

    public void init (HardwareMap thehwmap){

        hwmap   = thehwmap;

    /*
      Mapping each hardware device to the phone configuration file
     */

        Right   = hwmap.get(DcMotor.class,"Right");
        Left    = hwmap.get(DcMotor.class,"Left");
        RightB   = hwmap.get(DcMotor.class,"RightB");
        LeftB    = hwmap.get(DcMotor.class,"LeftB");
        Claw1   = hwmap.get(DcMotor.class, "Claw1");
        Claw2   = hwmap.get(DcMotor.class, "Claw2");
        Rotate1 = hwmap.get(Servo.class, "Rotate1");
        Rotate2 = hwmap.get(Servo.class, "Rotate2");



    /*
      Setting the stops for the Robot.
      This makes the motor's activity, once their value is zero, to act as a brake.
     */


        Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Claw1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Claw2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    /*
      Setting the direction for each motor.
     */


        Claw1.setDirection(DcMotor.Direction.REVERSE);
        Claw2.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);
        RightB.setDirection(DcMotor.Direction.REVERSE);
    }

}