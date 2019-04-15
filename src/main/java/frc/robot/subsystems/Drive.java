/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive.DriveControl;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {

  private Talon leftPWM;
  private Talon rightPWM;
  private DifferentialDrive drive;

  private DoubleSolenoid frontJack;
  private DoubleSolenoid backJack;

  private boolean frontJackState;
  private boolean backJackState;

  public Drive() {
    
    leftPWM = new Talon(RobotMap.Motors.LEFT_DRIVE);
    rightPWM = new Talon(RobotMap.Motors.RIGHT_DRIVE);

    leftPWM.setSafetyEnabled(true);
    rightPWM.setSafetyEnabled(true);

    drive = new DifferentialDrive(leftPWM, rightPWM);

    frontJack = new DoubleSolenoid(
      RobotMap.Pneumatics.FRONT_JACK_FORWARD, 
      RobotMap.Pneumatics.FRONT_JACK_REVERSE
    );

    backJack = new DoubleSolenoid(
      RobotMap.Pneumatics.BACK_JACK_FORWARD, 
      RobotMap.Pneumatics.BACK_JACK_REVERSE
    );

    //setFrontJack(true);
    //setBackJack(true);
  }

  public void setRaw(double leftVal, double rightVal) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    drive.arcadeDrive(xSpeed, zRotation);
  }

  public boolean getBackJackState() {
    return backJackState;
  }

  public boolean getFrontJackState() {
    return frontJackState;
  }

  private void setFrontJack(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
    frontJack.set(val);
    frontJackState = state;
  }

  private void setBackJack(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
    backJack.set(val);
    backJackState = state;
  }

  public void toggleFrontJack() {
    setFrontJack(!frontJackState);
  }

  public void toggleBackjack() {
    setBackJack(!backJackState);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveControl());
  }
}