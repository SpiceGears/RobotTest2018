package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeUp extends Command {

    public IntakeUp() {
        requires(Robot.openintake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.openintake.upIntake();
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
