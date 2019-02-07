/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controller;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CustomJoystick extends Joystick {

    static final double threshold = 0.05;

    public CustomJoystick(final int port) {
        super(port);
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

    // continue adding more methods for each necessary button
    // add by returning this.getButton(RobotMap.Joystick.{port number})
}
