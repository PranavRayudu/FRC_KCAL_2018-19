/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;


public class ArmControl extends Command {
  
  public ArmControl() {
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // boolean up = OI.joystick.getRawButton(RobotMap.Joystick.BTN_X);
    // boolean down = OI.joystick.getRawButton(RobotMap.Joystick.BTN_Y);

    // double pwr = (up ? 1.0 : 0.0) - (down ? 1.0 : 0.0);

    double pwr = 0.0;

    pwr += OI.logitechF310.rightTrigger();
    if(OI.logitechF310.rb.get())
      pwr -= 1.0;

    Robot.arm.setPwr(pwr);
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
    end();
  }
}
