package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ImageRec {
    private final double limelightHeight = 1.92; // TODO feet
    private final double limelightPitch = 0; // TODO degrees
    private final double targetHeight = 1.5;//8.1875; // feet

    private NetworkTableEntry hasTargets;
    private NetworkTableEntry xAngleOffset;
    private NetworkTableEntry yAngleOffset;

    public ImageRec() {
        NetworkTableInstance tables = NetworkTableInstance.getDefault();
        hasTargets = tables.getTable("limelight").getEntry("tv");
        xAngleOffset = tables.getTable("limelight").getEntry("tx");
        yAngleOffset = tables.getTable("limelight").getEntry("ty");
    }

    public boolean getHasTarget() {
        return hasTargets.getDouble(0) == 1;
    }

    public double getHorizontalAngleOffset() {
        return xAngleOffset.getDouble(0);
    }

    public double getVerticalAngleOffset() {
        return yAngleOffset.getDouble(0);
    }

    public double getEstimatedDistance() {
        return (targetHeight - limelightHeight) / Math.tan(Math.toRadians(limelightPitch + getVerticalAngleOffset()));
    }
}
