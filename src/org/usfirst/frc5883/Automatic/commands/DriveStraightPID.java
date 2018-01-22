package org.usfirst.frc5883.Automatic.commands;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightPID extends PIDCommand {
	double p = 0;
	double i = 0;
	double d = 0;
	double setPoint = 0;
	double maxSpeed = 0;
   
    public DriveStraightPID(double p, double i, double d, double setPoint, double maxSpeed) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
		this.p = p;
		this.i = i;
		this.d = d;
		this.setPoint = setPoint;
		this.maxSpeed = maxSpeed;
    	requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoder();
    	Robot.driveTrain.resetGyro();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isDrove = false;
    	if(setPoint >= 0) {
        	isDrove = Robot.driveTrain.getDistanceInMeters() > setPoint;
        	}
    	else if(setPoint < 0) {
    		isDrove = Robot.driveTrain.getDistanceInMeters() < setPoint;
    	}
        return isDrove;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return (setPoint-Robot.driveTrain.getDistanceInMeters())*-1;
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("OutPut1", output);
		if(output > maxSpeed) {
			output = maxSpeed;
		} else if (output < -maxSpeed) {
			output = -maxSpeed;
		}
		Robot.driveTrain.setSpeedRobot(output);
	}
}
