package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotorGroup;
import dev.easyfamily.EasyStack.hardware.Servo.EasyServo;


//commit
public class Intake{
    /**DcMotorEx INT1; //apagamos o dcmotorex e declaramos os easy motors
    DcMotorEx INT2;
     **/

    EasyMotor motorIntake1, motorIntake2;
    EasyMotorGroup Intake; //Declaramos o agrupamento desses motores.
    /**Servo Trava;

    Servo Dumper;
     **/
    EasyServo Dumper; //Igualmente declaramos servos da nossa lib, porém sem agrupamento, já que são subsistemas totalmente diferentes e independentes.
    EasyServo Blocker;
    public Intake(HardwareMap hardwareMap) {
        motorIntake1 = new EasyMotor(hardwareMap, "INT1"); //declaramos os motores individuais
        motorIntake2 = new EasyMotor(hardwareMap, "INT2");
        Intake = new EasyMotorGroup(motorIntake1, motorIntake2).reverse(); //vi que estavam -1, então declaramos todos como reverso.
        Blocker = new EasyServo(hardwareMap, "INTTrava")
                .addState("Aberto", 1) //adicionamos estados abertos e fechados
                .addState("Fechado", 0);
        Dumper = new EasyServo(hardwareMap, "Dumperr")
                .addState("Aberto", 1)
                .addState("Fechado", 0);
    }
    //retirei TODAS funções que utilizavam o gamepad. Sim, eu usava na decode mas descobri que é uma má prática.
    //colocamos funções com nomes literais (setters e getters) e sem entradas.
    public void setAllPower(double vel) {
        Intake.setPower(1);
    }
    public void setInPower(double vel) {motorIntake1.setPower(vel);}
    public void setBackPower(double vel) {motorIntake2.setPower(vel);}

    public void setTravaOpen(boolean est){
        Blocker.setState(est ? "Aberto" : "Fechado");
    } //recomendo depois saberem usar desta forma: se est é verdadeiro, o estado é aberto, se não, fechado. menor e mais fácil de entender

    public void setDumperOpen(boolean est) {
        Dumper.setState(est ? "Aberto" : "Fechado");
    }
    public double getAmp(){
        return Intake.getCurrentAmps();
    }
    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Intake amp", Intake.getCurrentAmps());
        telemetry.update();
    }
}