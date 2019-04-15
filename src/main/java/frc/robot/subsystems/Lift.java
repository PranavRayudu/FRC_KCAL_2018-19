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
  
  private DigitalInput topLimit;
  private DigitalInput bottomLimit;
  
  private TalonSRX rightLiftPWM;
  private TalonSRX leftLiftPWM;

  private boolean enablePID = true;

  private int heightState = 0;

  public Lift() {

    topLimit = new DigitalInput(RobotMap.Sensors.LIFT_SWTICH_UP);
    bottomLimit = new DigitalInput(RobotMap.Sensors.LIFT_SWTICH_DOWN);

    rightLiftPWM = new TalonSRX(RobotMap.Motors.RIGHT_LIFT);
    leftLiftPWM = new TalonSRX(RobotMap.Motors.LEFT_LIFT);

    rightLiftPWM.setNeutralMode(NeutralMode.Brake);
    leftLiftPWM.setNeutralMode(NeutralMode.Brake);
    
    rightLiftPWM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rightLiftPWM.setSensorPhase(false);
    zeroOutEncoder();
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    double in = val;

    // if(topLimit.get()) {
    //   in = Math.max(in, 0);
    //   OI.logitechF310.setRumble(RumbleType.kLeftRumble, 1.0);
    // }

    // if(bottomLimit.get()) {
    //   in = Math.min(in, 0);
    //   zeroOutEncoder();
    //   OI.logitechF310.setRumble(RumbleType.kLeftRumble, 1.0);
    // }

    rightLiftPWM.set(ControlMode.PercentOutput, -in);
    leftLiftPWM.set(ControlMode.PercentOutput, in);
  }

  // public void setLiftLevel(int lv) {
  //   switch(lv) {
  //     case 0: 
  //   }
  // }

  // public void usePID() {

  // }

  public void zeroOutEncoder() {
    rightLiftPWM.setSelectedSensorPosition(0);
  }

  public double getEncoder() {
    return rightLiftPWM.getSelectedSensorPosition();
  }

  public boolean bottomedOut() {
    return bottomLimit.get();
  }
  
  public boolean toppedOut() {
    return topLimit.get();
  }

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
