package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveThreeMeter extends CommandGroup {

    public DriveThreeMeter() {
        addSequential(new DriveDistance(3, 4, 1, 0.3));
    }
}
