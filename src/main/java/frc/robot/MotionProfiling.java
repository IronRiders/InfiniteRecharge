/*
package frc.robot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;

// import jaci.pathfinder.*;
// import jaci.pathfinder.followers.EncoderFollower;
// import jaci.pathfinder.modifiers.TankModifier;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MotionProfiling {
    private DriveTrain driveTrain;
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;
    public final DifferentialDriveKinematics kinematics;
    public DifferentialDriveOdometry odometry;
    public Trajectory trajectoryLeft;
    private double left;
    private double right;

    //Needs to be updated:
    private final double wheelBaseWidth = 0.5334; // meters  
    private final double wheelDiameter = 0.1524; //meters
    //# from last year private final int encoderTicksPerRevolution = 4096;
    private final double maxVelocity = 12; //ft/s


    public MotionProfiling(DriveTrain driveTrain, Path setupLeft , Path setupRight) throws IOException {
        this.driveTrain = driveTrain;
        leftMotor = driveTrain.getLeftMotor();
        rightMotor = driveTrain.getRightMotor();
        //pathweaver has an error with mixing up left and right
        trajectoryLeft = TrajectoryUtil.fromPathweaverJson(setupLeft);
        //Trajectory trajectoryRight = TrajectoryUtil.fromPathweaverJson(setupRight);

        kinematics = new DifferentialDriveKinematics(0.5334);
        
        RamseteController controller = new RamseteController();
        odometry = new DifferentialDriveOdometry(new Rotation2d(driveTrain.getGyro().getAngle())); //Need to add pose for robot starting  location

        ChassisSpeeds adjustedSpeeds = controller.calculate(odometry.getPoseMeters(), trajectoryLeft.sample(0));
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(adjustedSpeeds);
        left = wheelSpeeds.leftMetersPerSecond;
        right = wheelSpeeds.rightMetersPerSecond; //m/s val

        // left.configureEncoder(leftMotor.getSelectedSensorPosition(), encoderTicksPerRevolution, wheelDiameter); 
        // right.configureEncoder(rightMotor.getSelectedSensorPosition(), encoderTicksPerRevolution, wheelDiameter);

        // left.configurePIDVA(0.9, 0.0, 0.0, 1 / maxVelocity, 0); //Filler PID vals
        // right.configurePIDVA(0.9, 0.0, 0.0, 1 / maxVelocity, 0);
    }   

    public void update() {
        driveTrain.autoUpdateSpeed(left, right);
        odometry.update(new Rotation2d(driveTrain.getGyro().getAngle()), trajectoryLeft.sample(trajectoryLeft.getTotalTimeSeconds()).poseMeters, rightDistanceMeters);
    }

    // public void update() { 
    //     double l = left.calculate(leftMotor.getSelectedSensorPosition()); <--- Calculates % output (-1, 1)
    //     double r = right.calculate(rightMotor.getSelectedSensorPosition());
    //     double gyroHeading = driveTrain.getGyro().getAngleY();   // Assuming the gyro is giving a value in degrees
    //     double desiredHeading = -Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    //     double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    //     double turn = 0.8 * (-1.0/80.0) * angleDifference;

    //     driveTrain.autoUpdateSpeed(l + turn, r - turn);
    // }

    public boolean isFinished() {
        if (left.isFinished() && right.isFinished()) {
            return true;
        } else {
            return false;       
        }      
    }

    public void reset(){
        left.reset();
        right.reset();
        left.configureEncoder(leftMotor.getSelectedSensorPosition(), encoderTicksPerRevolution, wheelDiameter); 
        right.configureEncoder(rightMotor.getSelectedSensorPosition(), encoderTicksPerRevolution, wheelDiameter);
    }
}
*/