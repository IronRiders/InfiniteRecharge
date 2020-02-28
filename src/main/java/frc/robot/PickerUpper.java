package frc.robot;

//import com.revrobotics.CANEncoder;
//import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.ControlType;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class PickerUpper {
    private final CANSparkMax pickerUpperMotor;
    
   
    public PickerUpper(int pickerUpperPort) {
        pickerUpperMotor = new CANSparkMax(pickerUpperPort, MotorType.kBrushed);
    }
    public void senseTopLimitSwitch() {

    }

    
    public void pickUp() {
        pickerUpperMotor.set(.5);
        
    }
    public void stopPickingUp(){
        pickerUpperMotor.set(0);

    }

    
    

}