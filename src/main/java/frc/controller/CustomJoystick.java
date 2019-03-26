/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class CustomJoystick extends Joystick {

    private static final double joystickThreshold = 0.05;

    public JoystickButton trigger;
    public JoystickButton b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;

    public CustomJoystick(final int port) {
        super(port);

        trigger = new JoystickButton(this, 1);
        b2  = new JoystickButton(this, 2);
        b3  = new JoystickButton(this, 3);
        b4  = new JoystickButton(this, 4);
        b5  = new JoystickButton(this, 5);
        b6  = new JoystickButton(this, 6);
        b7  = new JoystickButton(this, 7);
        b8  = new JoystickButton(this, 8);
        b9  = new JoystickButton(this, 9);
        b10 = new JoystickButton(this, 10);
        b11 = new JoystickButton(this, 11);
        b12 = new JoystickButton(this, 12);
    }

    private double applyThreshold(double val) {
        return Math.abs(val) < joystickThreshold ? 0.0 : val;
    }

    public double xAxis() {
        return applyThreshold(getRawAxis(0));
    }

    public double yAxis() {
        return applyThreshold(getRawAxis(1));
    }

    public double zAxis() {
        return applyThreshold(getRawAxis(2));
    }

    public double slider() {
        return applyThreshold(getRawAxis(3));
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