/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveBase extends Subsystem {

  private PWMVictorSPX leftPWM;
  private PWMVictorSPX righVictorSPX;
  private DifferentialDrive drive;

  public DriveBase() {
    
    leftPWM = new PWMVictorSPX(RobotMap.leftMotorPWM);
    righVictorSPX = new PWMVictorSPX(RobotMap.rightMotorPWM);

    drive = new DifferentialDrive(leftPWM, righVictorSPX);
  }

  public void setRaw(double leftVal, double rightVal) {
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
  }
}
