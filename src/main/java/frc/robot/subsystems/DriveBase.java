/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveBase extends Subsystem {

  private Victor leftMotors;
  private Victor rightMotors;

  public DriveBase() {
    leftMotors = new Victor(RobotMap.leftMotorPWM);
    rightMotors = new Victor(RobotMap.rightMotorPWM);
  }

  public void setRaw(double leftVal, double rightVal) {
    leftMotors.set(leftVal);
    rightMotors.set(rightVal);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
  }
}
