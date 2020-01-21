package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MillsBot_2", group="Iterative Opmode")
@Disabled
public class MillsBot_2 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DcMotor pulleyMotor = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    double wheelpower;
    double pulleyPower;
    double servoPos;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        wheelpower = 0.5;
        pulleyPower = 0.5;
        servoPos = 0.0;
        frontRight  = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        pulleyMotor  = hardwareMap.get(DcMotor.class, "pulleyMotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        pulleyMotor.setDirection(DcMotor.Direction.FORWARD);
        leftServo.setPosition(1.0);
        rightServo.setPosition(1.0);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        frontRight.setPower(gamepad1.right_stick_y);
        frontLeft.setPower(gamepad1.left_stick_y);
        backRight.setPower(gamepad1.right_stick_y);
        backLeft.setPower(gamepad1.left_stick_y);

        if(gamepad1.y){
            pulleyMotor.setPower(pulleyPower);
        } else if(gamepad1.a) {
            pulleyMotor.setPower(-pulleyPower);
        } else {
            pulleyMotor.setPower(0.0);
        }

        if(gamepad1.x) {
            rightServo.setPosition(1.0);
            leftServo.setPosition(1.0);
        } else if (gamepad1.b) {
            rightServo.setPosition(0.0);
            leftServo.setPosition(0.0);
        } else {
            leftServo.setPosition(0.5);
            rightServo.setPosition(0.5);
        }


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", wheelpower, wheelpower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
