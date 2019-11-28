package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 */

@Autonomous(name="Team2: Auto_By_Time", group="Pushbot")

public class AutoByTime_6899 extends LinearOpMode {


    @Override
    public void runOpMode() {

        Hardware_6899 HWA     = new Hardware_6899(); // Uses my Pushbot's hardware
        ElapsedTime      runtime = new ElapsedTime();     // research elapsed time
        double     FORWARD_SPEED = -0.5;
        double     TURN_SPEED    = 0.3;
        double     LIFT_SPEED    = 0.3;
        double     SERVO_POS1    = 0.0;
        double     SERVO_POS2    = 1.0;

        HWA.init(hardwareMap);
        telemetry.addData("Status", "Ready and Waiting to run");
        telemetry.update();

        waitForStart();
        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
        // Step 1:  Drive forward for 3 seconds
        HWA.FL.setPower(FORWARD_SPEED);
        HWA.FR.setPower(FORWARD_SPEED);
        HWA.BL.setPower(FORWARD_SPEED);
        HWA.BR.setPower(FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }//.



        // Step 2:  Spin Left for 1.0 seconds
        HWA.FL.setPower(TURN_SPEED);
        HWA.BL.setPower(TURN_SPEED);
        HWA.FR.setPower(-TURN_SPEED);
        HWA.BR.setPower(-TURN_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }//.



        // Step 3:  Stop Moving
        HWA.FL.setPower(0);
        HWA.FR.setPower(0);
        HWA.BL.setPower(0);
        HWA.BR.setPower(0);
        HWA.Lift.setPower(LIFT_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 3.0)){
            telemetry.addData("Path", "Leg 3: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }//.



        //Step 4: Move Forward Slightly
        HWA.Lift.setPower(0);
        HWA.FL.setPower(FORWARD_SPEED);
        HWA.FR.setPower(FORWARD_SPEED);
        HWA.BL.setPower(FORWARD_SPEED);
        HWA.BR.setPower(FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < 1.2){
            telemetry.addData("Path", "Leg 4: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }//.


        HWA.FL.setPower(0);
        HWA.FR.setPower(0);
        HWA.BL.setPower(0);
        HWA.BR.setPower(0);

        while(opModeIsActive() && runtime.seconds() < 2.0){
            telemetry.addData("Path", "Leg 6: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }//.

        HWA.servo1.setPosition(SERVO_POS1);
        HWA.servo2.setPosition(SERVO_POS2);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0){
            telemetry.addData("Path", "Leg 7: %2.5f Seconds Elapsed");
            telemetry.update();
        }

        //Step 7: Reverse
        HWA.FL.setPower(-FORWARD_SPEED);
        HWA.FR.setPower(-FORWARD_SPEED);
        HWA.BL.setPower(-FORWARD_SPEED);
        HWA.BR.setPower(-FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && runtime.seconds() < 0.5){
            telemetry.addData("Path", "Leg 7: %2.5f Seconds Elapsed", runtime.seconds());
            telemetry.update();
        }


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
