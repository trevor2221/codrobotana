package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "testmagda1")
public class Testmagda1 extends OpMode {
    public Servo clawopen;
    DcMotor mfl;
    DcMotor mbl;
    DcMotor mfr;
    DcMotor mbr;
    double newTarget;
    @Override
    public void loop() {
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        if (gamepad1.left_stick_x>0){
            mbl.setPower(x);
            mbr.setPower(x);
            mfl.setPower(x);
            mfr.setPower(x);
        }
        if (gamepad1.left_stick_y<0){
            mbl.setPower(-y);
            mbr.setPower(-y);
            mfl.setPower(-y);
            mfr.setPower(-y);
        }
        if (gamepad1.left_stick_x<0){
            mbl.setPower(-x);
            mbr.setPower(-x);
            mfl.setPower(-x);
            mfr.setPower(-x);
        }
        if (gamepad1.left_stick_y>0){
            mbl.setPower(y);
            mbr.setPower(y);
            mfl.setPower(y);
            mfr.setPower(y);
        }
        if (!gamepad1.y) {
            mbl.setPower(0);
            mbr.setPower(0);
            mfl.setPower(0);
            mfr.setPower(0);
        }
            if (!gamepad1.x) {
                mbl.setPower(0);
                mbr.setPower(0);
                mfl.setPower(0);
                mfr.setPower(0);
            }
            if(gamepad1.b){
                clawopen.setPosition(1);
            }
            clawopen.setPosition(0);
        }

    public void init() {
        mfl = hardwareMap.get(DcMotor.class, "frontleft");
        mbl = hardwareMap.get(DcMotor.class, "backleft");
        mfr = hardwareMap.get(DcMotor.class, "frontright");
        mbr = hardwareMap.get(DcMotor.class, "backright");
        mfl.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.addData("Hardware:","Initialized");
        clawopen = hardwareMap.get(Servo.class, "clawopen");
    }
}



