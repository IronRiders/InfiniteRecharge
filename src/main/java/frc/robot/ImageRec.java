package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;

public class ImageRec {
    private final double targetHeight = 2.4; // meters TODO is actually 2.5
    private final double cameraHeight = 1.1557; // meters TODO is not final, need camera mounted on robot
    private final double verticalFieldOfView = 0.75572756611; // radians
    private final double imageWidth = 1920; // pixels
    private final double imageHeight = 1080; // pixels

    private VisionThread visionThread;
    private final Object lock = new Object();
    private double area;
    private double centerX;
    private double centerY;
    private double rectWidth;
    private double rectHeight;

    public ImageRec() {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution((int) imageWidth, (int) imageHeight);

        visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
            if (!pipeline.filterContoursOutput().isEmpty()) {
                MatOfPoint contour = pipeline.filterContoursOutput().get(0);
                for (int i = 1; i < pipeline.filterContoursOutput().size(); ++i) {
                    if (Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)).area() > Imgproc.boundingRect(contour).area()) {
                        contour = pipeline.filterContoursOutput().get(i);
                    }
                }
                synchronized (lock) {
                    Rect bounds = Imgproc.boundingRect(contour);
                    area = bounds.area();
                    rectWidth = bounds.width;
                    rectHeight = bounds.height;
                    centerX = bounds.x + rectWidth / 2;
                    centerY = bounds.y + rectHeight / 2;
                }
            }
        });
        visionThread.start();
    }

    public void test() {
        SmartDashboard.putNumber("ImageRec/area", area);
        SmartDashboard.putNumber("ImageRec/centerX", centerX);
        SmartDashboard.putNumber("ImageRec/centerY", centerY);
        SmartDashboard.putNumber("ImageRec/width", rectWidth);
        SmartDashboard.putNumber("ImageRec/height", rectHeight);
        SmartDashboard.putNumber("ImageRec/distance", findDistance());
    }

    private double findDistance() {
        double h = targetHeight - cameraHeight;
        double y = imageHeight / 2 - (centerY - rectHeight / 2);
        return h / Math.tan(Math.asin(y / (imageHeight/2) * Math.sin(verticalFieldOfView / 2)));
    }

    // First element is horizontal angle offset, second element is release velocity
    public double[] aim() {
        return new double[]{};
    }
}
