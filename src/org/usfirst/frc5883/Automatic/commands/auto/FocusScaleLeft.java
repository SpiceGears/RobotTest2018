package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetScale;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetSwitch;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.DownIntakeTime;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeDown;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeMotorIntake;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.UpIntakeTime;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FocusScaleLeft extends CommandGroup {

	
    public FocusScaleLeft(){
    	
    	String gameData;
    	gameData = Constants.getConstants().getGameData();
    	
    	if(gameData.length() > 0)
    	{
    		//LEFT DO SCALE 
    		if(gameData.charAt(1)== 'L') {
    			addParallel(new ElevatorSetScale());
    			addParallel(new CloseClaw());
    	    	addParallel(new DownIntakeTime(1.25));
    	    	
    	    	addSequential(new DriveDistance(6, 7, 5, 0.5));
    	    	
    	    	addSequential(new ProfileTurnCommand(30, 70, 90));
    	    	addParallel(new DropMotors());
    	    	addSequential(new ProfileTurnCommand(115, 120, 75));
    	    	
    	    	addParallel(new IntakeMotorIntake());
    	    	addParallel(new DownIntakeTime(1));
    	    	addParallel(new ElevatorSetIntake());
    	    	addParallel(new OpenClaw());

    	    	//Pobranie 2 cuba Very dobzie
    	    	//Podjazd do cuba
    	    	addSequential(new DriveDistance(1.35, 1.7, 0.5, 0.15));
    	    	addParallel(new CloseClaw());
   			
    	    	} 

    			else if (gameData.charAt(1)== 'R') {
    				
    				addSequential(new GoToOppositeScale(true));
    			
    			//Drop
    				addSequential(new OpenClaw());
    		}
    	}
    }
}