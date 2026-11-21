package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Limelight;

import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;

@TeleOp
public class testqualquerUseparatestaralgo extends OpMode {
    EasyMotor m0;
    EasyMotor m1;
    EasyMotor m2;
    EasyMotor m3;
    @Override
    public void init() {
        m0 = new EasyMotor(hardwareMap, "m0");
        m1 = new EasyMotor(hardwareMap, "m1");
        m2 = new EasyMotor(hardwareMap, "m2");
        m3 = new EasyMotor(hardwareMap, "m3");

    }

    @Override
    public void loop() {
        if (gamepad1.a){
            m0.setPower(0.5);
        }
        if (gamepad1.b){
            m1.setPower(0.5);
        }
        if (gamepad1.y){
            m2.setPower(0.5);
        }
        if (gamepad1.x){
            m3.setPower(0.5);
        }


    }
}
