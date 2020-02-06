package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class FreeSolo {
    //in encoder tix
    public final double distanceUp;
    public final double distanceDown;
    private final CANSparkMax climbUpMotor;
    private final CANSparkMax climbDownMotor;
    private final CANEncoder freeSoloEncoderUp;
    private final CANEncoder freeSoloEncoderDown;
    private CANPIDController pidUp;
    private CANPIDController  pidDown;
    public double kPup, kIup,kDup, kIzup, kFFup, kMaxOutputUp, kMinOutputUp, maxRPMup;
    public double kPDown, kIDown,kDDown, kIzDown, kFFDown, kMaxOutputDown, kMinOutputDown, maxRPMDown;
    public double radiusOfSpool = 0;
    public int tixPerRevolutionDown;
    public int tixPerRevolutionUp;

    public FreeSolo(int port1, int port2){
        climbUpMotor = new CANSparkMax(port1, MotorType.kBrushed);
        climbDownMotor = new CANSparkMax(port2, MotorType.kBrushed);
        freeSoloEncoderUp = climbUpMotor.getEncoder();
        freeSoloEncoderDown = climbDownMotor.getEncoder();
        pidUp = climbUpMotor.getPIDController();
        pidDown = climbDownMotor.getPIDController();
        int tixPerRevolutionUp = freeSoloEncoderUp.getCountsPerRevolution();
        int tixPerRevolutionDown =freeSoloEncoderDown.getCountsPerRevolution();
        distanceDown = 0;
        distanceUp = 0;
        climbUpMotor.setIdleMode(IdleMode.kBrake);
        climbDownMotor.setIdleMode(IdleMode.kBrake);
        kPup = 0;
        kIup = 0;
        kDup = 0;
        kIzup = 0;
        kFFup = 0;
        kMaxOutputUp = 1;
        kMinOutputUp = -1;

        kPDown = 0;
        kIDown = 0;
        kDDown = 0;
        kIzDown = 0;
        kFFDown = 0;
        kMaxOutputDown = 1;
        kMinOutputDown = -1;
        pidUp.setP(kPup);
        pidUp.setI(kIup);
        pidUp.setD(kDup);
        pidUp.setIZone(kIzup);
        pidUp.setFF(kFFup);
        pidUp.setOutputRange(kMinOutputUp, kMaxOutputUp);
        pidUp.setP(kPDown);
        pidUp.setI(kIDown);
        pidUp.setD(kDDown);
        pidUp.setIZone(kIzDown);
        pidUp.setFF(kFFDown);
        pidUp.setOutputRange(kMinOutputDown, kMaxOutputDown);
    }

    public void raiseClimber (double requestedDistanceUp){
        double tixUp = 0;
        tixUp = (requestedDistanceUp*tixPerRevolutionUp /(2*Math.PI*radiusOfSpool));
        pidUp.setReference(distanceUp, ControlType.kPosition);


    }

    public void lowerClimber(double requestedDistanceDown){
        double tixDown = 0;
        tixDown = (requestedDistanceDown*tixPerRevolutionDown /(2*Math.PI*radiusOfSpool));
        pidUp.setReference(distanceDown, ControlType.kPosition);


    }






}