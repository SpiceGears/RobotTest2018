package org.usfirst.frc5883.Automatic.commands.elevator;

import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.subsystems.Elevator.Mode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
/**
 *
 */
public class ElevatorSetSwitch extends InstantCommand {

    public ElevatorSetSwitch() {
        super();
        requires(Robot.elevator);
    }

    protected void initialize() {
        	Robot.elevator.setMode(Mode.SWITCH);
    }

}
