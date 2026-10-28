package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kD;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kF;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kI;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kP;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kS;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kV;


import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.easyfamily.EasyStack.control.EasyPIDFCoefficients;
import dev.easyfamily.EasyStack.control.EasyPIDFController;
import dev.easyfamily.EasyStack.feedforward.FFCoefficients;
import dev.easyfamily.EasyStack.feedforward.FFController;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotorGroup;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotorType;


public class Shooter {
    private EasyMotor shooter, shooter2;
    private EasyMotorGroup shooterGroup;

    private EasyPIDFController controller;
    private EasyPIDFCoefficients pidfCoefficients;
    private FFCoefficients ffCoefficients;
    private FFController ffController;

    public Shooter(HardwareMap hardwareMap) {
        ffCoefficients = new FFCoefficients(kS, kV);
        pidfCoefficients = new EasyPIDFCoefficients(kP, kI, kD, kF);
        ffController = new FFController(ffCoefficients);
        controller = new EasyPIDFController(pidfCoefficients);

        shooter = new EasyMotor(hardwareMap, "Shooter", EasyMotorType.GOBILDA_6000);
        shooter2 = new EasyMotor(hardwareMap, "Shooter2", EasyMotorType.GOBILDA_6000).reverse();

        shooterGroup = new EasyMotorGroup(shooter, shooter2)
                .setLeaderMotor(0)
                .crossCoupled()
                .setDeadband(0.005)
                .setVelocityPIDF(pidfCoefficients)
                .setVelocityFF(ffController)
                .stopAndResetEncoder();
    }

    public void setRPM(double rpm) { shooterGroup.setRPM(rpm); }
    public double getRPM() { return shooterGroup.getRPM(); }
    public double getPower() { return shooterGroup.getPower(); }
}


//por fim terminamos. mas recomendo fortemente implementar outras funções se necessário aqui.



/**
 * Por fim, lembrem-se uma coisa importante que fiquei sabendo: PID SEMPRE DEVE SER TUNADO INDIVIDUALMENTE,
 * INDEPENDENTE DO SISTEMA, TUNEM. Cada sistema tem um valor único para cada pid. Pesquisem como tunar um pid e um feedforward, qualquer
 * dúvida. me mandem mensagem.
 * **/