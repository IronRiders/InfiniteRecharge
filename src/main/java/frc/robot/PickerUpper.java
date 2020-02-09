package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PickerUpper{
    private final CANSparkMax pickerUpperMotor;
    private final CANSparkMax drawBridgeMotor;  // The motor that brings down the intake
    private CANPIDController drawBridge_PIDcontroller;
    private CANEncoder drawBridgeEncoder;
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public double drawBridgeCountsPerRevolution;
    public double drawBridgeWheelRadius = 0.2342;  // CHANGE ME!

    public PickerUpper (int pickerUpperPort, int drawBridgePort)  {
        pickerUpperMotor = new CANSparkMax(pickerUpperPort, MotorType.kBrushed);
        drawBridgeMotor = new CANSparkMax(drawBridgePort, MotorType.kBrushed);  // IS IT REALLY BRUSHED???

        drawBridgeMotor.setIdleMode(IdleMode.kCoast);
        // PID for DrawBridge
        drawBridgeEncoder = drawBridgeMotor.getEncoder();
        drawBridgeCountsPerRevolution = drawBridgeEncoder.getCountsPerRevolution();
        kP = .1;
        kI = .1;
        kD = 0;
        kFF = 0;
        kMaxOutput = 0.1;
        kMinOutput = 0.1;
        //maxRPM = 8000; 
        drawBridge_PIDcontroller = drawBridgeMotor.getPIDController();
        drawBridge_PIDcontroller.setP(kP);
        drawBridge_PIDcontroller.setI(kI);
        drawBridge_PIDcontroller.setD(kD);
        drawBridge_PIDcontroller.setIZone(kIz);
        drawBridge_PIDcontroller.setFF(kFF);
        drawBridge_PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);

        //puts PID numbers into smart dashboard so we can change them
        SmartDashboard.putNumber ("P Gain", 0);
        SmartDashboard.putNumber ("I  Gain", 0);
        SmartDashboard.putNumber ("D  Gain", 0);
        drawBridgeCountsPerRevolution = drawBridgeCountsPerRevolution*.25;

    }

    public void pickUp() {
        pickerUpperMotor.set(.5);


    }
    
    public void stopPickingUp() {
        pickerUpperMotor.set(0);

    }

    public void lowerDrawBridge() {
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("I Gain", 0);

        if((p != kP))  { drawBridge_PIDcontroller.setP(p); kP= p;}
        if((i != kI))  { drawBridge_PIDcontroller.setP(i); kI= i;}    
        if((d != kD))  { drawBridge_PIDcontroller.setP(d); kD= d;}
       
        drawBridge_PIDcontroller.setReference(drawBridgeCountsPerRevolution, ControlType.kPosition);
    }

    public void raiseDrawBridge() {
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("I Gain", 0);

        if((p != kP))  { drawBridge_PIDcontroller.setP(p); kP= p;}
        if((i != kI))  { drawBridge_PIDcontroller.setP(i); kI= i;}    
        if((d != kD))  { drawBridge_PIDcontroller.setP(d); kD= d;}
       
        drawBridge_PIDcontroller.setReference(-drawBridgeCountsPerRevolution, ControlType.kPosition);
    }

    public void stopLowering() {
        drawBridgeMotor.set(0);
        drawBridgeMotor.setIdleMode(IdleMode.kCoast);
    }

    public void stopRaising() {
        drawBridgeMotor.set(0);
        drawBridgeMotor.setIdleMode(IdleMode.kBrake);
    }
}