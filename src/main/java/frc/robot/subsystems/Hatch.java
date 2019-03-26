/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {

  private DoubleSolenoid hatchLifter;
  private boolean hatchState;
  
  public Hatch() {
    hatchLifter = new DoubleSolenoid(
      RobotMap.Pneumatics.HATCH_UPWARD, 
      RobotMap.Pneumatics.HATCH_DOWNWARD
    );
  }

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
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
