package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter {
    private CANSparkMax shooter;

    public Shooter(int sparkPort) {
        shooter = new CANSparkMax(sparkPort, MotorType.kBrushless);
    }

    public void shoot() {
        shooter.set(.5);
    }

    public void stop() {
        shooter.set(0);
    }
}