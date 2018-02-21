package org.usfirst.frc5883.Automatic.framework;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	
   Joystick joystick;
   int axis;
   double wychylenie;
   
   public AxisButton(Joystick joystick, int axis, double wychylenie) {
       this.joystick = joystick;
       this.axis = axis;
       this.wychylenie = wychylenie;
   }
   
   public boolean get() {
       if ( wychylenie > 0 ) {
           if ( joystick.getRawAxis(axis) > wychylenie )
               return true;
       } else if ( wychylenie < 0 ) {
           if ( joystick.getRawAxis(axis) < wychylenie)
               return true;
       }
       return false;
   }
   
}
