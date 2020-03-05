package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    public ImageRec imageRec;

    @Override
    public void robotInit() {
        imageRec = new ImageRec();
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("ImageRec/distance", imageRec.getEstimatedDistance());
    }
}
