
package org.usfirst.frc5883.Automatic.subsystems;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.PortMap;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorControl;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetScale;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorTrapezoidal;
import org.usfirst.frc5883.Automatic.controllers.ElevatorController;
import org.usfirst.frc5883.Automatic.motion.TrapezoidalMotionProfile;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	
	public Elevator() {
		
		elevatorMotor = new Spark(PortMap.elevatorMotor);
    	encoder = new Encoder(PortMap.elevatorEncoderA, PortMap.elevatorEncoderB);
    	encoder.setDistancePerPulse(0.00005);
    	encoder.setReverseDirection(false);
    	encoder.reset();
    	//SmartDashboard.putData("Encoder elevator", encoderRight);
	}

	public enum Mode { 
		INTAKE,
		SWITCH,
		SCALE,
		CLIMBING
	}
	
	private Mode elevatorMode = Mode.INTAKE;
	private Spark elevatorMotor;
	private Encoder encoder;
	private Constants constants = Constants.getConstants();
	private double oldTime = 0;
	
	private ElevatorController elevatorController;
	private TrapezoidalMotionProfile profile;
	private double setPoint = -0.001;
	private double newSetPoint = constants.elevatorIntake;

	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorTrapezoidal());

	}
	
	public void update(){
		
		
		SmartDashboard.putNumber("setpoint elevator", setPoint);
		SmartDashboard.putNumber("ELEVATOR ENCODER ODCZYT", getDistane());
		
		
		
    	if(newSetPoint == setPoint) {
    		elevatorController.update();
    	} else {
    		setPoint = newSetPoint;
    		profile = new TrapezoidalMotionProfile(setPoint, constants.ElevatorProfile_MaxV, constants.ElevatorProfile_MaxA);
    		elevatorController = new ElevatorController(profile);
    	}
    	
    	if(Robot.oi.getOperatorJoystick().getRawAxis(2) != 0) {
			newSetPoint = newSetPoint + Robot.oi.getOperatorJoystick().getRawAxis(2)/200;
		}else if(Robot.oi.getOperatorJoystick().getRawAxis(3) != 0) {
			newSetPoint = newSetPoint - Robot.oi.getOperatorJoystick().getRawAxis(3)/200;
		}else {
			//newSetPoint = setPoint;
		}
		
		switch(elevatorMode) {
		case SWITCH:
			newSetPoint = constants.elevatorSwitch;
			break;
		case INTAKE:
			newSetPoint = constants.elevatorIntake;
			break;
		case SCALE:
			newSetPoint = constants.elevatorScale;
			break;
		default:
			//setPoint = constants.elevatorIntake;
			break;
		}
		

		
	}
	
	//Manual Control
    public void controlMotor() {
    	
    	double speed = Robot.oi.getOperatorJoystick().getRawAxis(3);
    	
    	if (speed > 0) {
    		speed *= 1;
    	}
    	
    	if(encoder.getDistance() < -0.00) {
    		speed = -Math.abs(speed);
    		
    	}
    	
    	
    	elevatorMotor.set(speed);
    }
    
    public void stopMotor() {
    	elevatorMotor.set(0);
    }
    
    public void setMotorPower(double power) {
    	//SmartDashboard.putNumber("Elevator Speed", encoderRight.getRate());
    	elevatorMotor.set(power);
    }
    
    public double speedElevator = 0;
    public void setSpeedElevator(double speed) {
    	
    	double newTime = System.currentTimeMillis();
    	double deltaTime = newTime - oldTime;
    	
    	//BANG_BANG CONTROLLER
    	if(encoder.getRate() < speed) {
    		speedElevator +=  0.05;
    	} else if(encoder.getRate() > speed) {
    		speedElevator -=  0.05;
    	}
    	if(speedElevator > 1) {
    		speedElevator = 1;
    	} else if(speedElevator < -1) {
    		speedElevator = -1;
    	}
    	
    	
    	//(deltaTime/50) *
    	
    	//SmartDashboard.putNumber("set speed", speedElevator);
    	
    	elevatorMotor.set(-speedElevator);
    	
    	oldTime = System.currentTimeMillis();
    }
    
    //Encoder
	public double getDistane() {
	    return encoder.getDistance();
	}
       
    public void resetEncoder() {
    	encoder.reset();
    }   

	//Getters and setters
	public Mode getMode() {
		return elevatorMode;
	}

	public void setMode(Mode elevatorMode) {
		this.elevatorMode = elevatorMode;
	}

	public double getSetPoint() {
		return setPoint;
	}

	public void setSetPoint(double setPoint) {
		this.setPoint = setPoint;
	}
	
}