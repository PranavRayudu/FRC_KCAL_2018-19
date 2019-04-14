/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CustomButtonBoard extends Joystick {

    JoystickButton turbo, macro, home, share, options;
    JoystickButton lb, lt, rb, rt;
    JoystickButton sl, sr;
    JoystickButton x, y, a, b;

    CustomDPad dpad;

    public CustomButtonBoard(int port) {
        super(port);

        turbo = new JoystickButton(this, RobotMap.ButtonBoard.TURBO);
        macro = new JoystickButton(this, RobotMap.ButtonBoard.MACRO);
        home = new JoystickButton(this, RobotMap.ButtonBoard.HOME);
        share = new JoystickButton(this, RobotMap.ButtonBoard.SHARE);
        options = new JoystickButton(this, RobotMap.ButtonBoard.OPTIONS);

        lb = new JoystickButton(this, RobotMap.ButtonBoard.LB);
        lt = new JoystickButton(this, RobotMap.ButtonBoard.LT);
        rb = new JoystickButton(this, RobotMap.ButtonBoard.RB);
        rt = new JoystickButton(this, RobotMap.ButtonBoard.RT);
        
        x = new JoystickButton(this, RobotMap.ButtonBoard.X);
        y = new JoystickButton(this, RobotMap.ButtonBoard.Y);
        a = new JoystickButton(this, RobotMap.ButtonBoard.A);
        b = new JoystickButton(this, RobotMap.ButtonBoard.B);
        
        dpad = new CustomDPad(this);
    }
}
