/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;

public class DriveControl extends Command {
  public DriveControl() {
    requires(Robot.driveBase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // double rightVal = OI.joystick.rightStickY();
    // double lefttVal = OI.joystick.leftStickY();
    // Robot.driveBase.setRaw(rightVal, lefttVal);
    
    double forwardVal = OI.joystick.leftStickY();
    double rotVal = OI.joystick.leftStickX();
    Robot.driveBase.setArcade(forwardVal, rotVal);
    System.out.println("robot driving with controller!");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
