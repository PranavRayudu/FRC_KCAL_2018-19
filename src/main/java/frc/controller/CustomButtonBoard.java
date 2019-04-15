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

    JoystickButton share, options;
    JoystickButton lb, rb;
    //JoystickButton sl, sr;
    JoystickButton sr;
    JoystickButton x, y, a, b;

    CustomDPad dpad;

    public CustomButtonBoard(int port) {
        super(port);

        share = new JoystickButton(this, RobotMap.ButtonBoard.BTN_SHARE);
        options = new JoystickButton(this, RobotMap.ButtonBoard.BTN_OPTIONS);

        lb = new JoystickButton(this, RobotMap.ButtonBoard.BTN_LB);
        //lt = new JoystickButton(this, RobotMap.ButtonBoard.BTN_LT);
        rb = new JoystickButton(this, RobotMap.ButtonBoard.BTN_RB);
        //rt = new JoystickButton(this, RobotMap.ButtonBoard.BTN_RT);
        
        //sl = new JoystickButton(this, RobotMap.ButtonBoard.BTN_SL); // this is the macro key
        sr = new JoystickButton(this, RobotMap.ButtonBoard.BTN_SR); // this is the macro key

        x = new JoystickButton(this, RobotMap.ButtonBoard.BTN_X);
        y = new JoystickButton(this, RobotMap.ButtonBoard.BTN_Y);
        a = new JoystickButton(this, RobotMap.ButtonBoard.BTN_A);
        b = new JoystickButton(this, RobotMap.ButtonBoard.BTN_B);
        
        dpad = new CustomDPad(this);
    }

    public double xAxis() {
        return this.getRawAxis(RobotMap.ButtonBoard.LEFT_X_AXIS);
    }

    public double yAxis() {
        return this.getRawAxis(RobotMap.ButtonBoard.LEFT_Y_AXIS);
    }

    public double leftTrigger() {
        return this.getRawAxis(RobotMap.ButtonBoard.LEFT_TRIGGER);
    }

    public double rightTrigger() {
        return this.getRawAxis(RobotMap.ButtonBoard.RIGHT_TRIGGER);
    }
}
