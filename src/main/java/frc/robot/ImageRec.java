package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class ImageRec {
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
    
    public void update() {
        areaNTE.setDouble(area);
        centerXNTE.setDouble(centerX);
        centerYNTE.setDouble(centerY);
        widthNTE.setDouble(width);
        heightNTE.setDouble(height);
    }

    // TODO: everything else
}
