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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FocusSwitchSide extends CommandGroup {

    public FocusSwitchSide(boolean isLeft) {
    	String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();

    if(!isLeft) {
    	//RIGHT SIDE
    	if (gameData.charAt(0)== 'R') {
			addParallel(new ElevatorSetScale());
			addParallel(new CloseClaw());
	    	addParallel(new DownIntakeTime(1.25));
	    	
			addSequential(new DriveDistance(2.6, 5, 5, 0.6));
			addSequential(new ProfileTurnCommand(-45, 60, 70));
			
			//Drop
			
			addParallel(new DropMotors());
			addSequential(new WaitCommand(0.7));
	    	addParallel(new DownIntakeTime(1.25));
	    	addParallel(new OpenClaw());

			addSequential(new ProfileTurnCommand(45, 60, 70));
			addParallel(new ElevatorSetIntake());
			addSequential(new DriveDistance(2.7, 5, 5, 0.7));
			addSequential(new ProfileTurnCommand(-135, 60, 70));
			
			//Intake
			addSequential(new DriveDistance(0.8, 5, 5, 1));
			addParallel(new CloseClaw());
    	} else if (gameData.charAt(0)== 'L') {
    		
    		addSequential(new DriveDistance(5.4, 5, 5, 0.7));
	        addSequential(new ProfileTurnCommand(-90, 60, 50));
	        addParallel(new ElevatorSetScale());
	        addParallel(new DownIntakeTime(1));
	        addSequential(new DriveDistance(4.1, 5, 5, 0.3));
	        addSequential(new ProfileTurnCommand(-90, 60, 50));
	        addSequential(new DriveDistance(0.4, 5, 3.5, 0.1));

	        addParallel(new DropMotors());
	        addSequential(new WaitCommand(1));
	        addSequential(new UpIntakeTime(0.5));
	        addSequential(new DriveDistance(-0.1, 1, 1, 0.5));
	        addParallel(new ElevatorSetIntake());
    		
    	}
    } else {
    	
    	//LEFT SIDE
    	if (gameData.charAt(0)== 'L') {
			addParallel(new ElevatorSetScale());
			addParallel(new CloseClaw());
	    	addParallel(new DownIntakeTime(1.25));
	    	
			addSequential(new DriveDistance(2.6, 5, 5, 0.6));
			addSequential(new ProfileTurnCommand(45, 60, 70));
			
			//Drop
			
			addParallel(new DropMotors());
			addSequential(new WaitCommand(0.7));
	    	addParallel(new DownIntakeTime(1.25));
	    	addParallel(new OpenClaw());

			addSequential(new ProfileTurnCommand(-45, 60, 70));
			addParallel(new ElevatorSetIntake());
			addSequential(new DriveDistance(2.7, 5, 5, 0.7));
			addSequential(new ProfileTurnCommand(135, 60, 70));
			
			//Intake
			addSequential(new DriveDistance(0.8, 5, 5, 1));
			addParallel(new CloseClaw());
			
    	} else if (gameData.charAt(0)== 'R') {
    			//
    			addSequential(new DriveDistance(5.4, 5, 5, 0.7));
    	        addSequential(new ProfileTurnCommand(90, 60, 50));
    	        addParallel(new ElevatorSetScale());
    	        addParallel(new DownIntakeTime(1));
    	        addSequential(new DriveDistance(4.1, 5, 5, 0.3));
    	        addSequential(new ProfileTurnCommand(90, 60, 50));
    	        addSequential(new DriveDistance(0.4, 5, 3.5, 0.1));

    	        addParallel(new DropMotors());
    	        addSequential(new WaitCommand(1));
    	        addSequential(new UpIntakeTime(0.5));
    	        addSequential(new DriveDistance(-0.1, 1, 1, 0.5));
    	        addParallel(new ElevatorSetIntake());
    	        
    	}
    }
}
}
