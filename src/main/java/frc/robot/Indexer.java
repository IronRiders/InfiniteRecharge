package frc.robot;
//import com.fasterxml.jackson.core.util.DefaultIndenter;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Indexer {
    private final VictorSP index1;
    private final VictorSP index2;
    DigitalInput beamBreaker;
    boolean loadingIndexer = false;
    boolean feedingShooter = false;

    public Indexer(int port1, int port2, int port3) {
        index1 = new VictorSP(port1);
        index2 = new VictorSP(port2);
        //beamBreaker = new DigitalInput(port3);
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
    //This is the beam braker update method

   
    public void loadIndexer(){
        //loadingIndexer = true;
        index1.set(.3);
        index2.set(-.3);
    }

    public void feedShooter(){
        //feedingShooter = true;
        index1.set(.3);
        index2.set(-.3);

    }

    public void expell(){
        index1.set(-.7);
        index2.set(.7);
    }
    public void stopExpelling(){
        index2.set(0);
        index1.set(0);

    }
}
