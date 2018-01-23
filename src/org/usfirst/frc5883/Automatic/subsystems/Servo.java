package org.usfirst.frc5883.Automatic.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Servo extends Subsystem {

	  edu.wpi.first.wpilibj.Servo servo = new edu.wpi.first.wpilibj.Servo(9);

    public void initDefaultCommand() {
    	servo.set(0.5);
    }
    
    public void setServoOpen() {
    	servo.set(0.80);
    }
    
    public void setServoClose() {
    	servo.set(0.20);
    }
    
    
}

