package frc.robot;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Indexer {
    private final CANSparkMax index1;
    private final CANSparkMax index2;
    DigitalInput input;

    public Indexer(int port1, int port2, int port3){
        index1 = new CANSparkMax(port1, MotorType.kBrushless);
        index2 = new CANSparkMax(port2, MotorType.kBrushless);
        input = new DigitalInput(port3);
    }
    public Boolean detectBall() {
       // we don't know if true is no ball or true is ball
        boolean noBall = false; 
       if (input.get()){
            noBall = true;
                  
        }
        SmartDashboard.putBoolean ("indexer loaded", noBall);
        return noBall;
        
    }
    public void feed(){
        index1.set(.3);
        index2.set(-.3);
    }

    public void expell(){
        index1.set(-.3);
        index2.set(.3);
    }

    public void stopExpelling(){
        index1.set(0);
        index2.set(0);

    }
}
