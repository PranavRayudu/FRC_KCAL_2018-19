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
import frc.robot.commands.Wrist.WristControl;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {

  Talon wristPWM;

  public Wrist() {
    wristPWM = new Talon(RobotMap.Motors.WRIST);
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    wristPWM.set(val);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristControl());
  }
}
