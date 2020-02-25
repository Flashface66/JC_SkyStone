
        package org.firstinspires.ftc.teamcode.Team_3981;

        import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Tetrix_Motor extends LinearOpMode {

    private DcMotor Motor= null;

    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     COUNTS_PER_MOTOR_HEX    = 288;
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    //  private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) ;
    private static final double     DRIVE_SPEED             = 0.3;
    private static final double     TURN_SPEED              = 0.4;

    @Override
    public void runOpMode() {

        Motor = (DcMotor) hardwareMap.get("Motor");

        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d ",
                Motor.getCurrentPosition());
        telemetry.addLine("Join the Chav religion; Worship Chav");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.left_bumper) {

                int lifttarget = Motor.getCurrentPosition();

                Motor.setTargetPosition(lifttarget + 1440);

                Motor.setPower(0.5);

                Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                telemetry.addData("End", "Running to %7d ", Motor.getCurrentPosition());
                telemetry.update();

                while (
                        (Motor.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("End", "Running to %7d ", Motor.getCurrentPosition());

                    telemetry.update();
                }

                Motor.setPower(0);

            }
            if (gamepad1.right_bumper) {


                int lifttarget = Motor.getCurrentPosition();


                Motor.setTargetPosition(lifttarget - 1440);

                Motor.setPower(0.5);

                Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                telemetry.addData("End", "Running to %7d ", Motor.getCurrentPosition());
                telemetry.update();

                while (
                        (Motor.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("End", "Running to %7d ", Motor.getCurrentPosition());

                    telemetry.update();
                }

                Motor.setPower(0);

            }




        }
    }
}