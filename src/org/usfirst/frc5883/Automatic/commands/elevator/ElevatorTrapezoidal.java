package org.usfirst.frc5883.Automatic.commands.elevator;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.controllers.ElevatorController;
import org.usfirst.frc5883.Automatic.motion.TrapezoidalMotionProfile;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorTrapezoidal extends Command {
	
//	private ElevatorController elevatorController;
//	TrapezoidalMotionProfile profile;
//	private Constants constants;
//	public double setPoint = -0.001;
	
    public ElevatorTrapezoidal() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    //	constants = new Constants();
    }

    protected void execute() {
    	
    	Robot.elevator.update();
    	
//    	if(setPoint == Robot.elevator.getSetPoint()) {
//    		elevatorController.update();
//        	
//    	} else {
//    		setPoint = Robot.elevator.getSetPoint();
//    		profile = new TrapezoidalMotionProfile(setPoint, constants.ElevatorProfile_MaxV, constants.ElevatorProfile_MaxA);
//    		elevatorController = new ElevatorController(profile);
//       	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.elevator.stopMotor();
    }

    protected void interrupted() {
    	end();
    }

//	public ElevatorController getElevatorController() {
//		return elevatorController;
//	}
//    
    
}
