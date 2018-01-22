package org.usfirst.frc5883.Automatic.commands;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SpeedCommand extends Command {
    double setPoint = 0;
    double speed = 0;
    double maxSpeed = 0;
    public SpeedCommand(double setPoint, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.setPoint = setPoint;
    	this.speed = speed;
    	this.maxSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoder();
    	Robot.driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.getDistanceInMeters() >= setPoint-0.1*setPoint) {
    		SmartDashboard.putNumber("Distance", Robot.driveTrain.getDistanceInMeters());
    	    speed = (setPoint-Robot.driveTrain.getDistanceInMeters());
    	    if(speed <= 0) {
    	    	speed = 0;
    	    }
    	    else if(speed > maxSpeed){
    	    	speed = maxSpeed;
    	    }
    	}
    	Robot.driveTrain.setSpeedRobot(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isDrove = Robot.driveTrain.getDistanceInMeters() >= setPoint;
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
}
