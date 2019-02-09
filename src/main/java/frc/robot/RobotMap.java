/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  
  // controller ports
  public class Joystick {
    // please check that these controller ports are actually right through the dashboard
    public static final int PORT = 0;

    public static final int LEFT_X_AXIS = 1; 
    public static final int LEFT_Y_AXIS = 2;

    public static final int TRIGGER = 3;
    
    public static final int RIGHT_X_AXIS = 4;
    public static final int RIGHT_Y_AXIS = 5;
    
    public static final int BTN_A = 1;
    public static final int BTN_B = 2;
    public static final int BTN_X = 3;;
    public static final int BTN_Y = 4;
    public static final int BTN_LB = 5;
    public static final int BTN_RB = 6;
    public static final int BTN_BACK = 7;
    public static final int BTN_START = 8;
    public static final int BTN_LEFT_JOYSTICK = 9;
    public static final int BTN_RIGHT_JOYSTICK = 10;
  }

  public class Motors {
    public static final int LEFT_MOTOR_PWM = 0;
    public static final int RIGHT_MOTOR_PWM = 1;

    public static final int ARM_MOTOR_PWM = 10;

    public static final int LIFT_MOTOR_PWM = 11;

    public static final int LEFT_INTAKE_PWM = 2;
    public static final int RIGHT_INTAKE_PWM = 3;
  }

  public class Pneumatics {
    public static final int COMPRESSOR_PORT = 0;
    public static final int PUSHER_SOLENOID_PORT = 1;
    public static final int HORIZONTAL_SOLENOID_PORT = 3;
  }
}