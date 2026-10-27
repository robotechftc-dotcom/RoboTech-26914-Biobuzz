package org.firstinspires.ftc.teamcode.subsystems.Constantes;

import com.acmerobotics.dashboard.config.Config;

//Meninos, atenção a -> organização de código!! <-
//Criamos uma classe onde abrange nossas constantes. Assim é até mais fácil de configurar no dashboard, utilizando anotação
@Config

public class ShooterCostants {
    public static double kD = 0.0;
    public static double kI = 0;
    public static double kP = 0.00016;
    public static double kS = 0.045;
    public static double kV = 0.00015;

    public static double RPMtest = 3200;
}
