/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Wrist;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeControl extends Command {
  public IntakeControl() {
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean forward = OI.joystick.getRawButton(RobotMap.Joystick.BTN_A);
    boolean backward = OI.joystick.getRawButton(RobotMap.Joystick.BTN_B);

    double pwr = (forward ? 1.0 : 0.0) - (backward ? 1.0 : 0.0);

    Robot.wrist.setIntakePwr(pwr);
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
    Robot.wrist.setIntakePwr(0.0);
  }
}
