package org.usfirst.frc5883.Automatic.subsystems;

import org.usfirst.frc5883.Automatic.PortMap;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeControl;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeMotors extends Subsystem {
	
	 private final VictorSP intakeMotorleft = new VictorSP(PortMap.leftIntakeMotor);
	 private final VictorSP intakeMotorRight = new VictorSP(PortMap.rightIntakeMotor);


    public void initDefaultCommand() {
    	stop();
       // setDefaultCommand(new IntakeControl());
    }
    
    public void controlWithJoystick() {
    	double speed = 0;
    	if(Math.abs(Robot.oi.getOperatorJoystick().getRawAxis(1)) > 0.5) {
    		speed = Robot.oi.getOperatorJoystick().getRawAxis(1);
    	} else {
    		speed = 0;
    	}
    	
    	
    	intakeMotorleft.set(speed);
    	intakeMotorRight.set(-speed);
    	
    }
    public void intake() {
    	intakeMotorleft.set(-1);
    	intakeMotorRight.set(1);
    }
    
    public void drop() {
    	intakeMotorleft.set(1);
    	intakeMotorRight.set(-1);
    }
    
    public void stop() {
    	intakeMotorleft.set(0);
    	intakeMotorRight.set(0);
    }
}

