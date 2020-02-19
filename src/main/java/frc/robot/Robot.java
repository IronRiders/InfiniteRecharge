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
    private LambdaJoystick joystick;
    private Shooter shooter;
    private Indexer indexer;

    @Override
    public void robotInit() {
        shooter = new Shooter(SHOOTER_PORT);
        indexer = new Indexer(INDEXER_MOTOR_1, INDEXER_MOTOR_2, 0.5);
        joystick.addButton(0, () -> shooter.shoot(3000), () -> shooter.stop());
        joystick.addButton(1, () -> indexer.feed(), () -> indexer.stop());
    }

    @Override
    public void teleopPeriodic() {
        joystick.listen();
    }
}
