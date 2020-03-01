/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.*;

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
    private LambdaJoystick joystick2;
    public Shooter shooter;
    public Indexer indexer;
    public PickerUpper pickerUpper;
    public Climber climber;
    public DrawBridge drawBridge;
    public ImageRec imageRec;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        // CameraServer.getInstance().startAutomaticCapture(1);
        drawBridge = new DrawBridge(DRAWBRIDGE);
        driveTrain = new DriveTrain(LEFT_DRIVETRAIN_1, LEFT_DRIVETRAIN_2, RIGHT_DRIVETAIN_1, RIGHT_DRIVETAIN_2,
                GYRO_PORT);
        pickerUpper = new PickerUpper(PICKERUPPER);
        shooter = new Shooter(SHOOTER_PORT);
        joystick1 = new LambdaJoystick(0, driveTrain::updateSpeed);
        joystick1.addButton(1, driveTrain::setThrottleDirectionConstant);
        joystick2 = new LambdaJoystick(1);
        indexer = new Indexer(INDEX_MOTOR_1, INDEX_MOTOR_2, BEAMBREAKER);
        imageRec = new ImageRec();

        joystick2.addButton(2, shooter::shootReverse, shooter::stop);
        // joystick1.addButton(3, drawBridge::raisePickerUpper, drawBridge::stop);
        // MECHNICAL BROKEN
        // joystick1.addButton(4, drawBridge::lowerPickerUpper, drawBridge::stop);
        // joystick2.addButton(7, pickerUpper::reversePickUp,
        // pickerUpper::stopPickingUp);
        // joystick2.addButton(3, pickerUpper::pickUp, pickerUpper::stopPickingUp); -->
        // MECHANICAL BROKEN
        joystick2.addButton(1, shooter::shootWithOutPid, shooter::stop);
        joystick2.addButton(4, indexer::feedShooter, indexer::stopEverything);
        joystick2.addButton(8, indexer::expell, indexer::stopEverything);
        // joystick2.addButton(0, shooter::startShoot,shooter::stopShoot);
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
        double timer = DriverStation.getInstance().getMatchTime();
        if (timer < 13.0) {
            indexer.feedShooter();
        }
        if (timer < 10.00) {
            shooter.stop();
            driveTrain.getLeftMotor().set(-.1);
            driveTrain.getRightMotor().set(.1);
        } else {
            shooter.autoShoot();
        }
        if (timer < 7.00) {
            indexer.stopEverything();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopInit() {
        driveTrain.getLeftMotor().set(0);
        driveTrain.getRightMotor().set(0);
    }

    @Override
    public void teleopPeriodic() {
        // drawBridge.updateDrawBridge();
        // indexer.update();
        shooter.setSpeed(joystick2.getRawAxis(3));
        joystick2.listen();
        joystick1.listen();
        // shooter.updateIndexer();
        imageRec.log();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
