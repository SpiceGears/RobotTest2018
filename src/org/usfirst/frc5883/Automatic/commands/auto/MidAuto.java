package org.usfirst.frc5883.Automatic.commands.auto;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.commands.drivetrain.DriveDistance;
import org.usfirst.frc5883.Automatic.commands.drivetrain.ProfileTurnCommand;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetSwitch;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.DownIntakeTime;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.DropOnTime;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeDown;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeMotorIntake;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.UpIntakeTime;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MidAuto extends CommandGroup {
	

    public MidAuto() {
    	
    	double turnM = Constants.getConstants().turnTrapezoidalMultiply;
    	
    	String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.length() > 0) {
    		
    		if(gameData.charAt(0) == 'L') {
    			
    	addParallel(new ElevatorSetSwitch());
    	addParallel(new CloseClaw());
    	addParallel(new DownIntakeTime(1.2));
    	//DO LEFT SWITCH
    	addSequential(new DriveDistance(0.3, 4, 4, 1));
    	addSequential(new ProfileTurnCommand(-35, 60, 30));
    	
    	// CHANGE 15-03-2018
    	
    	addSequential(new DriveDistance(2.4 + 0.3, 4, 4, 0.5));
    	
    	//DROP
//    	addParallel(new DropMotors());
//    	addSequential(new WaitCommand(0.5));
//    	
    	addParallel(new OpenClaw());
    	addParallel(new DownIntakeTime(1.2));
    	
      	
    	// CHANGE 15-03-2018
    	
    	addSequential(new DriveDistance(-2.5, 4, 4, 0.5));
    	addSequential(new ElevatorSetIntake());
    //turn to scale, 
    	addSequential(new ProfileTurnCommand(15, 20, 25));

    	
//    	addSequential(new DriveDistance(-0.6, 4, 4, 0.5));
//    	addParallel(new ElevatorSetIntake());
//    	addParallel(new IntakeMotorIntake());
//    	addSequential(new ProfileTurnCommand(90, 60 * turnM, 30 * turnM));
//    	addSequential(new DriveDistance(2, 4, 4, 0.5));
//    	
//    	
//    	addSequential(new ProfileTurnCommand(110, 60 * 2 * turnM , 60 * turnM));
//    	addSequential(new DriveDistance(-2.4, 4, 4, 0.5));
//    	addSequential(new ElevatorSetIntake());
//    	addSequential(new ProfileTurnCommand(80, 60, 30));
//    	
//      	addSequential(new DriveDistance(1.35, 3, 4, 0.5));
//      	addSequential(new ProfileTurnCommand(-90, 60, 60));
//    	
//    	//INTAKE
//    	addParallel(new CloseClaw());
//    	
//    	
//   	addSequential(new ProfileTurnCommand(-155, 60, 60));
//    	addParallel(new UpIntakeTime(0.55));
//    	addSequential(new DriveDistance(1.55, 3, 4, 0.5));
//    	
//    	//DROP
//    	addParallel(new DropMotors());
    	
    }else if(gameData.charAt(0) == 'R') {	
    	addParallel(new ElevatorSetSwitch());
    	addParallel(new CloseClaw());
    	addParallel(new DownIntakeTime(1.2));
    	//DO RIGHT SWITCH
    	addSequential(new DriveDistance(0.3, 4, 4, 1));
    	addSequential(new ProfileTurnCommand(30, 20 * turnM, 25 * turnM));
    	addSequential(new DriveDistance(2.5, 8, 6, 0.5));
    	
		//DROP
    	//addParallel(new DropMotors());
    	//addSequential(new WaitCommand(0.2));
    	
    	addParallel(new OpenClaw());
    	addParallel(new DownIntakeTime(1.2));
    	
    	
//    	// CHANGE 15-03-2018
//    	
//    	addSequential(new DriveDistance(-0.6, 4, 4, 0.5));
//    	addParallel(new ElevatorSetIntake());
//    	addParallel(new IntakeMotorIntake());
//    	addSequential(new ProfileTurnCommand(-90, 60 * turnM, 30 * turnM));
//    	addSequential(new DriveDistance(2, 4, 4, 0.5));
    	
    	
    	
    	addSequential(new DriveDistance(-2.5, 4, 4, 0.5));
    	addSequential(new ElevatorSetIntake());
    //turn to scale, 
    	addSequential(new ProfileTurnCommand(-15, 20, 25));
//      	addSequential(new DriveDistance(1.45, 6, 6, 1.5));
//      	addSequential(new ProfileTurnCommand(90, 60, 60));
//    	
//    	//INTAKE
//    	addParallel(new CloseClaw());
//      	addSequential(new DriveDistance(1.45, 6, 6, 1.5));
//    	
//    	
//    	addSequential(new ProfileTurnCommand(-155, 60, 60));
//    	addParallel(new UpIntakeTime(0.55));
//    	addSequential(new DriveDistance(1.40, 3, 4, 1));
//    	
//    	//DROP
//    	addSequential(new DropMotors());
    	}
    }
    }
}
