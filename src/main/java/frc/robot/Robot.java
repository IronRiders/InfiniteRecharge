/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import static frc.robot.Ports.*;

import java.io.IOException;
import java.nio.file.Path;

public class Robot extends TimedRobot {
  public DriveTrain driveTrain;
  public BallSpitter ballSpitter;
  private LambdaJoystick joystick1;
  private LambdaJoystick joystick2;
  

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain(4,3,2, 1, 0);
    ballSpitter = new BallSpitter(SPITTERPORT);



  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
   
    joystick1.addButton(1, driveTrain::setThrottleDirectionConstant);
    joystick1.addButton(3, ballSpitter::spit, ballSpitter::neutral);
    joystick1.addButton(2, ballSpitter::suck, ballSpitter::neutral);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    String trajectoryJSON = "paths/YourPath.wpilib.json";
try {
  Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
  Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
} catch (IOException ex) {
  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
}

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    joystick1.listen();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
