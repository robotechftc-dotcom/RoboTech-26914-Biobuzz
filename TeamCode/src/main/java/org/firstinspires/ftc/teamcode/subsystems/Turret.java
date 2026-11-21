package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.easyfamily.EasyStack.controllable.EasyServo;
import dev.easyfamily.EasyStack.hardware.Servo.EasyServoGroup;

public class Turret {
    public EasyServo lft;
    public EasyServo rght;

    public Turret(HardwareMap hardwareMap) {
        lft = new EasyServo(hardwareMap, "lft", 120.0);
        rght = new EasyServo(hardwareMap, "rght", 120.0);
        EasyServoGroup tureti = new EasyServoGroup(lft, rght)
                .setLimits(0, 0.8)
                .addState("INIT", 0);
    }
}
