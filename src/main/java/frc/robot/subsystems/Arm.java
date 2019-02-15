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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Arm.ArmControl;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  
  TalonSRX armPWM;

  public Arm() {
    armPWM = new TalonSRX(RobotMap.Motors.ARM_MOTOR_PWM);

    //armPWM.setNeutralMode(NeutralMode.Coast);
    //armPWM.neutralOutput();
    armPWM.setSensorPhase(false); // is your sensor going pos or neg
    
    // uncomment only if limit switches are added to arm
    // put limit switch into talon port using standard stuff
    // armPWM.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    // armPWM.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);

    // how many seconds to get to full speed
    //armPWM.configClosedloopRamp(0.5, 0); 

    // TODO: set all these PID controls
    // Feast your eyes on 
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/PositionClosedLoop/src/main/java/frc/robot/Robot.java
    // 
		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
// 		_talon.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
// 		_talon.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
// 		_talon.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
//    _talon.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    //armPWM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    armPWM.set(ControlMode.PercentOutput, val * RobotMap.Constants.ARM_PWR);
    //} else { // configure PID controls before this
      //armPWM.set(ControlMode.Position, armPWM.getSelectedSensorPosition());
    //}
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmControl());
  }
}
