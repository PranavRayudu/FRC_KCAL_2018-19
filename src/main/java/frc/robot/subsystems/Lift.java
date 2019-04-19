/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Lift.LiftControl;

public class Lift extends Subsystem {
  
  private TalonSRX rightLiftPWM;
  private TalonSRX leftLiftPWM;

  //private DigitalInput bottomSwitch;

  private boolean reverse;
  private boolean enablePID;

  public Lift() {

    //bottomSwitch = new DigitalInput(RobotMap.Sensors.LIFT_SWTICH_BOTTOM);

    rightLiftPWM = new TalonSRX(RobotMap.Motors.RIGHT_LIFT);
    leftLiftPWM = new TalonSRX(RobotMap.Motors.LEFT_LIFT);
    
    enablePID = RobotMap.Config.LIFT_PID_ENABLED;
    reverse = RobotMap.Config.LIFT_REVERSED;

    rightLiftPWM.setNeutralMode(NeutralMode.Brake);
    leftLiftPWM.setNeutralMode(NeutralMode.Brake);

    rightLiftPWM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    rightLiftPWM.setSensorPhase(!reverse);
    
    rightLiftPWM.setInverted(reverse);
    leftLiftPWM.setInverted(reverse);

    zeroOutEncoder();
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    double in = val;

    // if(bottomedOut()) {
    //   in = Math.max(0, in);
    // }

    if(getEncoderRaw() > RobotMap.Constants.Lift.highLimit) {
      in = Math.min(0, in);
    }

    rightLiftPWM.set(ControlMode.PercentOutput, -in);
    leftLiftPWM.set(ControlMode.PercentOutput, in);
  }

  public void zeroOutEncoder() {
    rightLiftPWM.setSelectedSensorPosition(0);
  }

  public double getEncoderRaw() {
    return rightLiftPWM.getSelectedSensorPosition();
  }

  // public boolean bottomedOut() {
  //   return bottomSwitch.get();
  // }

  public void setPIDEnabled(boolean state) {
    this.enablePID = state;
  }

  public void togglePIDEnabled(){
    setPIDEnabled(!enablePID);
  }

  public boolean getPIDenabled() {
    return enablePID;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftControl());
  }
}
