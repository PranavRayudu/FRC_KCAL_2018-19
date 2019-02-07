/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.Wrist.IntakeControl;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  
  private Compressor compressor;
  private Solenoid vertical;
  private Solenoid horizontal;
  private Solenoid pusher;

  private boolean verticalState;
  private boolean horizontalState;
  private boolean pusherState;

  private Talon leftIntake;
  private Talon rightIntake;

  public Wrist() {
    compressor = new Compressor(RobotMap.Pneumatics.COMPRESSOR_PORT);
    vertical = new Solenoid(RobotMap.Pneumatics.VERTICAL_SOLENOID_PORT);
    vertical = new Solenoid(RobotMap.Pneumatics.HORIZONTAL_SOLENOID_PORT);
    pusher = new Solenoid(RobotMap.Pneumatics.PUSHER_SOLENOID_PORT);

    leftIntake = new Talon(RobotMap.Motors.LEFT_INTAKE_PWM);
    rightIntake = new Talon(RobotMap.Motors.RIGHT_INTAKE_PWM);
  }

  // intake controls

  public void setIntakePwr(double val) {
    leftIntake.set(val);
    rightIntake.set(-val);
  }
  
  // compressor controls

  public void setCompressor(boolean state) {
    if(state) compressor.start();
    else compressor.stop();
  }

  // horizontal solenoid controls

  public void tooglePusherSol() {
    pusherState = !pusherState;
    pusher.set(!pusherState);
  }

  public void setPusherSol(boolean state) {
    pusher.set(state);
    pusherState = state;
  }

  // vertical solenoid controls

  public void toogleVerticalSol() {
    verticalState = !verticalState;
    vertical.set(!verticalState);
  }

  public void setVerticalSol(boolean state) {
    vertical.set(state);
    verticalState = state;
  }

  // horizontal solenoid controls

  public void toggleHorizontalSol() {
    horizontalState = !horizontalState;
    horizontal.set(horizontalState);
  }

  public void setHorizontalSol(boolean state) {
    horizontal.set(state);
    horizontalState = state;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeControl());
  }
}
