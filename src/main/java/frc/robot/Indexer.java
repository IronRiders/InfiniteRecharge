package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Indexer {
        private final CANSparkMax index1;
        private final CANSparkMax index2;

        public Indexer(int port1, int port2){
            index1 = new CANSparkMax(port1, MotorType.kBrushless);
            index2 = new CANSparkMax(port2, MotorType.kBrushless);
            
        }


        public void feed(){
            index1.set(.3);
            index2.set(-.3);

        }

        public void expell(){
            index1.set(-.3);
            index2.set(.3);
        }






}