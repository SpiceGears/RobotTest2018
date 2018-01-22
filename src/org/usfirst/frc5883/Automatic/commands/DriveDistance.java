package org.usfirst.frc5883.Automatic.commands;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	private double metersToDrive = 0.5;
	
    public DriveDistance(double metersToDrive) {
    	this.metersToDrive = metersToDrive;
    }


    protected void initialize() {
    	
    	Robot.driveTrain.resetEncoder();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.getDistanceInMeters() >= metersToDrive;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
