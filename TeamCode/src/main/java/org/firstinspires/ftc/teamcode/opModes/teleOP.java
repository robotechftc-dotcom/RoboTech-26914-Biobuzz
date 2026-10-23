package org.firstinspires.ftc.teamcode.opModes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
//commit
@TeleOp(name = "TeleOp")
public class teleOP extends OpMode {

    private Follower follower;
    private Intake Inteiki;
    private Shooter shuter;
    private Boolean Test = true;


    @Override
    public void init() {

        follower = Constants.create(hardwareMap);
        Inteiki = new Intake(hardwareMap, gamepad1);
        shuter = new Shooter(hardwareMap);
    }

    @Override
    public void loop() {
        follower.manual(
                -gamepad1.left_stick_y * 0.3,
                -gamepad1.left_stick_x * 0.3,
                gamepad1.right_stick_x * 0.3
        );
        if(gamepad1.circleWasPressed()) {
            if (Test) {
                Test = false;
            } else {
                Test = true;
            }
        }
        if(Test) {
            shuter.ShooterRPM(3000.0);
        } else {
            shuter.ShooterRPM(0.0);
        }
        Inteiki.teleop();

        telemetry.addData("Motor 1 current", Inteiki.getmAhINT1());
        telemetry.addData("Motor 2 current", Inteiki.getmAhINT2());

        telemetry.update();
        follower.update();

    }
}