package Club_Meeting;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MillsBot", group="Iterative Opmode")
//@Disabled
public class MillsBot extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightForward = null;
    private DcMotor rightBackward = null;
    private DcMotor leftForward;
    private DcMotor leftBackward;
    double power;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        rightForward  = hardwareMap.get(DcMotor.class, "rightForward");
        rightBackward = hardwareMap.get(DcMotor.class, "rightBackward");
        leftForward = hardwareMap.get(DcMotor.class, "leftForward");
        leftBackward = hardwareMap.get(DcMotor.class, "leftBackward");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        rightForward.setDirection(DcMotor.Direction.FORWARD);
        rightBackward.setDirection(DcMotor.Direction.FORWARD);
        leftForward.setDirection(DcMotor.Direction.REVERSE);
        leftBackward.setDirection(DcMotor.Direction.REVERSE);

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
//        power = 0.0;
//        if(gamepad1.x){
//            power = 0.5;
//        } else if(gamepad1.b) {
//            power = -0.5;
//            }
        rightForward.setPower(gamepad1.right_stick_y);
        rightBackward.setPower(gamepad1.right_stick_y);
        leftForward.setPower(gamepad1.left_stick_y);
        leftBackward.setPower(gamepad1.left_stick_y);
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", power, power);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
