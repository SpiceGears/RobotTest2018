package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeControl extends Command {

    public IntakeControl() {
        requires(Robot.intakeMotors);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intakeMotors.controlWithJoystick();
    	
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
