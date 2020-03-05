package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

//import com.fasterxml.jackson.core.util.DefaultIndenter;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DigitalSource;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Indexer {
    private final VictorSPX index1;
    private final VictorSPX index2;
    DigitalInput beamBreaker;
    boolean loadingIndexer = false;
    boolean feedingShooter = false;

    public Indexer(int port1, int port2, int port3) {
        index1 = new VictorSPX(port1);
        index2 = new VictorSPX(port2);
        beamBreaker = new DigitalInput(port3);
    }

    // public void update() {

    //     if (loadingIndexer) {
    //         if (!beamBreaker.get()) {
    //             index1.set(0);
    //             index2.set(0);
    //             loadingIndexer = false;
    //         }
    //     }

    //     if(feedingShooter){
    //         if(beamBreaker.get()){
    //             //index1.set(0);
    //             //index2.set(0);
    //             feedingShooter = false;
    //             loadIndexer();
    //         }
    //     }
    // }

   
    public void loadIndexer(){
        loadingIndexer = true;
        index1.set(ControlMode.PercentOutput, .3);
        index2.set(ControlMode.PercentOutput, -.3);
    }

    public void feedShooter(){
        feedingShooter = true;
        index1.set(ControlMode.PercentOutput, -.3);
        index2.set(ControlMode.PercentOutput, .3);
//These are the correct values for the indexer
    }

    public void expell(){
        index1.set(ControlMode.PercentOutput, .2);
        index2.set(ControlMode.PercentOutput, -.2);
    }
    public void stopEverything(){
        index2.set(ControlMode.PercentOutput, 0);
        index1.set(ControlMode.PercentOutput, 0);

    }
}
