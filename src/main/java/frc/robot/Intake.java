package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    private CANSparkMax raiseMotor;
    private CANSparkMax intakeMotor;

    public Intake(int raisePort, int intakePort) {
        raiseMotor = new CANSparkMax(raisePort, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(intakePort, MotorType.kBrushless);
    }

    public void raise() {
        raiseMotor.set(0.5);
    }

    public void lower() {
        raiseMotor.set(-0.5);
    }

    public void suckIn() {
        intakeMotor.set(2);
    }

    public void spitOut() {
        intakeMotor.set(-2);
    }
}