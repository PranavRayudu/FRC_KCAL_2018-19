/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
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
  private Talon rightSPX;
  private DifferentialDrive drive;

  private Solenoid driveJack1;
  private Solenoid driveJack2;

  private boolean driveJackState;

  public Drive() {
    
    leftPWM = new Talon(RobotMap.Motors.LEFT_MOTOR_PWM);
    rightSPX = new Talon(RobotMap.Motors.RIGHT_MOTOR_PWM);

    drive = new DifferentialDrive(leftPWM, rightSPX);

    driveJack1 = new Solenoid(RobotMap.Pneumatics.JACK_PORT1);
    driveJack2 = new Solenoid(RobotMap.Pneumatics.JACK_PORT2);
  }

  public void setRaw(double leftVal, double rightVal) {
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  private void setJack(boolean state) {
    driveJack1.set(state);
    driveJack2.set(state);
    driveJackState = state;
  }

  public void toggleJack() {
    setJack(!driveJackState);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveControl());
  }
}