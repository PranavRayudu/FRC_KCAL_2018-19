/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Intake.IntakeStop;

/**
 * Add your docs here.
 */

public class Intake extends Subsystem {

  public enum IntakeState {IN, OUT, STOPPED};
  
  private Talon intake;  
  public IntakeState intakeState; // TODO: refactor this variable into private
  private boolean hatchMode = false;

  public Intake() {
    intake = new Talon(RobotMap.Motors.INTAKE);
    intake.setSafetyEnabled(true);
    intakeState = IntakeState.STOPPED;
  }

  // intake controls
  
  public void setIntakePwr(double val) {
    intake.set(val);
  }

  // NOTE: do not use - slow refresh rate
  // public void setIntakePwr(IntakeState state) {
  //   switch(state) {
  //     case IN: setIntakePwr(RobotMap.Constants.INTAKE_IN_PWR);
  //     case OUT: setIntakePwr(RobotMap.Constants.INTAKE_OUT_PWR);
  //     case STOPPED: setIntakePwr(0.0f);
  //   }

  //   intakeState = state;
  // }

    private void setHatchMode(boolean hatchMode) {
      this.hatchMode = hatchMode;
    }

    public void toggleHatchMode() {
      setHatchMode(!hatchMode);
    }

    public boolean getHatchMode() {
      return hatchMode;
    }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }
}
