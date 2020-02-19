package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {
    private final CANSparkMax index1;
    private final CANSparkMax index2;
    private double speed;

    public Indexer(int port1, int port2, double speed){
        index1 = new CANSparkMax(port1, MotorType.kBrushless);
        index2 = new CANSparkMax(port2, MotorType.kBrushless);
        this.speed = speed;
    }

    public void feed(){
        index1.set(speed);
        index2.set(-speed);
    }

    public void stop(){
        index1.set(0);
        index2.set(0);
    }
}
