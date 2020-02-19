package frc.robot;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter {
    private CANPIDController shooterMotor_PIDcontroller;
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    private CANSparkMax shooterMotor;

    public Shooter(int portNum) {
        shooterMotor = new CANSparkMax(portNum, MotorType.kBrushless);
        
        shooterMotor_PIDcontroller = shooterMotor.getPIDController();
        shooterMotor_PIDcontroller.setP(kP);
        shooterMotor_PIDcontroller.setI(kI);
        shooterMotor_PIDcontroller.setD(kD);
        shooterMotor_PIDcontroller.setIZone(kIz);
        shooterMotor_PIDcontroller.setFF(kFF);
        shooterMotor_PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void shoot(double rpm) {
        shooterMotor_PIDcontroller.setReference(rpm, ControlType.kVelocity);
        // We could use an if else statement that if the ball count is 0, then call stop.
    }

    public void stop(){
        shooterMotor_PIDcontroller.setReference(0, ControlType.kVelocity);
    }
}