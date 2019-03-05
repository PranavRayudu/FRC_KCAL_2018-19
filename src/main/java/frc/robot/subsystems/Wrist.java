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
import frc.robot.RobotMap;
import frc.robot.commands.Wrist.IntakeStop;

/**
 * Add your docs here.
 */

public class Wrist extends Subsystem {
  
  private DoubleSolenoid hatchLifter;
  private DoubleSolenoid gripper;

  private boolean hatchState;
  private boolean gripperState;

  private Talon leftIntake;
  private Talon rightIntake;

  public Wrist() {
    
    hatchLifter = new DoubleSolenoid(
      RobotMap.Pneumatics.HATCH_LIFTER_FORWARD, 
      RobotMap.Pneumatics.HATCH_LIFTER_REVERSE
    );

    gripper = new DoubleSolenoid(
      RobotMap.Pneumatics.GIRPPER_FORWARD, 
      RobotMap.Pneumatics.GRIPPER_REVERSE
    );
    
    leftIntake = new Talon(RobotMap.Motors.LEFT_INTAKE_PWM);
    rightIntake = new Talon(RobotMap.Motors.RIGHT_INTAKE_PWM);
  }

  // intake controls
  
  public void setIntakePwr(double val) {
    leftIntake.set(val);
    rightIntake.set(val);
  }

  // horizontal controls

  private void setGripperSol(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
    gripper.set(val);
    gripperState = state;
  }
  
  public void toggleGripperSol() {
    setGripperSol(!gripperState);
  }

  // pusher and vertical solenoid controls

  private void setHatchSol(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
    hatchLifter.set(val);
    hatchState = state;
  }
  
  public void toggleHatchSol() {
    setHatchSol(!hatchState);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }
}
