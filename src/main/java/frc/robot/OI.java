/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.controller.CustomGamepad;
import frc.controller.CustomJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static CustomGamepad logitechF310;
  public static CustomJoystick flightController;
  
  public static void init() {
    logitechF310 = new CustomGamepad(0);
    //flightController = new CustomJoystick(1);
  }

  public static class Axes {
    public static double driveForwardAxis() {
    return - OI.logitechF310.leftStickY() * RobotMap.Constants.DRIVE_HIGH
           - OI.logitechF310.rightStickY() * RobotMap.Constants.DRIVE_LOW
           + OI.logitechF310.dPadVertical() * RobotMap.Constants.DRIVE_LOW;
    }

    public static double driveRotAxis() {
      return  OI.logitechF310.leftStickX() * RobotMap.Constants.DRIVE_HIGH
            + OI.logitechF310.rightStickX() * RobotMap.Constants.DRIVE_LOW
            + OI.logitechF310.dPadHorizontal() * RobotMap.Constants.DRIVE_LOW;
    }

    public static double liftAxis() {
      return OI.logitechF310.lb.get() ? 1:0
           - OI.logitechF310.leftTrigger();
    }

    public static double wristAxis() {
      double pwr = 0.0;
      pwr += OI.logitechF310.rightTrigger();
      
      if(OI.logitechF310.rb.get())
        pwr -= 1.0;
      return pwr;
    }
  }
}
