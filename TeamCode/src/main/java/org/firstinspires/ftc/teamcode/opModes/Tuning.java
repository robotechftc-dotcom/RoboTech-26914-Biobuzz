package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kD;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kI;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kP;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kS;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kV;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import dev.easyfamily.EasyStack.gamepad.EasyGamepad;

@TeleOp
public class Tuning extends OpMode {
    Shooter Shooter;
    EasyGamepad pad;

    @Override
    public void init() {
        Shooter = new Shooter(hardwareMap);
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, false);
    }

    @Override
    public void loop() {
        pad.update();
        Shooter.setRPM(pad.bToggle() ? 3000 : 0);

        telemetry.addData("kP", kP);
        telemetry.addData("k", kI);
        telemetry.addData("k", kD);
        telemetry.addData("k", kS);
        telemetry.addData("k", kV);
        telemetry.addData("RPM shooter",Shooter.getRPM());
        telemetry.addData("power", Shooter.getPower());

        telemetry.update();

    }
}
