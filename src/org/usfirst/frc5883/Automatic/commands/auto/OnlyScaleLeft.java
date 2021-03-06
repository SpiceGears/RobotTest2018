package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetScale;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetSwitch;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.DownIntakeTime;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeMotorIntake;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.UpIntakeTime;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OnlyScaleLeft extends CommandGroup {

    public OnlyScaleLeft() {
    	String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();

    	double turnM = Constants.getConstants().turnTrapezoidalMultiply;

    	if(gameData.charAt(1) == 'L') {
			addParallel(new ElevatorSetScale());
			addParallel(new CloseClaw());
	    	addParallel(new DownIntakeTime(1.25));
	    	
	    	addSequential(new DriveDistance(6.17, 7, 5, 0.5));
	    	
	    	addSequential(new ProfileTurnCommand(30, 70 * turnM, 70 * turnM));
	    	addParallel(new DropMotors());
	    	addSequential(new ProfileTurnCommand(115, 120 * turnM, 75 * turnM));
	    	
	    	addParallel(new IntakeMotorIntake());
	    	addParallel(new DownIntakeTime(1));
	    	addParallel(new ElevatorSetIntake());
	    	addParallel(new OpenClaw());

	    	//Pobranie 2 cuba Very dobzie
	    	//Podjazd do cuba
	    	addSequential(new DriveDistance(1.35, 1.7, 0.5, 0.15));
	    	addParallel(new CloseClaw());
	    	
	    	
	    	if(gameData.charAt(0)== 'L') {
    	    	
     			addParallel(new ElevatorSetSwitch());
     			addParallel(new UpIntakeTime(0.5));
     			addSequential(new WaitCommand(0.9));
     			addParallel(new DriveDistance(0.30, 1, 1, 1));
    	    	addParallel(new DropMotors());
    	    	addSequential(new WaitCommand(0.5));
    	    	addParallel(new OpenClaw());
    	    	
    	    } 
    	} else if(gameData.charAt(1) == 'R'){
			//DO LEFT SCALE FROM RIGHT
	    	  
			addSequential(new GoToOppositeScale(true));
			
			//Drop
			addSequential(new OpenClaw());
	        
	        
		} else {
			addSequential(new DriveDistance(3, 7, 5, 0.5));

		}
    	
	    	
    }
    
}
