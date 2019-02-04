/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class CustomJoystick extends Joystick {

    static final double threshold = 0.05;

    public CustomJoystick(final int port) {
        super(port);
    }

    public double getScaledX() {
        double val = getX();
        return Math.abs(val) < threshold ? 0.0 : val;
    }

    public double getScaledY() {
        double val = getY();
        return Math.abs(val) < threshold ? 0.0 : val;
    }
}
