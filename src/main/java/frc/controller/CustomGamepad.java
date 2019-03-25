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
public class CustomGamepad extends Joystick {

    private static final double joystickThreshold = 0.05;
    private static final double triggerThreshold = 0.5;

    public JoystickButton a, b, x, y;
    public JoystickButton lb, rb;
    public JoystickButton start, back;
    public JoystickButton lefJoystickButton, righJoystickButton;

    public CustomGamepad(final int port) {
        super(port);

        a = new JoystickButton(this, RobotMap.Joystick.BTN_A);
        b = new JoystickButton(this, RobotMap.Joystick.BTN_B);
        x = new JoystickButton(this, RobotMap.Joystick.BTN_X);
        y = new JoystickButton(this, RobotMap.Joystick.BTN_Y);

        lb = new JoystickButton(this, RobotMap.Joystick.BTN_LB);
        rb = new JoystickButton(this, RobotMap.Joystick.BTN_RB);

        start = new JoystickButton(this, RobotMap.Joystick.BTN_START);
        back  = new JoystickButton(this, RobotMap.Joystick.BTN_BACK);
        
        lefJoystickButton = new JoystickButton(this, RobotMap.Joystick.BTN_LEFT_JOYSTICK);
        righJoystickButton = new JoystickButton(this, RobotMap.Joystick.BTN_RIGHT_JOYSTICK);
    }

    private double applyThreshold(double val) {
        return Math.abs(val) < joystickThreshold ? 0.0 : val;
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
        return this.getRawAxis(RobotMap.Joystick.LEFT_TRIGGER) > triggerThreshold;
    }

    public boolean rightTriggerPressed() {
        return this.getRawAxis(RobotMap.Joystick.RIGHT_TRIGGER) > triggerThreshold;
    }

    public double leftTrigger() {
        return this.getRawAxis(RobotMap.Joystick.LEFT_TRIGGER);
    }

    public double rightTrigger() {
        return this.getRawAxis(RobotMap.Joystick.RIGHT_TRIGGER);
    }

    public boolean dPadUp() {
        return pov() == 315 || pov() == 0 || pov() == 45;
    }

    public boolean dPadDown() {
        return pov() == 135 || pov() == 180 || pov() == 225;
    }

    public boolean dPadLeft() {
        return pov() == 225 || pov() == 270 || pov() == 315;
    }

    public boolean dPadRight() {
        return pov() == 45 || pov() == 90 || pov() == 135;
    }

    public double dPadVertical() {
        return dPadUp() ? 1 : dPadDown() ? -1 : 0;
    }

    public double dPadHorizontal() {
        return dPadRight() ? 1 : dPadLeft() ? -1 : 0;
    }
    private int pov() {
        return this.getPOV();
    }
}
