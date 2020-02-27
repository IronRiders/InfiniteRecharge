package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

public class  BallSpitter {

    private final SpeedController spitterWheel;
    private final double speed = 0.5;

    BallSpitter(final int spitterPort) {
        spitterWheel = new Spark(spitterPort);
  
    }

    public void suck() {
        spinWheels(speed);
    }

    public void spit() {
        spinWheels(-speed);
    }

    public void neutral() {
        spinWheels(0);
    }

    private void spinWheels(final double speed) {
        spitterWheel.set(speed);
      
    }


    public void updateSpeed(final LambdaJoystick.ThrottlePosition throttlePosition){
        final double left = throttlePosition.y - throttlePosition.x;
       

        spitterWheel.set(left);
       
    }
}