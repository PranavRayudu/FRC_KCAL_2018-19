/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LiftControl extends Command {

  public LiftControl() {
    requires(Robot.lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.lift.setPwr(OI.Axes.liftAxis());// * RobotMap.Constants.Lift.CTRL_PWR + RobotMap.Constants.Lift.kPID.kF);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.lift.setPwr(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
