package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class ImageRec {
    private final double targetX = 0;
    private final double targetHeight = 100;
    private final double[] distanceTuning = new double[]{10, 0.1};
    private final double[] alignmentTuning = new double[]{0, 100};
    private final double[] powerTuning = new double[]{10, 1000};

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

    // First element is horizontal angle offset, second element is release velocity
    public double[] aim() {
        update();

        // Convert height difference to distance away from target
        // When testing put distance on the dashboard
        // If a linear term can't reliably compute the distance, add higher power terms
        double distanceFromTarget = (targetHeight - height) * distanceTuning[1] + distanceTuning[0];

        // Do the same for distance that we're off horizontally
        double horizontalAlignment = (targetX - centerX) * alignmentTuning[1] + alignmentTuning[0];

        // Use the same procedure for converting distance to power
        double shotPower = distanceFromTarget * powerTuning[1] + powerTuning[0];

        return new double[]{horizontalAlignment, shotPower};
    }
}
