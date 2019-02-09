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
public class CustomJoystick extends Joystick {

    private static final double threshold = 0.05;

    public JoystickButton a, b, x, y;
    public JoystickButton lb, rb;
    public JoystickButton start, back;

    public CustomJoystick(final int port) {
        super(port);

        a = new JoystickButton(this, RobotMap.Joystick.BTN_A);
        b = new JoystickButton(this, RobotMap.Joystick.BTN_B);
        x = new JoystickButton(this, RobotMap.Joystick.BTN_X);
        y = new JoystickButton(this, RobotMap.Joystick.BTN_Y);

        lb = new JoystickButton(this, RobotMap.Joystick.BTN_LB);
        rb = new JoystickButton(this, RobotMap.Joystick.BTN_RB);

        start = new JoystickButton(this, RobotMap.Joystick.BTN_START);
        back  = new JoystickButton(this, RobotMap.Joystick.BTN_BACK);
    }

    private double applyThreshold(double val) {
        return Math.abs(val) < threshold ? 0.0 : val;
    }

    public double rightStickX() {
        this.getRawAxis(0);
        return applyThreshold(this.getRawAxis(RobotMap.Joystick.RIGHT_X_AXIS));
    }

    public double rightStickY() {
        return applyThreshold(this.getRawAxis(RobotMap.Joystick.RIGHT_Y_AXIS));
    }

    public double leftStickX() {
        return applyThreshold(this.getRawAxis(RobotMap.Joystick.LEFT_X_AXIS));
    }

    public double leftStickY() {
        return applyThreshold(this.getRawAxis(RobotMap.Joystick.LEFT_Y_AXIS));
    }

    public boolean leftTriggerPressed() {
        return this.getRawAxis(RobotMap.Joystick.TRIGGER) == -1;
    }

    public boolean rightTriggerPressed() {
        return this.getRawAxis(RobotMap.Joystick.TRIGGER) == 1;
    }

    // continue adding more methods for each necessary button
    // add by returning this.getButton(RobotMap.Joystick.{port number})
}
