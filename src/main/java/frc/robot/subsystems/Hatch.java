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

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {

  // private DoubleSolenoid claw;
  // private DoubleSolenoid extender;
  
  private boolean clawState;
  private boolean extenderState;

  public Hatch() {
    
    // claw = new DoubleSolenoid(
    //   RobotMap.Pneumatics.HATCH_UPWARD, 
    //   RobotMap.Pneumatics.HATCH_DOWNWARD
    // );

    clawState = true;
    setClawSol(clawState);

    // extender = new DoubleSolenoid(
    //   RobotMap.Pneumatics.HATCH_FORWARD, 
    //   RobotMap.Pneumatics.HATCH_REVERSE
    // );

    extenderState = false;
    setHatchExtentionSol(extenderState);
  }

  private void setClawSol(boolean state) {
    DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
//    claw.set(val);
    clawState = state;
  }
  
  public void toggleClawSol() {
    setClawSol(!clawState);
  }

  private void setHatchExtentionSol(boolean state) {
    //DoubleSolenoid.Value val = state ? Value.kForward : Value.kReverse;
//    extender.set(val);
    extenderState = state;
  }

  public void toggleExtenderSol() {
    setHatchExtentionSol(!extenderState);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
