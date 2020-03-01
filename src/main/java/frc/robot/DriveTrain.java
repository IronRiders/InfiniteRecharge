package frc.robot;

//import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LambdaJoystick.ThrottlePosition;

public class DriveTrain {
    //private final AHRS gyro;

    private final CANSparkMax leftMotor1;
    private final CANSparkMax rightMotor1;
    private final CANSparkMax leftMotor2;
    private final CANSparkMax rightMotor2;
    private final boolean throttleMode = true;

    public int throttleDirectionConstant = 1;

    public DriveTrain(final int leftPort1, final int leftPort2, final int rightPort1, final int rightPort2, final int gyroPortNumber) {
        //gyro = new AHRS(); //TODO: Probs shouldn't be deafult constructor
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
        double scaledW = throttlePosition.w;
        double scaleFactorA = 0.3;
        double scaleFactorB = 0.7;
        // Top is X scale bottem is Y
        double scaleFactorC = 0.3;
        double scaleFactorD = 0.7;
        double throttle1 = scaledW * -1;
        double throttle2 = throttleMode ? ((throttle1 + 1.00) / 2.00) : 0.70;
        scaledY = (scaleFactorC * Math.abs(throttlePosition.y*throttleDirectionConstant*throttle2))
                + (scaleFactorD * throttlePosition.y * throttlePosition.y);
        scaledX = (scaleFactorA * Math.abs(throttlePosition.x))
                + (scaleFactorB * throttlePosition.x * throttlePosition.x);
        if (throttlePosition.x < 0)
            scaledX = -scaledX;
        if (throttlePosition.y < 0)
            scaledY = -scaledY;

       

        leftMotor1.set(-(scaledY - scaledX));
        rightMotor1.set(scaledY + scaledX);
    }

    public void setThrottleDirectionConstant(){
        throttleDirectionConstant=-1*throttleDirectionConstant;
    }

//     public void autoUpdateSpeed(double left, double right) {
        
//         leftMotor1.set(left);
//        rightMotor1.set(right);
//     }

	public CANSparkMax getLeftMotor() {
		return leftMotor1;
	}

	public CANSparkMax getRightMotor() {
		return rightMotor1;
	}

// 	//public AHRS getGyro() {
// 		return gyro;
// 	}
// }
}