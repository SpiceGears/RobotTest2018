package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDown extends Command {

    public IntakeDown() {
        requires(Robot.openintake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.openintake.downIntake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.openintake.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}
