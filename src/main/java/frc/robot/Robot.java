package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    public ImageRec imageRec;

    @Override
    public void robotInit() {
        imageRec = new ImageRec();
    }

    @Override
    public void robotPeriodic() {
        imageRec.test();
    }
}
