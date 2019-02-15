/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Lift.LiftControl;

public class Lift extends Subsystem {
  
  DigitalInput topLimit;
  DigitalInput bottomLimit;

  TalonSRX liftPWM;

  public Lift() {

    topLimit = new DigitalInput(RobotMap.Sensors.LIFT_SWTICH_UP);
    bottomLimit = new DigitalInput(RobotMap.Sensors.LIFT_SWTICH_DOWN);

    liftPWM = new TalonSRX(RobotMap.Motors.LIFT_MOTOR_PWM);
    
    //liftPWM.setNeutralMode(NeutralMode.Brake);
    //liftPWM.neutralOutput();
    //liftPWM.setSensorPhase(false); // is your sensor going pos or neg
    //liftPWM.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void setPwr(double val) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    double in = val;

    // if(topLimit.get()) {
    //   in = Math.min(in, 0);
    //   System.out.println("limiting top!!!!!!!!!!!!!!!!!!!!!!!");
    // }

    if(bottomLimit.get()) {
      in = Math.max(in, 0);
    }

    liftPWM.set(ControlMode.PercentOutput, in);
  }

  public void setGoal(double goal) {
    //liftPWM.set(ControlMode.Position, goal);
  }

  public boolean bottomedOut() {
    return bottomLimit.get();
  }

  public boolean toppedOut() {
    return topLimit.get();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftControl());
  }
}
