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
    armPWM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    armPWM.setSensorPhase(false); // is your sensor going pos or neg
    armPWM.setSelectedSensorPosition(0);
    // how many seconds to get to full speed
    //armPWM.configClosedloopRamp(0.5, 0);

    // TODO: set all these PID controls
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/PositionClosedLoop/src/main/java/frc/robot/Robot.java
    // 
		/* Config Position Closed Loop gains in slot0, typically kF stays zero. */
    armPWM.config_kF(RobotMap.Constants.kArmGains.kSlotIdx,
        RobotMap.Constants.kArmGains.kF, 
        RobotMap.Constants.kTimeoutMs);
    armPWM.config_kP(RobotMap.Constants.kArmGains.kSlotIdx,
        RobotMap.Constants.kArmGains.kP, 
        RobotMap.Constants.kTimeoutMs);
    armPWM.config_kI(RobotMap.Constants.kArmGains.kSlotIdx,
        RobotMap.Constants.kArmGains.kI, 
        RobotMap.Constants.kTimeoutMs);
    armPWM.config_kD(RobotMap.Constants.kArmGains.kSlotIdx,
        RobotMap.Constants.kArmGains.kD, 
        RobotMap.Constants.kTimeoutMs);
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    armPWM.set(ControlMode.PercentOutput, val * RobotMap.Constants.ARM_PWR);

    System.out.println("arm raw vals are: " + armPWM.getSelectedSensorPosition());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmControl());
  }
}
