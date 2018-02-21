package org.usfirst.frc5883.Automatic.subsystems;

import org.usfirst.frc5883.Automatic.PortMap;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeOpenControll;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OpenIntake extends Subsystem {
	
	private Spark openMotor = new Spark(PortMap.openIntakeMotor);
//	private DigitalInput upLimitSwitch = new DigitalInput(PortMap.limitSwitchUPOpenIntake);
	
	private long oldTime = 0;
	private double dTime = 0;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeOpenControll());
        
    }
    public void controlMotorWithJoystick() {
    	
    	openMotor.set(-Robot.oi.getOperatorJoystick().getRawAxis(1));
    }
    public void stopMotor() {
    	openMotor.set(0);
    }
    public void downIntake() {
    	openMotor.set(-1);
    }
    
    public void upIntake() {
//    	if(!upLimitSwitch.get()) {
    		openMotor.set(1);
//    	}else {
//    		stopMotor();
    		dTime = 0;
    	
    
    }
}
