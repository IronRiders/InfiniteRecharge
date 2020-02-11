package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;

public class ImageRec {
    private final double verticalFieldOfView = 0.75572756611; // radians
    private final double imageWidth = 1920; // pixels
    private final double imageHeight = 1080; // pixels

    private NetworkTableEntry areaNTE;
    private NetworkTableEntry centerXNTE;
    private NetworkTableEntry centerYNTE;
    private NetworkTableEntry widthNTE;
    private NetworkTableEntry heightNTE;

    private double area;
    private double centerX;
    private double centerY;
    private double width;
    private double height;

    public ImageRec() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("GRIP/gripHighGoalRec");
        areaNTE = table.getEntry("area");
        centerXNTE = table.getEntry("centerX");
        centerYNTE = table.getEntry("centerY");
        widthNTE = table.getEntry("width");
        heightNTE = table.getEntry("height");
    }
    
    private void update() {
        areaNTE.setDouble(area);
        centerXNTE.setDouble(centerX);
        centerYNTE.setDouble(centerY);
        widthNTE.setDouble(width);
        heightNTE.setDouble(height);
    }

    public void test() {
        update();

        SmartDashboard.putNumber("ImageRec/centerX", centerX);
        SmartDashboard.putNumber("ImageRec/centerY", centerY);
        SmartDashboard.putNumber("ImageRec/width", width);
        SmartDashboard.putNumber("ImageRec/height", height);
    }

    // First element is horizontal angle offset, second element is release velocity
    public double[] aim() {
        update();

        // math
        // double x = h / (Math.tan((verticalFieldOfView / imageHeight) * (y - imageHeight / 2)));
        // x = distance to target, h = height from camera height to middle of target in whatever unit (feet, meters), y = center y from grip
        // credit to eric, joey, issac

        return new double[]{};
    }
}
