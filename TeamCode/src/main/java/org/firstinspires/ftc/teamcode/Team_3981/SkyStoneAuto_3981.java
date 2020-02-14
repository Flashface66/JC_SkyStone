package org.firstinspires.ftc.teamcode.Team_3981;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.List;

import static org.openftc.easyopencv.OpenCvInternalCamera.*;
import static org.openftc.easyopencv.OpenCvInternalCamera.CameraDirection.*;


/**
 * Created by maryjaneb  on 11/13/2016.
 *
 * nerverest ticks
 * 60 1680
 * 40 1120
 * 20 560
 *
 * monitor: 640 x 480
 *YES
 */
@Autonomous(name= "Autonomous", group="Sky autonomous")
public class SkyStoneAuto_3981 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //0 means skystone, 1 means yellow stone
    //-1 for debug, but we can keep it like this because if it works, it should change to either 0 or 255
    private static int valMid = -1;
    private static int valLeft = -1;
    private static int valRight = -1;

    private static float rectHeight = 1f/8f;
    private static float rectWidth = 2f/8f;

    private static float offsetX = 0f/8f;//changing this moves the three rects and the three circles left or right, range : (-2, 2) not inclusive
    private static float offsetY = 2f/8f;//changing this moves the three rects and circles up or down, range: (-4, 4) not inclusive

    private static float[] midPos = {4f/8f+offsetX, 4f/8f+offsetY};//0 = col, 1 = row
    private static float[] leftPos = {2f/8f+offsetX, 4f/8f+offsetY};
    private static float[] rightPos = {6f/8f+offsetX, 4f/8f+offsetY};
    //moves all rectangles right or left by amount. units are in ratio to monitor

    private final int rows = 1280;
    private final int cols = 960;

    OpenCvCamera phoneCam;


    private Hardware_Test RB = new Hardware_Test();   // Use a Pushbot's hardware

    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    private static final double     COUNTS_PER_MOTOR_HEX    = 288;
    private static final double     DRIVE_GEAR_REDUCTION    = 0.67;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     CIRCUMFRENCE_INCHES     = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) ;
    private static final double     DRIVE_SPEED             = 0.3;
    private static final double     TURN_SPEED              = 0.4;


    @Override
    public void runOpMode() throws InterruptedException {


        RB.init(hardwareMap);



        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                RB.Left.getCurrentPosition(),
                RB.Right.getCurrentPosition());
        telemetry.update();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        phoneCam.openCameraDevice();//open camera
        phoneCam.setPipeline(new StageSwitchingPipeline());//different stages
        phoneCam.startStreaming(rows, cols, OpenCvCameraRotation.SIDEWAYS_LEFT);//display on RC




        //width, height
        //width = height in this case, because camera is in portrait mode.

        waitForStart();
        runtime.reset();

        if (valLeft == 0 && valMid == 0 && valRight == 0  ) {
           RB.Right.setPower(-0.3);
            RB.Left.setPower(-0.3);
        }

        while (opModeIsActive()) {
            telemetry.addData("Values", valLeft+"   "+valMid+"   "+valRight);
            telemetry.addData("Height", rows);
            telemetry.addData("Width", cols);

            if (valLeft == 0 && valMid == 0 && valRight == 0  ) {
                telemetry.addData("SkyStone","NONE");
            }
            if(valLeft == 0 && valMid == 255 && valRight == 255){
                telemetry.addData("SkyStone","LEFT");
            }
            if(valLeft == 255 && valMid == 0 && valRight == 255){
                telemetry.addData("SkyStone","MIDDLE");
            }
            if(valLeft == 255 && valMid == 255 && valRight == 0){
                telemetry.addData("SkyStone","RIGHT");
            }

            telemetry.update();
            //sleep(100);


            //call movement functions
//            strafe(0.4, 200);
//            moveDistance(0.4, 700);

        }
    }




    public void encoderDrive(double speed,
                             double leftInches, double rightInches
    ) {
        double rightRotationsNeeded =rightInches/ CIRCUMFRENCE_INCHES;
        double leftRotationsNeeded =leftInches/ CIRCUMFRENCE_INCHES;
        int newLeftTarget= (int)(leftRotationsNeeded*COUNTS_PER_INCH);
        int newRightTarget= (int)(rightRotationsNeeded*COUNTS_PER_INCH);



        // Determine new target position, and pass to motor controller
        //newLeftTarget =  (int)((leftInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_INCH);
        //newRightTarget =  (int)((rightInches/CIRCUMFRENCE_INCHES) * COUNTS_PER_INCH);

        RB.Left.setTargetPosition(newLeftTarget);
        RB.Right.setTargetPosition(newRightTarget);

        // reset the timeout time and start motion.

        RB.Left.setPower(-speed);
        RB.Right.setPower(speed);

        // Turn On RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);





        while ((RB.Left.isBusy() || RB.Right.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    RB.Left.getCurrentPosition(),
                    RB.Right.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        RB.Left.setPower(0);
        RB.Right.setPower(0);

        // Turn off RUN_TO_POSITION
        RB.Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sleep(100);   // optional pause after each move

    }

    public void lift(double speed,
                     double Inches){
        //double rotationsNeeded =Inches;
        int target = (int)(Inches*COUNTS_PER_MOTOR_HEX);
        RB.Claw1.setTargetPosition(target);
        RB.Claw2.setTargetPosition(target);
        RB.Claw1.setPower(speed);
        RB.Claw2.setPower(speed);
        RB.Claw1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.Claw2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (
                (RB.Claw1.isBusy() || RB.Claw2.isBusy())) {

            // Display it for the driver.
            telemetry.addData("End",  "Running to %7d :%7d, %7d",  RB.Claw2.getCurrentPosition(),
                    RB.Claw1.getCurrentPosition(), target);

            telemetry.update();
        }

        RB.Claw1.setPower(0);
        RB.Claw2.setPower(0);

    }

    public void Clamp(long time,double pos){
        RB.Rotate2.setPosition(pos);
        sleep(time);
    }

    //detection pipeline
    static class StageSwitchingPipeline extends OpenCvPipeline
    {
        Mat yCbCrChan2Mat = new Mat();
        Mat thresholdMat = new Mat();
        Mat all = new Mat();
        List<MatOfPoint> contoursList = new ArrayList<>();

        enum Stage
        {//color difference. greyscale
            detection,//includes outlines
            THRESHOLD,//b&w
            RAW_IMAGE,//displays raw view
        }

        private Stage stageToRenderToViewport = Stage.detection;
        private Stage[] stages = Stage.values();

        @Override
        public void onViewportTapped()
        {
            /*
             * Note that this method is invoked from the UI thread
             * so whatever we do here, we must do quickly.
             */

            int currentStageNum = stageToRenderToViewport.ordinal();

            int nextStageNum = currentStageNum + 1;

            if(nextStageNum >= stages.length)
            {
                nextStageNum = 0;
            }

            stageToRenderToViewport = stages[nextStageNum];
        }

        @Override
        public Mat processFrame(Mat input)
        {
            contoursList.clear();
            /*
             * This pipeline finds the contours of yellow blobs such as the Gold Mineral
             * from the Rover Ruckus game.
             */

            //color diff cb.
            //lower cb = more blue = skystone = white
            //higher cb = less blue = yellow stone = grey
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);//converts rgb to ycrcb
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2);//takes cb difference and stores

            //b&w
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 102, 255, Imgproc.THRESH_BINARY_INV);

            //outline/contour
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            yCbCrChan2Mat.copyTo(all);//copies mat object
            //Imgproc.drawContours(all, contoursList, -1, new Scalar(255, 0, 0), 3, 8);//draws blue contours


            //get values from frame
            double[] pixMid = thresholdMat.get((int)(input.rows()* midPos[1]), (int)(input.cols()* midPos[0]));//gets value at circle
            valMid = (int)pixMid[0];

            double[] pixLeft = thresholdMat.get((int)(input.rows()* leftPos[1]), (int)(input.cols()* leftPos[0]));//gets value at circle
            valLeft = (int)pixLeft[0];

            double[] pixRight = thresholdMat.get((int)(input.rows()* rightPos[1]), (int)(input.cols()* rightPos[0]));//gets value at circle
            valRight = (int)pixRight[0];

            //create three points
            Point pointMid = new Point((int)(input.cols()* midPos[0]), (int)(input.rows()* midPos[1]));
            Point pointLeft = new Point((int)(input.cols()* leftPos[0]), (int)(input.rows()* leftPos[1]));
            Point pointRight = new Point((int)(input.cols()* rightPos[0]), (int)(input.rows()* rightPos[1]));

            //draw circles on those points
            Imgproc.circle(all, pointMid,5, new Scalar( 255, 0, 0 ),1 );//draws circle
            Imgproc.circle(all, pointLeft,5, new Scalar( 255, 0, 0 ),1 );//draws circle
            Imgproc.circle(all, pointRight,5, new Scalar( 255, 0, 0 ),1 );//draws circle

            //draw 3 rectangles
            Imgproc.rectangle(//1-3
                    all,
                    new Point(
                            input.cols()*(leftPos[0]-rectWidth/2),
                            input.rows()*(leftPos[1]-rectHeight/2)),
                    new Point(
                            input.cols()*(leftPos[0]+rectWidth/2),
                            input.rows()*(leftPos[1]+rectHeight/2)),
                    new Scalar(0, 255, 0), 3);
            Imgproc.rectangle(//3-5
                    all,
                    new Point(
                            input.cols()*(midPos[0]-rectWidth/2),
                            input.rows()*(midPos[1]-rectHeight/2)),
                    new Point(
                            input.cols()*(midPos[0]+rectWidth/2),
                            input.rows()*(midPos[1]+rectHeight/2)),
                    new Scalar(0, 255, 0), 3);
            Imgproc.rectangle(//5-7
                    all,
                    new Point(
                            input.cols()*(rightPos[0]-rectWidth/2),
                            input.rows()*(rightPos[1]-rectHeight/2)),
                    new Point(
                            input.cols()*(rightPos[0]+rectWidth/2),
                            input.rows()*(rightPos[1]+rectHeight/2)),
                    new Scalar(0, 255, 0), 3);

            switch (stageToRenderToViewport)
            {
                case THRESHOLD:
                {
                    return thresholdMat;
                }

                case detection:
                {
                    return all;
                }

                case RAW_IMAGE:
                {
                    return input;
                }

                default:
                {
                    return input;
                }
            }
        }

    }
}