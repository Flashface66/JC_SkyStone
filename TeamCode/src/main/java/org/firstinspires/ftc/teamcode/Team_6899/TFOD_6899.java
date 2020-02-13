package org.firstinspires.ftc.teamcode.Team_6899;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

@Autonomous(name = "TFOD_6899", group = "SkystoneSide")

public class TFOD_6899 extends LinearOpMode {

    //TFOD_6899 Variables Declarations
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY = "AXd3t+r/////AAABmYdQv5eup0Yvt7Cp6op1bbch4Lq/mi6/90lBIGTa3G86Q3M3vF8VTjha2cPyJ5auJ3gFCwIHbHN2tytuuutr+ucEh2QiMkH+1iwzbp7dq4XQ9PRVrvbotldPz/savzAeUUJc9ygJZOD7fJkYEgf3fOWqHpWyAFoydu8zT2tyT0lgGwDJMdvWx3K5haYE0FSOKXSAQuGVl0DFQKucFjadPmWR7k9CkA17tTuaFSPnBYrdKg5vVWqgcpFezLf8ZllGI88AHeKBrYmtNx9ZiXR71p7R0M7kp+u2Dx5UHDzCA9KxJWapTGxZpWHhvqPBeDgdviMqmEMXOkRsNQYbLbqzB0S/k83x/8WG0EEOiC7H1FFA";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    //Encoder Variables Declarations
    private Hardware_6899 HWA     = new Hardware_6899();   // Uses my hardware
    private ElapsedTime runtime   = new ElapsedTime();
    private static final double     COUNTS_PER_MOTOR_REV   = 1120;
    private static final double     DRIVE_GEAR_REDUCTION   = 1.0 ;
    private static final double     WHEEL_DIAMETER_INCHES  = 4.0 ;
    private static final double     COUNTS_PER_INCH        = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/(WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED            = 0.8;
    private static final double     TURN_SPEED             = 0.7;
    private static final double     LIFT_SPEED             = 0.8;


    @Override
    public void runOpMode() {

        initVuforia();
        HWA.init(hardwareMap);

        //Stop and Reset all Motor's Encoder Values
        HWA.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HWA.LiftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Run Mode for all Motors to run RUN_USING_ENCODER
        HWA.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.LiftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HWA.LiftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        if (ClassFactory.getInstance().canCreateTFObjectDetector()){
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        if (tfod != null){
            tfod.activate();
        }

        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();

        waitForStart();

        //encoderDrive(DRIVE_SPEED, 5, 5);
        //encoderLift(LIFT_SPEED, 20);

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.

                    encoderDrive(TURN_SPEED, -5, 5);
                    sleep(1500);

                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# of Objects Detected", updatedRecognitions.size());

                        // step through the list of recognitions and display boundary info.
                        int i = 0;

                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i),     "%.03f , %.03f", recognition.getLeft(),  recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f", recognition.getRight(), recognition.getBottom());
                            boolean skystone;
                            skystone = (recognition.getLabel().equals(LABEL_SECOND_ELEMENT));

                            if ((skystone) && (runtime.seconds() < 6)){
                                encoderLift(LIFT_SPEED, -18);
                                encoderDrive(DRIVE_SPEED, 40, 40);

                                servoClose();
                                encoderDrive(DRIVE_SPEED, -40, -40);
                                encoderDrive(TURN_SPEED, -12, 12);
                                encoderDrive(DRIVE_SPEED, -90, -90);
                                encoderDrive(TURN_SPEED, 12, -12);
                                encoderLift(LIFT_SPEED, 15);


                            }else{
                                encoderDrive(TURN_SPEED, 4, 4);
                            }

                        }
                        telemetry.update();
                    }
                }
            }
        }

        if (tfod != null){
            tfod.shutdown();
        }
    }

    //Initialize the TFOD_6899 localization engine.
    private void initVuforia() {
        /*
         * Configure TFOD_6899 by creating a Parameter object, and passing it to the TFOD_6899 engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the TFOD_6899 engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    //Initialize the TensorFlow Object Detection engine.
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    //Function to Run wheel motors
    private void encoderDrive(double speed, double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newLeftTarget   = HWA.FL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget  = HWA.FR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            HWA.FL.setTargetPosition(newLeftTarget);
            HWA.BL.setTargetPosition(newLeftTarget);
            HWA.FR.setTargetPosition(newRightTarget);
            HWA.BR.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            HWA.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            HWA.FL.setPower(Math.abs(speed));
            HWA.BL.setPower(Math.abs(speed));
            HWA.FR.setPower(Math.abs(speed));
            HWA.BR.setPower(Math.abs(speed));


            while (opModeIsActive() &&
                    (runtime.seconds() < 3.0) &&
                    (HWA.FL.isBusy() && HWA.BL.isBusy() && HWA.FR.isBusy() && HWA.BR.isBusy())) {
                telemetry.update();
            }

            // Stop all motion;
            HWA.FR.setPower(0);
            HWA.BR.setPower(0);
            HWA.FL.setPower(0);
            HWA.BL.setPower(0);


            // Turn off RUN_TO_POSITION
            HWA.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            sleep(200);   // optional pause after each move
        }
    }

    //Function to Run Lift motors
    private void encoderLift(double LIFT_SPEED, double upInches){
        int LiftTargetL;
        int LiftTargetR;

        if (opModeIsActive()){
            LiftTargetL = HWA.LiftR.getCurrentPosition() + (int)(upInches * COUNTS_PER_INCH);
            LiftTargetR = HWA.LiftR.getCurrentPosition() + (int)(upInches * COUNTS_PER_INCH);

            HWA.LiftL.setTargetPosition(LiftTargetL);
            HWA.LiftR.setTargetPosition(LiftTargetR);

            HWA.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            HWA.LiftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            HWA.LiftL.setPower(Math.abs(LIFT_SPEED));
            HWA.LiftR.setPower(Math.abs(LIFT_SPEED));

            while (opModeIsActive() && (runtime.seconds() < 1.0) && (HWA.LiftL.isBusy() || HWA.LiftR.isBusy())) {
                telemetry.update();
            }

            HWA.LiftL.setPower(0);
            HWA.LiftR.setPower(0);

            HWA.LiftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            HWA.LiftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(500);
        }
    }

    //Function to bring down the Arm
    private void servoClose(){
        HWA.ServoR.setPosition(1);
        HWA.ServoL.setPosition(0);
        sleep(1800);
        HWA.ServoR.setPosition(0.5);
        HWA.ServoL.setPosition(0.5);
    }

    //Function to bring up the Arm
    private void servoOpen(){
        HWA.ServoR.setPosition(0);
        HWA.ServoL.setPosition(1);
        sleep(1800);
        HWA.ServoR.setPosition(0.5);
        HWA.ServoL.setPosition(0.5);
    }
}
