/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
  }

  public void setPwr(double val) {

    double in = 0.0;
    
    if(topLimit.get())
      Math.min(0.0, val);
    
    if(bottomLimit.get())
      in = Math.max(0.0, val);

    liftPWM.set(ControlMode.PercentOutput, in);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftControl());
  }
}
