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
public class CustomDPad {

    private Joystick joy;

    public CustomDPadButton u;
    public CustomDPadButton r;
    public CustomDPadButton d;
    public CustomDPadButton l;

    private void initButtons() {
        u = new CustomDPadButton(this, 315, 0  , 45 );
        r = new CustomDPadButton(this, 45 , 90 , 135);
        d = new CustomDPadButton(this, 135, 180, 225);
        l = new CustomDPadButton(this, 225, 270, 315);
    }

    public CustomDPad(int port) {
        joy = new Joystick(port);
        initButtons();
    }

    public CustomDPad(Joystick joy) {
        this.joy = joy;
        initButtons();
    }

    public int pov() {
        return joy.getPOV();
    }

    public boolean isUp() {
        return u.get();
    }

    public boolean isDown() {
        return d.get();
    }

    public boolean isLeft() {
        return l.get();
    }

    public boolean isRight() {
        return r.get();
    }

    public double vertical() {
        return isUp() ? 1 : isDown() ? -1 : 0;
    }

    public double horizontal() {
        return isRight() ? 1 : isLeft() ? -1 : 0;
    }
}
