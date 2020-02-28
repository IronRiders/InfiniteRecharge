package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
    public CANEncoder shootEncoder;
    private CANPIDController shooterMotor_PIDcontroller;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public int countsPerRevolution;
    public CANSparkMax shooterMotor;

    public double wheelRadius = 0.1016;
    //this RPM is a estamate from build.

    public Shooter(int portNum) {
        shooterMotor = new CANSparkMax(portNum, MotorType.kBrushed);
        shootEncoder = shooterMotor.getEncoder();
        countsPerRevolution = shootEncoder.getCountsPerRevolution();
        kP = .1;
        kI = .1;
        kD = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;
        //maxRPM = 8000; 
        shooterMotor_PIDcontroller = shooterMotor.getPIDController();
        shooterMotor_PIDcontroller.setP(kP);
        shooterMotor_PIDcontroller.setI(kI);
        shooterMotor_PIDcontroller.setD(kD);
        shooterMotor_PIDcontroller.setIZone(kIz);
        shooterMotor_PIDcontroller.setFF(kFF);
        shooterMotor_PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);
        
        //puts PID numbers into smart dashboard so we can change them
        SmartDashboard.putNumber ("P Gain", 0);
        SmartDashboard.putNumber ("I  Gain", 0);
        SmartDashboard.putNumber ("D  Gain", 0);
    }


    public  void shoot(double velocity) {
       //Updates the pid number from smart dashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("I Gain", 0);

        if((p != kP))  { shooterMotor_PIDcontroller.setP(p); kP= p;}
        if((i != kI))  { shooterMotor_PIDcontroller.setP(i); kI= i;}    
        if((d != kD))  { shooterMotor_PIDcontroller.setP(d); kD= d;}
        
        
        velocity = (velocity *countsPerRevolution)/ (2 *Math.PI *wheelRadius);
        shooterMotor_PIDcontroller.setReference(velocity, ControlType.kVelocity);
        /*
        We could use an if else statement that if the ball count is 0, then call stop.
        */

    }

    public void stop(){
        shooterMotor.set(0);
    }
}
