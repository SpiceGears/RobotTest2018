package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetScale;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.DownIntakeTime;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.UpIntakeTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GoToOppositeScale extends CommandGroup {

    public GoToOppositeScale(boolean isLeft) {
    	double needFlip = 1;
    	if(isLeft) {
    		needFlip = -1;
    	}
    	
        addSequential(new DriveDistance(5.4, 5, 5, 0.7));
        addSequential(new ProfileTurnCommand(-90*needFlip, 60, 50));
        addParallel(new ElevatorSetScale());
        addParallel(new DownIntakeTime(1));
        addSequential(new DriveDistance(4.5, 5, 5, 0.3));
        addSequential(new ProfileTurnCommand(90*needFlip, 60, 50));
        addSequential(new DriveDistance(0.55, 5, 5, 0.1));

        addParallel(new DropMotors());
        addSequential(new WaitCommand(1));
        addSequential(new UpIntakeTime(0.5));
        addSequential(new DriveDistance(-0.4, 1, 1, 0.5));
        addParallel(new ElevatorSetIntake());
        
        
    }
}
