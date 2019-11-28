package org.firstinspires.ftc.teamcode.Team_3981;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;




public class Hardware_Test {

    public DcMotor Right   = null;
    public DcMotor Left    = null;
    public DcMotor Claw1   = null;
    public DcMotor Claw2   = null;
    public Servo   Rotate1 = null;
    public Servo   Rotate2 = null;
    public Servo   Gate    = null;
    public DcMotor Swivel1 = null;
    public DcMotor Swivel2 = null;

    public HardwareMap hwmap;


    Hardware_Test(){

    }

    public void init (HardwareMap thehwmap){

        hwmap   = thehwmap;

    /*
      Mapping each hardware device to the phone configuration file
     */

        Right   = hwmap.get(DcMotor.class,"Right");
        Left    = hwmap.get(DcMotor.class,"Left");
        Claw1   = hwmap.get(DcMotor.class, "Claw1");
        Claw2   = hwmap.get(DcMotor.class, "Claw2");
        Rotate1 = hwmap.get(Servo.class, "Rotate1");
        Rotate2 = hwmap.get(Servo.class, "Rotate2");
        Gate    = hwmap.get(Servo.class, "Gate");
        Swivel1 = hwmap.get(DcMotor.class, "Swivel1");
        Swivel2 = hwmap.get(DcMotor.class, "Swivel2");

    /*
      Setting the stops for the Robot.
      This makes the motor's activity, once their value is zero, to act as a brake.
     */


        Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Swivel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Swivel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Claw1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Claw2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    /*
      Setting the direction for each motor.
     */

        Swivel1.setDirection(DcMotor.Direction.REVERSE);
        Swivel2.setDirection(DcMotor.Direction.FORWARD);
        Claw1.setDirection(DcMotor.Direction.REVERSE);
        Claw2.setDirection(DcMotor.Direction.FORWARD);

    }

}