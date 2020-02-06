package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber {
    public CANSparkMax pullUpMotor;
    public CANSparkMax pullDownMotor;

    public Climber(int Port1, int Port2){
        pullUpMotor = new CANSparkMax(Port1, MotorType.kBrushed);
        pullDownMotor = new CANSparkMax(Port2, MotorType.kBrushed);
        pullDownMotor.setIdleMode(IdleMode.kBrake);
        pullUpMotor.setIdleMode(IdleMode.kBrake);
    
    }

    public void pullUp(){
        pullUpMotor.set(.1);
        
    }

    public void pullDown(){
        pullDownMotor.set(-.1);

    }

    public void stopClimbing(){

        pullDownMotor.set(0);
        pullUpMotor.set(0);
    }
    
}