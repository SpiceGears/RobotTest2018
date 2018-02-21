package org.usfirst.frc5883.Automatic.commands.elevator;


import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorPID extends PIDCommand {
	
	Constants constans;
	public double setPoint = 0;
	
    public ElevatorPID() {
		super(Constants.getConstants().elevator_kP, Constants.getConstants().elevator_kI, Constants.getConstants().elevator_kD);
		requires(Robot.elevator);
		constans = Constants.getConstants();
		setPoint = Robot.elevator.getSetPoint();
		
	}

    protected void initialize() {
    	
    	SmartDashboard.putNumber("Number", 2);
    }

    protected void execute() {
    	Robot.elevator.update();
    	setPoint = Robot.elevator.getSetPoint();
    	
    	SmartDashboard.putNumber("setPoint Elevator", setPoint);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.elevator.setMotorPower(0);
    }

    protected void interrupted() {
    	end();
    }

	protected double returnPIDInput() {
		
		double error = setPoint-Robot.elevator.getDistane();
		SmartDashboard.putNumber("error PID elevator", error);
		
		return error;
	}

	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Output_Elevator", output);
		Robot.elevator.setMotorPower(output);
	}
}
