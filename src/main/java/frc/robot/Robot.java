/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DepositHatch;
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
      //UsbCamera wristCamera = 
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_WRIST);
      //wristCamera.setFPS(20);
      //wristCamera.setResolution(260, 195);

      //wristCamera.setVideoMode(PixelFormat.kBGR, 260, 195, 20);
      
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_WRIST);
    }

    try {
      //UsbCamera liftCamera = 
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_LIFT);
      //liftCamera.setFPS(20);
      //liftCamera.setResolution(160, 120);

    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_LIFT);
    }

    OI.init();

    drive = new Drive();
    lift = new Lift();
    arm = new Arm();
    wrist = new Wrist();

    //SmartDashboard.putData(drive);
    // SmartDashboard.putData(lift);
    // SmartDashboard.putData(arm);
    //SmartDashboard.putData(wrist);

    compressor = new Compressor();
    compressor.setClosedLoopControl(RobotMap.Config.ENABLE_PNEUMATICS);

    led = new DigitalOutput(RobotMap.Sensors.LED_STRIP);

    System.out.println("Robot has turned on");
  }
  
  private void updateLED() {
    led.set(DriverStation.getInstance().getAlliance() != Alliance.Red);
  }

  private void postDashboardValues() {
    
    SmartDashboard.putBoolean("compresssor pressurized", compressor.getPressureSwitchValue());
    SmartDashboard.putString("lift is ", lift.bottomedOut() ? "bottomed out" : lift.toppedOut() ? "topped" : "in middle");
    
    String intakeText = "";
    switch(wrist.intakeState) {
      case IN: intakeText = "taking in";
      break;
      case OUT: intakeText = "going out";
      break;
      case STOPPED: intakeText = "stopped"; 
    }
    SmartDashboard.putString("intake is ", intakeText);
    //SmartDashboard.putData();
  }

  private void bindButtons() {
    OI.joystick.a.whenPressed(new ToggleHatchLifter());
    OI.joystick.b.whenPressed(new ToggleGripper());
    
    OI.joystick.x.whenPressed(new ToggleFrontJack());
    OI.joystick.y.whenPressed(new ToggleBackJack());

    //OI.joystick.start.whenPressed(new CalibrateLift());
    //OI.joystick.back.whenPressed(new DepositHatch());

    OI.joystick.lefJoystickButton.whenPressed(new IntakeOut());
    OI.joystick.righJoystickButton.toggleWhenPressed(new IntakeIn());
  }
  
  @Override
  public void robotPeriodic() {
  }

  private void commonInit() {
    bindButtons();
    postDashboardValues();

    //SmartDashboard.getBoolean(key, defaultValue);
  }

  private void commonLoop() {
    
    updateLED();
    postDashboardValues();
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    updateLED();
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
  public void testInit() {
    commonInit();
  }

  @Override
  public void testPeriodic() {
    commonLoop();
  }
}
