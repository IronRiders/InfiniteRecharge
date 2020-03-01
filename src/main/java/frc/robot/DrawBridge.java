package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DrawBridge {
    private final TalonSRX drawBridgeMotor;

    public DrawBridge(int motorPort) {
        drawBridgeMotor = new TalonSRX(motorPort);
        drawBridgeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void lowerPickerUpper() {
        drawBridgeMotor.set(ControlMode.PercentOutput, .7);

    }

    public void raisePickerUpper() {
        drawBridgeMotor.set(ControlMode.PercentOutput, -.7);

    }

    public void stop() {
        drawBridgeMotor.set(ControlMode.PercentOutput, 0);

    }

}
// public class DrawBridge {
// private final TalonSRX drawBridgeMotor; // The motor that brings down the
// intake
// DigitalInput topLimitSwitch;
// DigitalInput bottomLimitSwitch;
// int directionMoving = 0;
// // -1 is down, 0 is not moving 1 is moving up
// public DrawBridge(int drawBridgePort, int bottom, int top){
// drawBridgeMotor = new TalonSRX(drawBridgePort); // IS IT REALLY BRUSHED???
// topLimitSwitch = new DigitalInput(top);
// bottomLimitSwitch = new DigitalInput(bottom);
// drawBridgeMotor.setNeutralMode(NeutralMode.Coast);
// }

// public void updateDrawBridge() {
// if (directionMoving == 1) {
// if (topLimitSwitch.get()) {
// drawBridgeMotor.set(TalonSRXControlMode.PercentOutput, 0);
// directionMoving = 0;
// }
// }
// else if (directionMoving == -1) {
// if (bottomLimitSwitch.get()) {
// drawBridgeMotor.set(TalonSRXControlMode.PercentOutput, 0);
// directionMoving = 0;
// }

// }

// }

// public void lowerDrawBridge() {
// drawBridgeMotor.set(TalonSRXControlMode.PercentOutput, -.2);
// directionMoving = -1;
// }

// public void raiseDrawBridge() {
// drawBridgeMotor.set(TalonSRXControlMode.PercentOutput, .2);
// directionMoving = 1;
// }

// }