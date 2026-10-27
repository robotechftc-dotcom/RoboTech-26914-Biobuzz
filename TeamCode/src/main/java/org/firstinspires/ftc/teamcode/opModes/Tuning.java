package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kD;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kI;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kP;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kS;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kV;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.RPMtest;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import dev.easyfamily.EasyStack.gamepad.EasyGamepad;

@TeleOp
public class Tuning extends OpMode {

    private Shooter shooter;
    private EasyGamepad pad;

    @Override
    public void init() {
        shooter = new Shooter(hardwareMap);
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, false);
    }

    @Override
    public void loop() {
        pad.update();

        double targetRPM = pad.bToggle() ? RPMtest : 0;
        shooter.setRPM(targetRPM);

        double currentRPM = shooter.getRPM();

        telemetry.addData("Target RPM", targetRPM);
        telemetry.addData("Current RPM", currentRPM);
        telemetry.addData("Error RPM", targetRPM - currentRPM);
        telemetry.addData("Power", shooter.getPower());

        telemetry.addData("kP", kP);
        telemetry.addData("kI", kI);
        telemetry.addData("kD", kD);
        telemetry.addData("kS", kS);
        telemetry.addData("kV", kV);

        telemetry.update();
    }
}