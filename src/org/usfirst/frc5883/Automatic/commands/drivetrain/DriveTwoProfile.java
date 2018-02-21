package org.usfirst.frc5883.Automatic.commands.drivetrain;

import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.controllers.LinearAngularFollowerController;
import org.usfirst.frc5883.Automatic.motion.TrapezoidalMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTwoProfile extends Command {

	TrapezoidalMotionProfile linear;
	TrapezoidalMotionProfile angular;
	
	LinearAngularFollowerController controller;
	
	public DriveTwoProfile(TrapezoidalMotionProfile linear, TrapezoidalMotionProfile angular) {
		requires(Robot.driveTrain);
		this.linear = linear;
		this.angular = angular;
	}
	
	public DriveTwoProfile(TrapezoidalMotionProfile linear, TrapezoidalMotionProfile angular, double timeout) {
		this(linear, angular);
		setTimeout(timeout);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncoder();
		controller = new LinearAngularFollowerController(linear, angular);
		
		Robot.driveTrain.setController(controller);
	}
	
	@Override
	protected boolean isFinished() {
		return controller.update() || isTimedOut();
	}
	
	protected void end() {
		Robot.driveTrain.setController(null);
		Robot.driveTrain.setTankDrive(0, 0);
	}
	
	public DriveTwoProfile timeout(double t) {
		setTimeout(t);
		return this;
	}
}
