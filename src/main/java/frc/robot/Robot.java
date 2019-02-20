/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive.ToggleBackJack;
import frc.robot.commands.Drive.ToggleFrontJack;
import frc.robot.commands.Lift.CalibrateLift;
import frc.robot.commands.Wrist.IntakeIn;
import frc.robot.commands.Wrist.IntakeOut;
import frc.robot.commands.Wrist.ToggleGripper;
import frc.robot.commands.Wrist.ToggleHatchLifter;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

  public static Drive drive;
  public static Lift lift;
  public static Arm arm;
  public static Wrist wrist;

  private Compressor compressor;

  DigitalOutput led;

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

    OI.init();

    drive = new Drive();
    lift = new Lift();
    arm = new Arm();
    wrist = new Wrist();

    // SmartDashboard.putData(drive);
    // SmartDashboard.putData(lift);
    // SmartDashboard.putData(arm);
    SmartDashboard.putData(wrist);

    compressor = new Compressor();
    compressor.setClosedLoopControl(RobotMap.Config.ENABLE_PNEUMATICS);

    led = new DigitalOutput(RobotMap.Sensors.LED_STRIP);

    System.out.println("Robot has turned on");
  }

  private void commonInit() {
    OI.joystick.a.whenPressed(new ToggleHatchLifter());
    OI.joystick.b.whenPressed(new ToggleGripper());
    
    OI.joystick.x.whenPressed(new ToggleFrontJack());
    OI.joystick.y.whenPressed(new ToggleBackJack());

    OI.joystick.start.whenPressed(new CalibrateLift());

    OI.joystick.lefJoystickButton.whenPressed(new IntakeOut());
    OI.joystick.righJoystickButton.toggleWhenPressed(new IntakeIn());
  }

  
  public void updateLED() {
    
    if(DriverStation.getInstance().getAlliance() == Alliance.Red) {
      led.set(false);
    } else {
      led.set(true);
    }
  }

  private void commonLoop() {
    
    updateLED();

    Scheduler.getInstance().run();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    updateLED();
  }

  @Override
  public void autonomousInit() {
    commonInit();
  }

  @Override
  public void autonomousPeriodic() {
    commonLoop();
  }

  @Override
  public void teleopInit() {
    commonInit();
  }

  @Override
  public void teleopPeriodic() {
    commonLoop();
  }

  @Override
  public void testPeriodic() {
  }
}
