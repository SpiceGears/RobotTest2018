package org.usfirst.frc5883.Automatic.commands.elevator;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorControl extends Command {

    public ElevatorControl() {
       requires(Robot.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.elevator.controlMotor();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.elevator.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}
