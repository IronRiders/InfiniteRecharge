/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import static frc.robot.Ports.*;


public class Robot extends TimedRobot {
  public DriveTrain driveTrain;
  public BallSpitter ballSpitter;
  private LambdaJoystick joystick1;
  private LambdaJoystick joystick2;
  

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain(4,3,2, 1, 0);
    ballSpitter = new BallSpitter(5);



  }

  @Override
  public void robotPeriodic() {
    
   
    joystick1.addButton(1, driveTrain::setThrottleDirectionConstant);
    joystick1.addButton(3, ballSpitter::spit, ballSpitter::neutral);
    joystick1.addButton(2, ballSpitter::suck, ballSpitter::neutral);
  }


  @Override
  public void autonomousInit() {


  }

  
  @Override
  public void autonomousPeriodic() {
  }
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
