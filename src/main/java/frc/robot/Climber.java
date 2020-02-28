package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber {
    public CANSparkMax armUpMotor;
    public CANSparkMax robotUpMotor;

    public Climber(int Port1, int Port2){
        armUpMotor = new CANSparkMax(Port1, MotorType.kBrushed);
        robotUpMotor = new CANSparkMax(Port2, MotorType.kBrushed);
        robotUpMotor.setIdleMode(IdleMode.kBrake);
        armUpMotor.setIdleMode(IdleMode.kBrake);
    
    }

    public void armUp(){
        // climber up
        armUpMotor.set(.1);
        
    }
    public void armDown(){
        armUpMotor.set(-.1);
    }
    public void robotUp(){
        // robot up
        robotUpMotor.set(-.1);
    }

    public void stopEverything(){
        armUpMotor.set(0);
        robotUpMotor.set(0); 
        // armUpMotor.setIdleMode(IdleMode.kBrake);
        // robotUpMotor.setIdleMode(IdleMode.kBrake);
    }
    
}
