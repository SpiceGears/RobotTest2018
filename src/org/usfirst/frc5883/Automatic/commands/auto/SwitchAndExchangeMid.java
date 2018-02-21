package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetSwitch;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.DropOnTime;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeDown;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.UpIntakeTime;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SwitchAndExchangeMid extends CommandGroup {
	

    public SwitchAndExchangeMid() {
    	
    	String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.length() > 0) {
    		
    		if(gameData.charAt(0) == 'L') {
    	addParallel(new ElevatorSetSwitch());
    	addParallel(new CloseClaw());
    	addParallel(new IntakeDown());
    	//DO LEFT SWITCH
    	addSequential(new DriveDistance(0.3, 4, 4, 1));
    	addSequential(new ProfileTurnCommand(-30, 60, 30));
    	addSequential(new DriveDistance(2.4, 4, 4, 0.5));
    	
    	//DROP
    	addParallel(new OpenClaw());
      	
    	addSequential(new DriveDistance(-2.4, 4, 4, 0.5));
    	addSequential(new ElevatorSetIntake());
    	addSequential(new ProfileTurnCommand(25, 60, 30));
    	addSequential(new DriveDistance(1.35, 3, 4, 0.5));
    	
    	
    	//INTAKE
    	addParallel(new CloseClaw());
    	
    	
    	addSequential(new ProfileTurnCommand(-155, 60, 60));
    	addParallel(new UpIntakeTime(0.55));
    	addSequential(new DriveDistance(1.55, 3, 4, 0.5));
    	
    	//DROP
    	addParallel(new DropMotors());
    	
    }else if(gameData.charAt(0) == 'R') {	
    	addParallel(new ElevatorSetSwitch());
    	addParallel(new CloseClaw());
    	addParallel(new IntakeDown());
    	//DO RIGHT SWITCH
    	addSequential(new DriveDistance(0.3, 4, 4, 1));
    	addSequential(new ProfileTurnCommand(25, 60, 30));
    	addSequential(new DriveDistance(2.5, 8, 6, 0.5));
    	
		//DROP
    	addParallel(new OpenClaw());
	
    	addSequential(new DriveDistance(-2.5, 8, 5, 0.5));
    	addSequential(new ElevatorSetIntake());
    	addSequential(new ProfileTurnCommand(-25, 60, 30));
    	addSequential(new DriveDistance(1.45, 6, 6, 1.5));
    	
    	//INTAKE
    	addParallel(new CloseClaw());
    	
    	
    	addSequential(new ProfileTurnCommand(-155, 60, 60));
    	addParallel(new UpIntakeTime(0.55));
    	addSequential(new DriveDistance(1.40, 3, 4, 1));
    	
    	//DROP
    	addSequential(new DropMotors());
    	}
    }
    }
}
