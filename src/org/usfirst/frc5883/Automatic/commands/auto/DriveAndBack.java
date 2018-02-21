package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndBack extends CommandGroup {

    public DriveAndBack() {
        addSequential(new DriveDistance(4, 1, .5, 0));
        addSequential(new DriveDistance(-4, 1, .5, 0));
      
    }
}
