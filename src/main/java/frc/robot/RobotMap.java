/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  
  // TODO: make this worth something
  public static final double[] liftCheckpoints = {10.0, 20.0, 30.0};

  public class Config {
    public static final boolean ENABLE_PNEUMATICS = true; 
    public static final boolean ENABLE_MOTORS = true;
  }

  public class Sensors {

    public static final int CAMERA_ONE = 0;
    public static final int CAMERA_TWO = 1;

    public static final int LIFT_SWTICH_UP = 0;
    public static final int LIFT_SWTICH_DOWN = 1;
  }

  public class Joystick {

    public static final int LEFT_X_AXIS = 0;
    public static final int LEFT_Y_AXIS = 1;

    public static final int RIGHT_X_AXIS = 4;
    public static final int RIGHT_Y_AXIS = 5;
    
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    public static final int DPAD = 0;
    
    public static final int BTN_A = 1;
    public static final int BTN_B = 2;
    public static final int BTN_X = 3;
    public static final int BTN_Y = 4;
    public static final int BTN_LB = 5;
    public static final int BTN_RB = 6;
    public static final int BTN_BACK = 7;
    public static final int BTN_START = 8;
    public static final int BTN_LEFT_JOYSTICK = 9;
    public static final int BTN_RIGHT_JOYSTICK = 10;
  }

  public class Motors {
    public static final int LEFT_MOTOR_PWM = 1;
    public static final int RIGHT_MOTOR_PWM = 0;

    public static final int LEFT_INTAKE_PWM = 2;
    public static final int RIGHT_INTAKE_PWM = 3;
        
    // CAN wired, so ports refer to Device ID
    // configure in Phoenix Tuner
    public static final int ARM_MOTOR_PWM = 2;
    public static final int LIFT_MOTOR_PWM = 3;
  }

  public class Pneumatics {
    public static final int HATCH_LIFTER_FORWARD = 0;
    public static final int HATCH_LIFTER_REVERSE = 1;

    public static final int GIRPPER_FORWARD = 2;
    public static final int GRIPPER_REVERSE = 3;

    public static final int JACK_FORWARD = 4;
    public static final int JACK_REVERSE = 5;
  }
}