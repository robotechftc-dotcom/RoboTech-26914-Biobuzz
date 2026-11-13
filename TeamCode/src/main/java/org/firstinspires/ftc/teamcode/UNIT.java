package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative OpMode") // define que o robô vai ser controlado por um controle e o nome da programação
public class UNIT extends OpMode //classe
{
    //cria as variáveis dos motores
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    @Override// o override é sempre necessário nessas classes, pois elas já têm informações
    public void init(){//init define o que vai rodar uma vez quando o robô ser iniciado
        //exibe uma mensagem no driver hub
        telemetry.addData("Status", "Initialized");
        //identifica como o motor está definido no control hub para usá-lo
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        //fala para qual direção o motor deve girar
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void init_loop() {// init_loob define o que vai rodar em loop quando o robô ser iniciado
    }

    @Override
    public void start() {// init_loob define o que vai rodar uma vez quando for pressionado start
    }

    @Override
    public void loop() {// init_loob define o que vai rodar em loop quando for pressionado start
        //declaração das variáveis
        double leftPower;
        double rightPower;
        //variáveis para representar botões do gamepad (controle)
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        //energia que vai ser mandada para os motores se os botões forem precionados
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
        //definição que a energia definida logo acima vai ser mandada para os motores
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        //print falando o quanto de energia os motores estão recebendo
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    @Override
    public void stop() {//stop define o que o robô vai rodar quando o botão de stop for pressionado
    }

}