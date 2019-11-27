package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;




@Autonomous
public class TRY_AGAIN extends LinearOpMode {



    private ElapsedTime runtime = new ElapsedTime();

    private Auto_Test.Hardware_Test RB = new Auto_Test.Hardware_Test();

    ColorSensor colorSensor;




    @Override
    public void runOpMode() throws InterruptedException {


        RB.init(hardwareMap);

        colorSensor = hardwareMap.get(ColorSensor.class, "colour");

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);


        waitForStart();



            encoderDrive(0.5, 0.5, 24, 0.5);
            encoderDrive(0.5, 0.5, -24, 2);
            encoderDrive(0.5, 0.5, 24, 2);
            encoderDrive(0.5, 0.5, -24, 0.5);
            encoderDrive(0.5, 0.5, 24, 0.5);
            encoderDrive(0.5, 0.5, -24, 0.5);
            encoderDrive(0.5, 0.5, 24, 0.5);
            encoderDrive(0.5, 0.5, -24, 0.5);








            stop();


    }

        




    



    public void encoderDrive(double Lspeed, double Rspeed, double Inches, double timeoutS) throws InterruptedException {

        double     COUNTS_PER_MOTOR_REV    = 1440 ;    //Set for NevRest 20 drive. For 40's change to 1120. For 60's 1680
        double     DRIVE_GEAR_REDUCTION    = 1.5 ;     // This is the ratio between the motor axle and the wheel
        double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
        double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        //initialise some variables for the subroutine
        int newLeftTarget;
        int newRightTarget;
        // Ensure that the opmode is still active
        // Determine new target position, and pass to motor controller we only do this in case the encoders are not totally zero'd
        newLeftTarget = (RB.Left.getCurrentPosition()  ) + (int)(Inches * COUNTS_PER_INCH)/2;
        newRightTarget = (RB.Right.getCurrentPosition()  ) + (int)(Inches * COUNTS_PER_INCH)/2;
        telemetry.addLine("Chav is amazing; Praise Chav");
        RB.Left.setTargetPosition(newLeftTarget);
        RB.Right.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        // keep looping while we are still active, and there is time left, and neither set of motors have reached the target
        while ( (runtime.seconds() < timeoutS) &&
                (Math.abs(RB.Left.getCurrentPosition())  < newLeftTarget  &&
                        Math.abs(RB.Right.getCurrentPosition() ) < newRightTarget)) {



            //Pass the seed values to the motors
            RB.Left.setPower(Lspeed);
            RB.Right.setPower(Rspeed);



        }
        // Stop all motion;
        //Note: This is outside our while statement, this will only activate once the time, or distance has been met
        RB.Left.setPower(0);
        RB.Right.setPower(0);
        // show the driver how close they got to the last target
        telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
        telemetry.addData("Path2",  "Running at %7d :%7d", RB.Left.getCurrentPosition(), RB.Right.getCurrentPosition());
        telemetry.update();
        //setting resetC as a way to check the current encoder values easily
        double resetC = ((Math.abs(RB.Left.getCurrentPosition())+Math.abs(RB.Right.getCurrentPosition())));
        //Get the motor encoder resets in motion
        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //keep waiting while the reset is running
        while (Math.abs(resetC) > 0){
            resetC =  ((Math.abs(RB.Left.getCurrentPosition())+ Math.abs(RB.Right.getCurrentPosition())));
            idle();
        }
        // switch the motors back to RUN_USING_ENCODER mode
        RB.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//give the encoders a chance to switch modes.
        waitOneFullHardwareCycle();
        // sleep(250);   // optional pause after each move
    }


   /* public void setColorSensor(ColorSensor colorSensor) {

        this.colorSensor = colorSensor;

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        if (!(values[0] >= 29) && !(hsvValues[0] <= 90)){


        }


    }

    */

    private void Stop(){
        RB.Right.setPower(0);
        RB.Left.setPower(0);
    }
}

