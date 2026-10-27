package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kD;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kI;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kP;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kS;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kV;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.RPMtest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import dev.easyfamily.EasyStack.gamepad.EasyGamepad;

@TeleOp
public class Tuning extends OpMode {
    private boolean backA = true, first = true;
    private Intake intake;

    private Shooter shooter;
    private EasyGamepad pad;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, true);
    }

    @Override
    public void loop() {
        pad.update();

        double targetRPM = pad.bToggle() ? RPMtest : 0;
        shooter.setRPM(targetRPM);

        if (pad.x()) {
            if (backA) intake.setAllPower(1);
            else { intake.setBackPower(0); intake.setInPower(1); }
        } else {
            intake.setInPower(pad.leftTrigger());
            intake.setBackPower(pad.rightTrigger());
        }

        intake.setTravaOpen(!pad.a());
        if (pad.a()) { intake.setAllPower(1); backA = true; }

        intake.setDumperOpen(pad.yToggle());

        if (!first && intake.getAmp() > 5) backA = false;
        first = false;

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