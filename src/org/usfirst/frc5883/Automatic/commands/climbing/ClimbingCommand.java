package org.usfirst.frc5883.Automatic.commands.climbing;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbingCommand extends Command {

    public ClimbingCommand() {
        requires(Robot.climbing);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climbing.climbing();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.climbing.stopClimbing();
    }

    protected void interrupted() {
    	end();
    }
}
