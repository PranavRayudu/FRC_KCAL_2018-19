/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;

public class ClimbPlatform extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbPlatform() {
    requires(Robot.drive);
    
    addSequential(new ToggleFrontJack());
    addSequential(new WaitCommand(1)); // let cylinder fully extend
    
    addSequential(new TimedDrive(0.7, 0.7, 3));

    addSequential(new ToggleBackJack());
    addSequential(new WaitCommand(1));

    addSequential(new TimedDrive(0.7, 0.7, 3));
    
    // NOTE:
    // addSequential() and addParallel() have a second parameter, timeout
    // it is the time (in seconds) after which the command will be terminated 
    // the command may terminate before that, but timeout is simply max time a command is allowed to live
    // may be useful later, but I created my own timed command 'cuz I feel like it
  }
}
