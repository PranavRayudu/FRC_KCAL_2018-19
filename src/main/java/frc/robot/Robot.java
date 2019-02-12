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
import frc.robot.commands.Drive.ToggleJack;
import frc.robot.commands.Wrist.ToggleGripper;
import frc.robot.commands.Wrist.ToggleHatchLifter;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

  public static OI oi;

  public static Drive drive;
  public static Lift lift;
  public static Arm arm;
  public static Wrist wrist;

  ToggleGripper togGrip;  

  @Override
  public void robotInit() {
    
    try {
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_ONE);
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_ONE);
    }

    try {
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_TWO);
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_TWO);
    }


    oi = new OI();

    drive = new Drive();
    lift = new Lift();
    arm = new Arm();
    wrist = new Wrist();

    SmartDashboard.putData(drive);
    SmartDashboard.putData(lift);
    SmartDashboard.putData(arm);
    SmartDashboard.putData(wrist);

    togGrip = new ToggleGripper();

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

    oi.joystick.y.whenPressed(new ToggleHatchLifter());
    oi.joystick.b.whenPressed(new ToggleJack());
  }

  @Override
  public void teleopPeriodic() {

    System.out.println("Robot has started driver control!");

    Scheduler.getInstance().run();
    
    if(OI.joystick.dPadDown())
      togGrip.start();
  }

  @Override
  public void testPeriodic() {
  }
}
