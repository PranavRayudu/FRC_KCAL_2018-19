/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.Arm.ShortArmLower;
import frc.robot.commands.Wrist.ToggleHatchLifter;

public class DepositHatch extends CommandGroup {
  
  public DepositHatch() {
    requires(Robot.arm);
    requires(Robot.wrist);

    // REQUIRED: hatch must be closed first
    //addSequential(new ToggleHatchLifter()); // extend/deposit
    //addSequential(new WaitCommand(0.5f));
    addSequential(new ShortArmLower()); // lower arm for 0.5s
    addSequential(new WaitCommand(0.5f));
    addSequential(new ToggleHatchLifter()); // then retract extender
  }

}
