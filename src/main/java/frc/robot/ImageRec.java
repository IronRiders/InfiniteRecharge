package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImageRec {
    private NetworkTableEntry hasTargets;
    private NetworkTableEntry xAngleOffset;

    public ImageRec() {
        NetworkTableInstance tables = NetworkTableInstance.getDefault();
        hasTargets = tables.getTable("limelight").getEntry("tv");
        xAngleOffset = tables.getTable("limelight").getEntry("tx");
    }

    public void log() {
        boolean targets = hasTargets.getDouble(0) == 1;
        SmartDashboard.putBoolean("ImageRec/hasTargets", targets);
        SmartDashboard.putNumber("ImageRec/turnLeft", xAngleOffset.getDouble(0));
    }
}
