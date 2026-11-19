package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Limelight;

@TeleOp
public class testqualquerUseparatestaralgo extends OpMode {
    Limelight lime;
    @Override
    public void init() {
        lime = new Limelight(hardwareMap);
    }

    @Override
    public void loop() {
        lime.update();
        boolean b = lime.BlueHive();
        boolean r = lime.RedHive();
        telemetry.addData("TemTagVermelho?", r);
        telemetry.addData("TemTagAzul?", b);
        telemetry.update();
    }
}
