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

  private DoubleSolenoid jack;

  private boolean driveJackState;

  public Drive() {
    
    leftPWM = new Talon(RobotMap.Motors.LEFT_MOTOR_PWM);
    rightPWM = new Talon(RobotMap.Motors.RIGHT_MOTOR_PWM);

    drive = new DifferentialDrive(leftPWM, rightPWM);

    jack = new DoubleSolenoid(
      RobotMap.Pneumatics.JACK_FORWARD, 
      RobotMap.Pneumatics.JACK_REVERSE
    );
  }

  public void setRaw(double leftVal, double rightVal) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    //drive.arcadeDrive(xSpeed, zRotation);
  }

  private void setJack(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
    jack.set(val);
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