package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImageRec {
    private NetworkTableEntry hasTargets;
    private NetworkTableEntry cameraTransform;

    public ImageRec() {
        NetworkTableInstance tables = NetworkTableInstance.getDefault();
        hasTargets = tables.getTable("limelight").getEntry("tv");
        cameraTransform = tables.getTable("limelight").getEntry("camtran");
    }

    public void test() {
        boolean targets = hasTargets.getDouble(0) == 1;
        SmartDashboard.putBoolean("ImageRec/hasTargets", targets);
        if (targets) {
            double[] transform = cameraTransform.getDoubleArray(new double[6]);
            SmartDashboard.putNumber("ImageRec/x", transform[0]);
            SmartDashboard.putNumber("ImageRec/y", transform[1]);
            SmartDashboard.putNumber("ImageRec/z", transform[2]);
            SmartDashboard.putNumber("ImageRec/pitch", transform[3]);
            SmartDashboard.putNumber("ImageRec/yaw", transform[4]);
            SmartDashboard.putNumber("ImageRec/roll", transform[5]);
            SmartDashboard.putNumber("ImageRec/distance", findDistance());
        }
    }

    private double findDistance() {
        double[] transform = cameraTransform.getDoubleArray(new double[6]);
        return Math.sqrt(transform[0] * transform[0] + transform[2] * transform[2]);
    }
}
