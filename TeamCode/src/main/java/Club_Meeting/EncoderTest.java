package Club_Meeting;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.R;

@Autonomous(name="EncoderTest", group="Linear Opmode")
//@Disabled
public class EncoderTest extends LinearOpMode {
    final int TICKS_PER_REV = 1120;
    final double GEAR_REDUCTION = 2/3;
    final double CIRCUMFRENCE = (4.0 * Math.PI);
    final double REVS_PER_INCH = ((GEAR_REDUCTION * TICKS_PER_REV) / CIRCUMFRENCE);
    private DcMotor leftDriveMotor = null;
    private DcMotor rightDriveMotor = null;

    public void setDrivePower(double left_pow, double right_pow) {
        leftDriveMotor.setPower(left_pow);
        rightDriveMotor.setPower(right_pow);
    }
/*
    public void setDriveDistance(double distance, double pow) {
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int rotations = (int) (distance * REVS_PER_INCH);
        rightDriveMotor.setTargetPosition(rotations);
        leftDriveMotor.setTargetPosition(rotations);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveMotor.setPower(pow);
        rightDriveMotor.setPower(pow);
    }
*/
    public boolean motorsIsBusy() {
        return leftDriveMotor.isBusy() || rightDriveMotor.isBusy();
    }



    @Override
    public void runOpMode() {
        rightDriveMotor = hardwareMap.get(DcMotor.class, "Right");
        leftDriveMotor = hardwareMap.get(DcMotor.class, "Left");


        rightDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        setDrivePower(0.5,0.5);
        sleep(5000);
        setDrivePower(0,0);
        sleep(25000);
        }
}
