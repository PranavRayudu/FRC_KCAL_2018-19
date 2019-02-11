/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.commands.DriveBase.DriveControl;

/**
 * Add your docs here.
 */
public class DriveBase extends Subsystem {

  private PWMVictorSPX leftPWM;
  private PWMVictorSPX rightSPX;
  private DifferentialDrive drive;

  private Solenoid driveLift1;
  private Solenoid driveLift2;

  private boolean driveLiftState;

  public DriveBase() {
    
    leftPWM = new PWMVictorSPX(RobotMap.Motors.LEFT_MOTOR_PWM);
    rightSPX = new PWMVictorSPX(RobotMap.Motors.RIGHT_MOTOR_PWM);

    drive = new DifferentialDrive(leftPWM, rightSPX);

    driveLift1 = new Solenoid(RobotMap.Pneumatics.LIFT_SOLENOID_PORT1);
    driveLift2 = new Solenoid(RobotMap.Pneumatics.LIFT_SOLENOID_PORT2);
  }

  public void setRaw(double leftVal, double rightVal) {
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  public void setBaseLift(boolean state) {
    driveLift1.set(state);
    driveLift2.set(state);
    driveLiftState = state;
  }

  public void toggleBaseLift() {
    driveLiftState = !driveLiftState;
    driveLift1.set(driveLiftState);
    driveLift2.set(driveLiftState);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveControl());
  }
}