package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpIntakeTime extends Command {
	
	long startTime = 0;
	double time;

    public UpIntakeTime(double time) {
        requires(Robot.openintake);
        this.time = time;
    }

    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    protected void execute() {
    	Robot.openintake.upIntake();
    }

    protected boolean isFinished() {
        return System.currentTimeMillis()-startTime > time*1000;
    }

    protected void end() {
    	Robot.openintake.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}
