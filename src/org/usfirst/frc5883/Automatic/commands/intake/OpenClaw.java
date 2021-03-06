package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {

    public OpenClaw() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.solenoidClose();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.solenoidOff();
    }

    protected void interrupted() {
    	end();
    }
}
