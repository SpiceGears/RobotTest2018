package org.usfirst.frc5883.Automatic.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndBack extends CommandGroup {

    public DriveAndBack() {
        addSequential(new DriveStraightPID(0.2, 0.02, 30, 4, 1));
        addSequential(new DriveStraightPID(-0.2, 0.02, 30, -4, -0.5));
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
