package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter {
    public CANEncoder shootEncoder;
    private CANPIDController shooterMotor_PIDcontroller;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public int countsPerRevolution;
    public CANSparkMax shooterMotor;
    public Shooter(int portNum) {
        shooterMotor = new CANSparkMax(portNum, MotorType.kBrushless);
        shootEncoder = shooterMotor.getEncoder();
        shootEncoder.getCountsPerRevolution();
        
        shooterMotor_PIDcontroller = shooterMotor.getPIDController();
        shooterMotor_PIDcontroller.setP(kP);
        shooterMotor_PIDcontroller.setI(kI);
        shooterMotor_PIDcontroller.setD(kD);
        shooterMotor_PIDcontroller.setIZone(kIz);
        shooterMotor_PIDcontroller.setFF(kFF);
        shooterMotor_PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);
        
        
    }


    public  void shoot(double velocity) {
        shooterMotor.set(velocity);
    }
}