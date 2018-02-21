package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseClaw extends Command {

    public CloseClaw() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.solenoidOpen();
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
