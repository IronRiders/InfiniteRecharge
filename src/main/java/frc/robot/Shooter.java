package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter {
    public CANEncoder shootEncoder;
    private CANPIDController shooterMotor_PIDcontroller;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public int countsPerRevolution;
    public CANSparkMax shooterMotor;
    public double wheelRadius = 0.0508; // meters

    public Shooter(int portNum) {
        shooterMotor = new CANSparkMax(portNum, MotorType.kBrushless);
        shootEncoder = shooterMotor.getEncoder();
        countsPerRevolution = shootEncoder.getCountsPerRevolution();
        
        shooterMotor_PIDcontroller = shooterMotor.getPIDController();
        shooterMotor_PIDcontroller.setP(kP);
        shooterMotor_PIDcontroller.setI(kI);
        shooterMotor_PIDcontroller.setD(kD);
        shooterMotor_PIDcontroller.setIZone(kIz);
        shooterMotor_PIDcontroller.setFF(kFF);
        shooterMotor_PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void shoot(double velocity /* meters per second */) {
        double rpm = velocity * 60 / (wheelRadius * Math.PI * 2);
        shooterMotor_PIDcontroller.setReference(rpm, ControlType.kVelocity);
        // We could use an if else statement that if the ball count is 0, then call stop.
    }

    public void stop(){
        shooterMotor.set(0);
    }
}
