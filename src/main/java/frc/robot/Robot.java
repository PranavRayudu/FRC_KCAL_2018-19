/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.Wrist.TogglePusher;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

  public static OI oi;

  public static DriveBase driveBase;
  public static Lift lift;
  public static Arm arm;
  public static Wrist wrist;
  
  @Override
  public void robotInit() {
    
    try {
      CameraServer.getInstance().startAutomaticCapture(0);
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera 0");
    }

    try {
      CameraServer.getInstance().startAutomaticCapture(1);
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera 1");
    }


    oi = new OI();

    driveBase = new DriveBase();
    lift = new Lift();
    arm = new Arm();
    wrist = new Wrist();

    SmartDashboard.putData(driveBase);
    SmartDashboard.putData(lift);
    SmartDashboard.putData(arm);
    SmartDashboard.putData(wrist);

    System.out.println("Robot has turned on");
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // although all of these commands are part of the same subsystem, they are instantaneous methods, so they should happen instantly
    wrist.setCompressor(true);;
    oi.joystick.b.whenPressed(new TogglePusher());
    //oi.joystick.y.whenPressed(new ToggleWristHorizontal());
    //oi.joystick.start.whenPressed(new ToggleWristVertical());
  }

  @Override
  public void teleopPeriodic() {

    System.out.println("Robot has started driver control!");

    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
