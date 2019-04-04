/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PIDLift extends Command {
  double P, I, D = 1;
  double tolerance, setpoint;

  double integral, error, previous_error;

  public PIDLift(int setpoint) {
    requires(Robot.lift);
    this.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    this.P = RobotMap.Constants.kLiftGains.kP;
    this.I = RobotMap.Constants.kLiftGains.kI;
    this.D = RobotMap.Constants.kLiftGains.kD;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.previous_error = error;
    this.error = setpoint - Robot.lift.getEncoder(); // Error = Target - Actual
    
    this.integral += (error*.02f); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    double derivative = (error - this.previous_error) / .02f;
    double output = P*error + I*this.integral + D*derivative;

    Robot.lift.setPwr(output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.lift.getPIDenabled() || Math.abs(setpoint - error) < tolerance;
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
