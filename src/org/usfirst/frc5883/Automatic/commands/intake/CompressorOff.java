package org.usfirst.frc5883.Automatic.commands.intake;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CompressorOff extends Command {

    public CompressorOff() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.compressorOFF();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.compressorOn();
    }

    protected void interrupted() {
    	end();
    }
}
