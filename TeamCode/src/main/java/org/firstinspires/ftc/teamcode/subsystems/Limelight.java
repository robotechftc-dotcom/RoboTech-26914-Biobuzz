package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import dev.easyfamily.EasyStack.Camera.EasyLimelight;

public class Limelight {
    public EasyLimelight lime;

    public Limelight(HardwareMap hardwareMap) {
        lime = new EasyLimelight(hardwareMap, "limelight")
                .setMount(10, 10, DistanceUnit.INCH)
                .setPipeline(1);
        lime.start();
    }
    public void update() {lime.update();}

    public boolean RedHive() {
        for(int i = 30; i<38;) {
            i++;
            if(lime.hasTag(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean BlueHive() {
        for(int i = 38; i<46;) {
            i++;
            if(lime.hasTag(i)) {
                return true;
            }
        }
        return false;
    }
}
