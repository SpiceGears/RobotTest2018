package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOpenControll extends Command {

    public IntakeOpenControll() {
        requires(Robot.openintake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.openintake.controlMotorWithJoystick();
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
