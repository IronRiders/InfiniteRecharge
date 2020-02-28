package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LambdaJoystick.ThrottlePosition;

public class DriveTrain {
    private final CANSparkMax leftMotor1;
    private final CANSparkMax rightMotor1;
    private final CANSparkMax leftMotor2;
    private final CANSparkMax rightMotor2;
    public double autoValue;//the hell does this do?
    private boolean drivingOffSpeed;
    public int throttleDirectionConstant = 1;
    private boolean throttleForward = true;
    public boolean masteralarm = false;
    public boolean velocityNeverToExcede = false;
    public boolean revrSpeedWarn = false;
    public double throttleInput;
    public boolean RvsThrottleWarn;
    public boolean velocityToTurn;
    public boolean stopDriveMotors;
    public double VelocityCheck;
    public double speedbrake;
    public boolean braketoggler = true;
    boolean rushing = false;
    public boolean masterSafteyOff = true;
    public double angleGoal;
    private boolean throttleMode = true;
   
    public boolean rightflare = true;


    public DriveTrain(final int leftPort1, final int leftPort2, final int rightPort1, final int rightPort2,
            final int gyroPortNumber) {
        leftMotor1 = new CANSparkMax(leftPort1, MotorType.kBrushless);
        leftMotor2 = new CANSparkMax(leftPort2, MotorType.kBrushless);
        rightMotor1 = new CANSparkMax(rightPort1, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(rightPort2, MotorType.kBrushless);

        leftMotor1.setIdleMode(IdleMode.kBrake);
        leftMotor2.setIdleMode(IdleMode.kBrake);
        rightMotor1.setIdleMode(IdleMode.kBrake);
        rightMotor2.setIdleMode(IdleMode.kBrake);
        leftMotor2.follow(leftMotor1);
        rightMotor2.follow(rightMotor1);
        
    }

    public void updateSpeed(final ThrottlePosition throttlePosition) {
        double scaledX = throttlePosition.x;
        double scaledY = throttlePosition.y;
        double scaledZ = throttlePosition.w;
        double scaleFactorA = 0.3;
        double scaleFactorB = 0.7;
        // Top is X scale bottem is Y
        double scaleFactorC = 0.3;
        double scaleFactorD = 0.7;
        scaledY = (scaleFactorC * Math.abs(throttlePosition.y))
                + (scaleFactorD * throttlePosition.y * throttlePosition.y);
        scaledX = (scaleFactorA * Math.abs(throttlePosition.x))
                + (scaleFactorB * throttlePosition.x * throttlePosition.x);
        if (throttlePosition.x < 0)
            scaledX = -scaledX;
        if (throttlePosition.y < 0)
            scaledY = -scaledY;

        leftMotor1.set(scaledY + scaledX);
        rightMotor1.set(scaledY - scaledX);

        autoValue = scaledY;
        if (throttlePosition.x < 0) {
            scaledX = -scaledX;
        }

        if (throttlePosition.y < 0) {
            scaledY = -scaledY;
        }

        double throttle1 = scaledZ * -1.00; // isaac helped fix the broken code (ishan messed up the sig figs)
        // double throttle1 = 1.00;
        double throttle2 = throttleMode ? ((throttle1 + 1.00) / 2.00) : 0.70; // Throttle as a value between 1
                                                                                        // and 2
        double throttle3 = throttle2 * 100.00;
        double thrust1 = (java.lang.Math.abs((throttlePosition.y * 1.00) * throttle3)); // Thrust as a value between 1
                                                                                        // and 100

        /*
         * in theory creates a value double trust which gives a value between 0 and 1
         * for the y input and should Give proportion thrust out put when throtle is
         * enabled)
         */

        // velocityNeverToExcede = (thrust1 >= 85.00) ? true : false;
        // velocityToTurn = (thrust1 > 20.00) ? true : false;
        // masteralarm = (throttle3 <= 20.00)
        //         || ((velocityNeverToExcede == true) || ((revrSpeedWarn == true) && (RvsThrottleWarn == true)));
        // RvsThrottleWarn = ((throttleForward == false) && (throttleMode == true)) ? /* (thrust1 >= 60.00)? */ (true)
        //         : (false);
        // revrSpeedWarn = ((throttle3 >= 55.00) && (throttleForward == false) ? (revrSpeedWarn = true)
        //         : (revrSpeedWarn = false));
        // SmartDashboard.putBoolean("Alarms/RvsOverSpeed", revrSpeedWarn);
        // SmartDashboard.putBoolean("Alarms/masteralarm", masteralarm);
        // SmartDashboard.putNumber("status/throttlePrime", throttle3);
        // SmartDashboard.putBoolean("Alarms/RvsThrottleWarn",RvsThrottleWarn);
        // SmartDashboard.putNumber("status/thrust", thrust1);
        // SmartDashboard.putNumber("raw data/Xraw", throttlePosition.x);
        // SmartDashboard.putNumber("raw data/Yraw", throttlePosition.y);
        // SmartDashboard.putNumber("raw data/Zraw", throttlePosition.z);
        // SmartDashboard.putBoolean("Alarms/VNE", velocityNeverToExcede);
        // SmartDashboard.putBoolean("Alarms/V1", velocityToTurn);
        //SmartDashboard.putBoolean("status/RobotArmed", masterSafteyOff);
        // SmartDashboard.putBoolean("BrakesIndicator",Brakes);
        // SmartDashboard.putNumber
        // VelocityCheck = (Brakes == true)?(speedbrake):throttle2;
        SmartDashboard.putNumber("Troubleshoot/Xraw", throttlePosition.x);
        SmartDashboard.putNumber("Troubleshoot/Yraw", throttlePosition.y);
        SmartDashboard.putNumber("Troubleshoot/Zraw", throttlePosition.w);
        SmartDashboard.putNumber("Troubleshoot/XScaled", scaledX);
        SmartDashboard.putNumber("Troubleshoot/YScaled", scaledY);
        SmartDashboard.putNumber("Troubleshoot/ZScaled", scaledZ);
        

        SmartDashboard.putBoolean("Alarms/VNE", velocityNeverToExcede);

        scaledX = (scaledX * 0.5 * (stopDriveMotors==false ? (throttle2) : 0.00));
        scaledY = scaledY * throttleDirectionConstant * (stopDriveMotors ==false ? (throttle2) : 0.00);
        

        final double right = ((-scaledX - scaledY) * -1);// +throttlePosition.z; //why plus throttle z?
        final double left = (scaledY - scaledX) * -1;

        leftMotor1.set(left);
        leftMotor2.follow(leftMotor1);
        rightMotor1.set(right);
        rightMotor2.follow(rightMotor1);

        SmartDashboard.putNumber("Troubleshoot/leftMotors", left);
        SmartDashboard.putNumber("Troubleshoot/rightMotors", right);
    }

    // it is now safe to touch stuff

    public void autoUpdateSpeed(double left, double right) {
        leftMotor1.set(left);
        rightMotor1.set(right);
        leftMotor2.follow(leftMotor1);
        rightMotor2.follow(rightMotor1);

    }
       

    public CANSparkMax getLeftMotor() {
        return leftMotor1;
    }

    public CANSparkMax getRightMotor() {
        return rightMotor1;
    }

    public void togglethrottleMode() {
        throttleMode = !throttleMode;
        SmartDashboard.putBoolean("status/LowSpeed", throttleMode);
    }

    public void cruiseControl() {
        autoUpdateSpeed(0.9, -0.9);
    }

    public void setThrottleDirectionConstant() {
        throttleDirectionConstant *= -1;
        throttleForward = !throttleForward;
        SmartDashboard.putBoolean("status/foward", throttleForward);
    }

    public void rightFlareEngager() {
        throttleDirectionConstant *= -1;
        throttleForward = !throttleForward;
        //rightMotor1 *= -1;
        rightflare = !rightflare;
        SmartDashboard.putBoolean("status/foward", throttleForward);
    }

    public void stopDriveMotors() {
      stopDriveMotors=true;

    }
    public void restartDriveMotors(){
      stopDriveMotors=false;
    }

    public void setDrivingOffSpeed() {
        drivingOffSpeed = !drivingOffSpeed;
    }

    public void updateRightSpeed() {
        rightMotor1.set( 0.5);
        rightMotor2.follow(rightMotor1);
    }

    public void stopRightSpeed() {
        rightMotor1.set(0.0);
        rightMotor2.follow(rightMotor1);
    }

    public void updateLeftSpeed() {
        leftMotor1.set( 0.5);
        leftMotor2.follow(leftMotor1);
    }

    public void stopLeftSpeed() {
        leftMotor1.set(0.0);
        leftMotor2.follow(leftMotor1);
    }

    int leftControlCount = 0;
    public void leftControl() {
        if (leftControlCount % 3 == 0) {
            updateLeftSpeed();
        } else if (leftControlCount % 3 == 1) {
            stopLeftSpeed();
        } else {}
        leftControlCount++;
    }

    int rightControlCount = 0;
    public void rightControl() {
        if (rightControlCount % 3 == 0) {
            updateRightSpeed();
        } else if (rightControlCount % 3 == 1) {
            stopRightSpeed();
        } else {
        }
    }
   

    public void startRush() {
        reset();
        rushing = true;
    }

    private void reset() {
    }

    public void endRush() {
        rushing = false;
    }

    {
        leftControlCount++;
    }


}
