/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  public class Config {
    public static final boolean ENABLE_PNEUMATICS = true; 
    public static final boolean ENABLE_MOTORS = true;
  }

  public class Constants {
    public static final double INTAKE_IN_PWR    = 0.50f;
    public static final double INTAKE_OUT_PWR   =-0.90f;
    
    public static final double WRIST_PWR        =-0.50f;
    public static final double WRIST_AUTO_PWR   = 0.20f;
    public static final double WRIST_DEAD_PWR   =-0.05f; // not used

    public static final double DRIVE_HIGH       = 0.80f;
    public static final double DRIVE_LOW        = 0.50f;
    
    public class kLiftGains {
      public static final double kF = 0.0;
      public static final double kP = 1.0;
      public static final double kI = 1.0;
      public static final double kD = 1.0;
    }
  }

  public class Sensors {

    public static final int CAMERA_WRIST = 0;
    public static final int CAMERA_LIFT = 1;

    public static final int LIFT_SWTICH_UP = 1;
    public static final int LIFT_SWTICH_DOWN = 0;

    public static final int LED_STRIP = 2;
  }

  public class Motors {
    public static final int LEFT_DRIVE = 1;
    public static final int RIGHT_DRIVE = 0;

    public static final int INTAKE = 2;    
    public static final int WRIST = 3;

    // CAN wired, so ports refer to Device ID
    public static final int RIGHT_LIFT = 2;
    public static final int LEFT_LIFT = 3;
  }

  public class Pneumatics {
    public static final int HATCH_UPWARD = 0;
    public static final int HATCH_DOWNWARD = 1;

    public static final int HATCH_FORWARD = 2;
    public static final int HATCH_REVERSE = 3;

    public static final int FRONT_JACK_FORWARD = 6;
    public static final int FRONT_JACK_REVERSE = 7;
    
    public static final int BACK_JACK_FORWARD = 4;
    public static final int BACK_JACK_REVERSE = 5;
  }
  
  public class xInputGamepad {

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

  public class FlightJoystick {

  }
}