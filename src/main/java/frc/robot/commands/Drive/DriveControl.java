/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveControl extends Command {
  public DriveControl() {
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // Double Joystick control
    // double rightVal = OI.joystick.rightStickY();
    // double lefttVal = OI.joystick.leftStickY();
    // Robot.driveBase.setRaw(rightVal, lefttVal);
    
    // Single Joystick control
    double forwardVal = - OI.joystick.leftStickY() * RobotMap.Constants.DRIVE_HIGH
                        - OI.joystick.rightStickY() * RobotMap.Constants.DRIVE_LOW;
    double rotVal = OI.joystick.leftStickX();

    Robot.drive.setArcade(forwardVal, rotVal);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.setRaw(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
