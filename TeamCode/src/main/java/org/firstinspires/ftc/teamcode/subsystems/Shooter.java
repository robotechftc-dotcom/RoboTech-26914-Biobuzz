package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kD;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kI;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kP;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kS;
import static org.firstinspires.ftc.teamcode.subsystems.Constantes.ShooterCostants.kV;


import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.easyfamily.EasyStack.control.EasyPIDCoefficients;
import dev.easyfamily.EasyStack.control.EasyPIDController;
import dev.easyfamily.EasyStack.feedforward.FFCoefficients;
import dev.easyfamily.EasyStack.feedforward.FFController;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotorType;


public class Shooter {
    private EasyMotor shooter; //Criamos objeto shooter da classe EasyMotor
    EasyPIDCoefficients pidCoefficients; //Importante: Recomendo declarar e inicializar construtores no construtor do proprio shooter. Erro meu:
    //Coloquei o nome de PIDFCoefficients e não de EasyPIDFCoefficients.
    //Utilizaremos apenas pid, sem o feedforward pois vamos utilizar a parte para um sistema mais
    //preciso e complexo.
    EasyPIDController controller; //Criamos um objeto da classe EasyPIDController.
    FFCoefficients ffCoefficients; //Igualmente criamos o feedforward coeficients: Podemos utilizar o PIDF, porém o F do pid é estático e não utiliza bateria como referência.
                                    //PESQUISEM COMO FUNCIONA UM FEEDFORWARD PARA ENTENDER MELHOR!
    FFController ffController; //criamos um controlador feedforward
    public Shooter(HardwareMap hardwareMap) {
        ffCoefficients = new FFCoefficients(kS, kV); //Criamos os coefficients utilizando da classe ShooterPIDF
        pidCoefficients = new EasyPIDCoefficients(kP, kI, kD); //Criamos os coefficients utilizando da classe ShooterPIDF
        ffController = new FFController(ffCoefficients);    //Lançamos constantes nos
        controller = new EasyPIDController(pidCoefficients);//controladores
        shooter = new EasyMotor(hardwareMap, "Shooter", EasyMotorType.GOBILDA_6000) //E agora sim declaramos o shooter
                .setDeadband(0.005) //colocamos um deadband um pouco mais baixo
                .setVelocityController(controller) //setamos o VELOCITY controller pid
                .setVelocityFF(ffController) //setamos o FF controller
                .stopAndResetEncoder(); //por segurança paramos e resetamos o encoder
    }
    public void setRPM(double rpm) {shooter.setRPM(rpm);}

    public double getRPM() {return shooter.getRPM();}
}//por fim terminamos. mas recomendo fortemente implementar outras funções se necessário aqui.



/**
 * Por fim, lembrem-se uma coisa importante que fiquei sabendo: PID SEMPRE DEVE SER TUNADO INDIVIDUALMENTE,
 * INDEPENDENTE DO SISTEMA, TUNEM. Cada sistema tem um valor único para cada pid. Pesquisem como tunar um pid e um feedforward, qualquer
 * dúvida. me mandem mensagem.
 * **/