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

public class PIDLiftSmart extends Command {
  private double P, I, D, F;
  private double tolerance, setpoint;

  private double integral, error, previous_error;
  
  private static double timeStep = 0.02f;

  public PIDLiftSmart() {
    requires(Robot.lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    this.P = RobotMap.Constants.Lift.kPID.kP;
    this.I = RobotMap.Constants.Lift.kPID.kI;
    this.D = RobotMap.Constants.Lift.kPID.kD;
    this.F = RobotMap.Constants.Lift.kPID.kF;
    this.tolerance = RobotMap.Constants.Lift.tolerance;
    
    if(Robot.intake.getHatchMode())
      this.setpoint = RobotMap.Constants.Lift.setpoints.PLAYER_STATION;
    else
      this.setpoint = RobotMap.Constants.Lift.setpoints.CARGO;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    this.error = setpoint - Robot.lift.getEncoderRaw(); // Error = Target - Actual
    
    this.integral += (this.error * timeStep); // increase by area under curve (dist * time)
    double derivative = (this.error - this.previous_error) / timeStep; // velocity

    double output = (this.P * this.error) + (this.I * this.integral) + (this.D * derivative) + this.F;
    
    this.previous_error = error;

    Robot.lift.setPwr(output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    if(!Robot.lift.getPIDenabled()) {
      System.out.println("PID command stopped because PID is disabled");
      return true;
    } else if(Math.abs(setpoint - Robot.lift.getEncoderRaw()) < tolerance) {
      System.out.println("PID command stopped because of tolerance threshold");
      return true;
    } else {
      return false;
    }
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
