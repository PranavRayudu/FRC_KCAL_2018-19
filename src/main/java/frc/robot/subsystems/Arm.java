/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
  }

  public void setPwr(double val) {
    armPWM.set(ControlMode.PercentOutput, val);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmControl());
  }
}
