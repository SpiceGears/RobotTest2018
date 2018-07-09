package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.Constants;
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
    	
    	double turnM = Constants.getConstants().turnTrapezoidalMultiply;

    	
        addSequential(new DriveDistance(5.4, 5, 5, 0.7));
        addSequential(new ProfileTurnCommand(-90*needFlip, 60 * turnM, 50 * turnM));
        
        addParallel(new DownIntakeTime(1));
        
       
        //No cross
        //addSequential(new DriveDistance(1.8, 5, 5, 0.3));
        //end
        
        
        addParallel(new ElevatorSetScale());
        addSequential(new DriveDistance(4.51, 5, 5, 0.3));
        addSequential(new ProfileTurnCommand(90*needFlip, 60 * turnM, 50 * turnM));
        addSequential(new DriveDistance(0.63 + 0.15, 5, 5, 0.1+ 0.15));

        addParallel(new DropMotors(0.6));
        addSequential(new WaitCommand(1));
        addParallel(new OpenClaw());
        addSequential(new UpIntakeTime(0.5));
        addSequential(new DriveDistance(-0.4, 1, 1, 0.5));
        addParallel(new ElevatorSetIntake());
        
        
    }
}
