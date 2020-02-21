/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Ports.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public DriveTrain driveTrain;
  private LambdaJoystick joystick1;
  public Shooter shooter;
  public Indexer indexer;
  public PickerUpper pickerUpper;
  public Climber climber;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = new DriveTrain(LEFT_DRIVETRAIN_1, LEFT_DRIVETRAIN_2, RIGHT_DRIVETAIN_1, RIGHT_DRIVETAIN_2, GYRO_PORT);
    climber = new Climber(RAISE_HOOK, WINCH_UP);
    pickerUpper = new PickerUpper(PICKERUPPER, DRAWBRIDGE);
    shooter = new Shooter(SHOOTER_PORT);
    joystick1 = new LambdaJoystick(0, driveTrain::updateSpeed);
    indexer = new Indexer(INDEX_MOTOR_1, INDEX_MOTOR_2, LINE_BREAKER_PORT);
    joystick1.addButton(2, pickerUpper::pickUp, pickerUpper::stopPickingUp);
    joystick1.addButton(1, () -> shooter.shoot(shooterVelocity));
    joystick1.addButton(3, climber::armUp, climber::stopClimbing);
    joystick1.addButton(4, climber::robotUp, climber::stopClimbing);
    joystick1.addButton(5, pickerUpper::lowerDrawBridge, pickerUpper::stopLowering);
    joystick1.addButton(6, pickerUpper::raiseDrawBridge, pickerUpper::stopRaising);
    joystick1.addButton(7, indexer::expell, indexer::stopExpelling);


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
