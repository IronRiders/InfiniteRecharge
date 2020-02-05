package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class PickerUpper{
    private final CANSparkMax pickerUpperMotor;

    public PickerUpper (int port)  {
        pickerUpperMotor = new CANSparkMax(port, MotorType.kBrushed);

    }

    public void pickUp() {
        pickerUpperMotor.set(.5);


    }
    
    public void stopPickingUp() {
        pickerUpperMotor.set(0);

    }
}