package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropMotors extends Command {

	double speed = 1;
	
    public DropMotors() {
        requires(Robot.intakeMotors); 
        speed = 1;
    }
    
    public DropMotors(double speed) {
    	this.speed = speed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	 Robot.intakeMotors.drop(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intakeMotors.stop();
    }

    protected void interrupted() {
    	end();
    }
}
