package org.firstinspires.ftc.teamcode.opModes;

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
        // Usamos o easygamepad, utilizando a função set toogle, onde o b já vai ser true, então o shooter vai estar ligado, se pressar b, recebe falso. podem alterar isso se quiser.
        pad = new EasyGamepad(gamepad1).setToggle(EasyGamepad.Button.B, true);
    }

    @Override
    public void loop() {
        pad.update();
        follower.manual(-pad.leftStickY() * 0.3, -pad.leftStickX() * 0.3, pad.rightStickX() * 0.3);
        //recomendo utilizarem o ftcdashboard para setar o rpm manualmente.
        shooter.ShooterRPM(pad.bToggle() ? 3000 : 0);
        //toda a cadeia de função desordeenada agora de uma forma condesada e simples.
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

        if (!first && intake.getCurrentINT2() > 5) backA = false;
        first = false;
        intake.getTelemetry(telemetry);
        telemetry.update();
        follower.update();
    }
}