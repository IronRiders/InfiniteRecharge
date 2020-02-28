package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.revrobotics.CANEncoder;
//import com.revrobotics.CANPIDController;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.ControlType;
//import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PickerUpper {
    private final VictorSPX pickerUpperMotor;
    
   
    public PickerUpper(int pickerUpperPort) {
        pickerUpperMotor = new VictorSPX(pickerUpperPort);
    }
    public void senseTopLimitSwitch() {

    }

    
    public void pickUp() {
        pickerUpperMotor.set(ControlMode.PercentOutput, -.5);
        
    }
    public void stopPickingUp(){
        pickerUpperMotor.set(ControlMode.PercentOutput ,0);

    }

    
    

}