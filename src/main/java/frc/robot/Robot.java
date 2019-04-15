/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
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
import frc.robot.commands.Intake.IntakeOut;
import frc.robot.commands.Intake.PoweredIntakeIn;
import frc.robot.commands.Lift.PIDLift;
import frc.robot.commands.Lift.TogglePIDEnabled;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {

  public static Drive drive;
  public static Lift lift;
  public static Wrist wrist;
  public static Intake intake;
  public static Hatch hatch;

  private Compressor compressor;

  DigitalOutput led;

  @Override
  public void robotInit() {
    
    try {
      UsbCamera wristCamera = 
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_WRIST);
      wristCamera.setFPS(20);
      //wristCamera.setExposureManual(60);
      wristCamera.setResolution(260, 195);

      //wristCamera.setVideoMode(PixelFormat.kBGR, 260, 195, 20);
      
    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_WRIST);
    }

    try {
      UsbCamera liftCamera = 
      CameraServer.getInstance().startAutomaticCapture(RobotMap.Sensors.CAMERA_LIFT);
      liftCamera.setFPS(20);
      liftCamera.setResolution(160, 120);

    } catch (Exception e) {
      System.out.println("Problem occured with loading Camera " + RobotMap.Sensors.CAMERA_LIFT);
    }

    OI.init();

    drive = new Drive();
    lift = new Lift();
    wrist = new Wrist();
    intake = new Intake();
    hatch = new Hatch();

    compressor = new Compressor();
    compressor.setClosedLoopControl(RobotMap.Config.ENABLE_PNEUMATICS);

    led = new DigitalOutput(RobotMap.Sensors.LED_STRIP);

    System.out.println("Robot has turned on");
  }
  
  private void updateLED() {
    led.set(DriverStation.getInstance().getAlliance() != Alliance.Red);
  }

  private void postDashboardValues() {
    
    SmartDashboard.putNumber("lift encoder is ", lift.getEncoder());

    SmartDashboard.putBoolean("compresssor pressurized", compressor.getPressureSwitchValue());
    SmartDashboard.putBoolean("Lift PID enabled ", lift.getPIDenabled());

    SmartDashboard.putBoolean("front jack", drive.getFrontJackState());
    SmartDashboard.putBoolean("back jack", drive.getBackJackState());
    
    String intakeText = "";
    switch(intake.intakeState) {
      case IN: intakeText = "taking in";
      break;
      case OUT: intakeText = "going out";
      break;
      case STOPPED: intakeText = "stopped"; 
    }

    SmartDashboard.putString("intake is ", intakeText);
  }

  private void bindButtons() {
    
    OI.logitechF310.x.whenPressed(new ToggleFrontJack());
    OI.logitechF310.y.whenPressed(new ToggleBackJack());

    OI.logitechF310.lefJoystickButton.whenPressed(new IntakeOut());
    OI.logitechF310.righJoystickButton.whileHeld(new PoweredIntakeIn());
    
    OI.logitechF310.back.whenPressed(new TogglePIDEnabled());
    
    OI.logitechF310.a.whenPressed(new PIDLift(RobotMap.Constants.kLiftGains.setpoints.GROUND));
    OI.logitechF310.b.whenPressed(new PIDLift(RobotMap.Constants.kLiftGains.setpoints.SHIP_LV2));
    // OI.logitechF310.start.whenPressed(new PIDLift(RobotMap.Constants.kLiftGains.setpoints.SHIP_LV3));
    
  }
  
  @Override
  public void robotPeriodic() {
  }

  private void commonInit() {
    bindButtons();
    postDashboardValues();
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
    lift.zeroOutEncoder();
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
