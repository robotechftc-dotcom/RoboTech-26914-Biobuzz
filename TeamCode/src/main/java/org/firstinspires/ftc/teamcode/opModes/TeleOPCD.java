package org.firstinspires.ftc.teamcode.opModes;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.RPMtest;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import dev.easyfamily.EasyStack.gamepad.EasyGamepad;

@TeleOp(name = "TeleOpPCD")
public class TeleOPCD extends OpMode {

    private Follower follower;
    private Intake intake;
    private EasyGamepad pad;
    private boolean backA = true, first = true;

    @Override
    public void init() {

        follower = Constants.create(hardwareMap);
        intake = new Intake(hardwareMap);
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, true).setToggle(EasyGamepad.Button.Y, true); // shooter começa ligado e o dumper começa para cima


    }

    @Override
    public void loop() {
        pad.update(); // obrigatório no início de todo loop

        follower.manual(-pad.leftStickY(), pad.leftStickX() , pad.rightStickX());


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

        telemetry.update();
        follower.update();
    }
}

