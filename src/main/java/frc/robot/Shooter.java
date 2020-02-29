package frc.robot;

//import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LambdaJoystick;
import frc.robot.LambdaJoystick.ThrottlePosition;
public class Shooter {
    //private CANEncoder shootEncoder;
    private CANPIDController shooterMotor_PIDcontroller;
    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;// maxRPM;
    //private int countsPerRevolution;
    private CANSparkMax shooterMotor;
    private double speed;
    //private double wheelRadius = 0.1016;
    //this RPM is a estamate from build.

    public Shooter(int portNum) {
        shooterMotor = new CANSparkMax(portNum, MotorType.kBrushless);
        //shootEncoder = shooterMotor.getEncoder();
        //countsPerRevolution = shootEncoder.getCountsPerRevolution();
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


    public  void shoot(double rpm) {
       //Updates the pid number from smart dashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("I Gain", 0);

        if((p != kP))  { shooterMotor_PIDcontroller.setP(p); kP= p;}
        if((i != kI))  { shooterMotor_PIDcontroller.setP(i); kI= i;}    
        if((d != kD))  { shooterMotor_PIDcontroller.setP(d); kD= d;}
        
        shooterMotor_PIDcontroller.setReference(rpm, ControlType.kVelocity);
        /*
        We could use an if else statement that if the ball count is 0, then call stop.
        */

    }

    public void shootWithOutPid(){
        double w = this.speed;
        w = (.5* w +.5)*-1;
        shooterMotor.set(w);

    }

    public void setSpeed(double speed){
       this.speed = speed;
        System.out.println(speed);
    }
    public void stop(){
        shooterMotor.set(0);
    }
}
