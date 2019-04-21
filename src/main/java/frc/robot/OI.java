/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.controller.CustomGamepad;
import frc.robot.commands.Drive.ToggleBackJack;
import frc.robot.commands.Drive.ToggleFrontJack;
import frc.robot.commands.Intake.IntakeOut;
import frc.robot.commands.Intake.PoweredIntakeIn;
import frc.robot.commands.Lift.PIDLift;
import frc.robot.commands.Lift.PIDLiftSmart;
import frc.robot.commands.Lift.TogglePIDEnabled;
import frc.robot.commands.Wrist.ToggleHatchMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static CustomGamepad logitechF310;
  //public static CustomJoystick flightController;
  
  public static void init() {
    logitechF310 = new CustomGamepad(0);
    //flightController = new CustomJoystick(1);

    
    OI.logitechF310.x.whenPressed(new ToggleFrontJack());
    OI.logitechF310.y.whenPressed(new ToggleBackJack());

    OI.logitechF310.lefJoystickButton.whenPressed(new IntakeOut());
    OI.logitechF310.righJoystickButton.whileHeld(new PoweredIntakeIn());

    OI.logitechF310.back.whenPressed(new TogglePIDEnabled());
    OI.logitechF310.start.whenPressed(new ToggleHatchMode());
    
    OI.logitechF310.a.whenPressed(new PIDLift(RobotMap.Constants.Lift.setpoints.GROUND));
    OI.logitechF310.b.whenPressed(new PIDLiftSmart());
    //OI.logitechF310.b.whenPressed(new PIDLift(RobotMap.Constants.Lift.setpoints.SHIP_LV2));
  }

  public static class Axes {

    public static double driveVerticalLowAxis() {
    return - OI.logitechF310.rightStickY()
           + OI.logitechF310.dpad.vertical();
    }

    public static double driveVerticalHighAxis() {
      return - OI.logitechF310.leftStickY();
    }

    public static double driveHorizontalLowAxis() {
      return OI.logitechF310.rightStickX()
           + OI.logitechF310.dpad.horizontal();
    }

    public static double driveHorizontalHighAxis() {
      return  OI.logitechF310.leftStickX();
    }

    public static double liftAxis() {
      
      double pwr = 0.0;

      pwr += OI.logitechF310.leftTrigger();
      
      pwr -= OI.logitechF310.lb.get() ? 1.0 : 0.0;
      
      return pwr;
    }

    public static double wristAxis() {
      
      double pwr = 0.0;

      pwr += OI.logitechF310.rightTrigger();
      
      pwr -= OI.logitechF310.rb.get() ? 1.0 : 0.0;
      
      return pwr;
    }
  }
}
