package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveThreeMeter extends CommandGroup {

    public DriveThreeMeter() {
    	
    	addSequential(new WaitCommand(8));

    	addSequential(new ProfileTurnCommand(35, 60, 30));

        addSequential(new DriveDistance(3, 4, 1, 0.3));
    }
}
