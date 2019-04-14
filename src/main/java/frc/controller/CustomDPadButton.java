/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controller;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Add your docs here.
 */
public class CustomDPadButton extends Button {
    
    private CustomDPad dpad;
    private int pov1 = Integer.MAX_VALUE;
    private int pov2 = Integer.MAX_VALUE;
    private int pov3 = Integer.MAX_VALUE;

    public CustomDPadButton(CustomDPad dpad, int pov1) {
        this.dpad = dpad;
        this.pov1 = pov1;
    }
    
    public CustomDPadButton(CustomDPad dpad, int pov1, int pov2, int pov3) {
        this.dpad = dpad;
        this.pov1 = pov1;
        this.pov2 = pov2;
        this.pov3 = pov3;
    }

    @Override
    public boolean get() {
        return dpad.pov() == pov1 ||  dpad.pov() == pov2 || dpad.pov() == pov3;
    }
}
