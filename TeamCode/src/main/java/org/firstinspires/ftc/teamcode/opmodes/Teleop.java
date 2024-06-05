package org.firstinspires.ftc.teamcode.opmodes;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODERS;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
@TeleOp(name = "Teleop", group = "Linear Opmode")

public class Teleop extends LinearOpMode {

    int desiredPosition = 3000;
    int downposition = 0;
    int get = 1000;
    int down = 0;
    int up = 900;
    private boolean isup= false;
    DcMotor arm;
    DcMotor slide;
    Servo clawposition;
    Servo clawopend;
    private static final double DEFAULT_POSITION = 0;
    SampleMecanumDrive drive;
    float speed = 1f;
    private void wait(int ms)
    {
        try{
            Thread.sleep(ms);
        } catch (InterruptedException ignored){
            //nu face nimic
        }
    }
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Gamepad1 Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad1 Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad1 Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Gamepad2 A Button", gamepad2.a);
        telemetry.addData("Gamepad2 B Button", gamepad2.b);
        telemetry.update();
        arm = hardwareMap.get(DcMotor.class, "arm");
        slide = hardwareMap.get(DcMotor.class, "slide");
        clawposition = hardwareMap.get(Servo.class, "clawposition1");
        clawopend = hardwareMap.get(Servo.class, "clawopend");
        //1150 rpm
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setTargetPosition(slide.getCurrentPosition());
        arm.setTargetPosition(arm.getCurrentPosition());
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(1);
        arm.setPower(0.5);


        drive = new SampleMecanumDrive(hardwareMap);
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * speed,
                            gamepad1.left_stick_x * speed,
                            gamepad1.right_stick_x
                    )
            );
            drive.update();
            //arm.setPower(-gamepad2.left_stick_y * speed);
            telemetry.addData("merge_arm", -gamepad2.left_stick_y);
            //  idle();
            if(gamepad2.a) // pozitia de colectare a pixelilor
            {
                slide.setTargetPosition(get);

                //clawposition.setPosition(0.70);// pozitia de unde ia pixelul
                //isup=true;
            }
            if(gamepad2.x){
                arm.setTargetPosition(up);
                //slide.setTargetPosition(desiredPosition);
                clawposition.setPosition(0.2); // pozitia asta o va avea doar cand bratul e sus pt board
                //isup=true;
            }
            else
            {
                arm.setTargetPosition(down);
                //slide.setTargetPosition(get);

                clawposition.setPosition(0);// pozitia de unde ia pixelul
                //isup=true;
            }
            if(gamepad2.y)
            {
                clawopend.setPosition(0.4);
            }
            else{
                clawopend.setPosition(-0.1);
            }
            if (gamepad2.b) {
                slide.setTargetPosition(desiredPosition);
                //clawposition.setPosition(-0.65); // pozitia asta o va avea doar cand bratul e sus pt board
            }
            if(gamepad2.left_bumper)
            {
                slide.setTargetPosition(downposition);
            }
        }
    }
}
