package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.RPMtest;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import dev.easyfamily.EasyStack.gamepad.EasyGamepad;

@TeleOp(name = "TeleOp")
public class teleOP extends OpMode {

    private Follower follower;
    private Intake intake;
    private Shooter shooter;
    private EasyGamepad pad;
    private boolean backA = true, first = true;


    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, true); // shooter começa ligado


    }

    @Override
    public void loop() {
        pad.update(); // obrigatório no início de todo loop

        follower.manual(-pad.leftStickY() * 0.3, -pad.leftStickX() * 0.3, pad.rightStickX() * 0.3);

        shooter.setRPM(pad.bToggle() ? RPMtest : 0);

        telemetry.addData("b is pressed", pad.bToggle());

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

        telemetry.addData("RPM shooter",shooter.getRPM());

        telemetry.update();
        follower.update();
    }
}