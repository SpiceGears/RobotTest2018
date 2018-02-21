package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropOnTime extends Command {
	
	long startTime = 0;
	double time;

    public DropOnTime(double time) {
        requires(Robot.intakeMotors);
    }

    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    protected void execute() {
    	Robot.intakeMotors.drop();
    }

    protected boolean isFinished() {
        return System.currentTimeMillis()-startTime > time*1000;
    }

    protected void end() {
    	Robot.intakeMotors.stop();
    }

    protected void interrupted() {
    	end();
    }
}
