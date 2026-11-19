package org.firstinspires.ftc.teamcode.subsystems.Constantes;

import com.acmerobotics.dashboard.config.Config;

import dev.easyfamily.EasyStack.dashboard.EasyDash;
import dev.easyfamily.EasyStack.dashboard.EasyTunable;

//Meninos, atenção a -> organização de código!! <-
//Criamos uma classe onde abrange nossas constantes. Assim é até mais fácil de configurar no dashboard, utilizando anotação
@Config
public class ShooterCostants {
    @EasyTunable(min=0, max=0) public static double kF = 0.000;
    @EasyTunable(min=0, max=0) public static double kD = 0.0;
    @EasyTunable(min=0, max=0) public static double kI = 0;
    @EasyTunable(min=0, max=1) public static double kP = 0.0001;
    @EasyTunable(min=0, max=1) public static double kS = 0.08;
    @EasyTunable(min=0, max=1) public static double kV = 0.00018;
    @EasyTunable(min=0, max=6000) public static double RPMtest = 3000;
}