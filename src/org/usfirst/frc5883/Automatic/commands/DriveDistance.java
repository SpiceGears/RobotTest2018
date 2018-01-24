package org.usfirst.frc5883.Automatic.commands;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.controllers.ProfileDriveController;
import org.usfirst.frc5883.Automatic.motion.TrapezoidalMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	TrapezoidalMotionProfile profile;
	double vf = 0;
	double distance;
	Constants constants;
	public DriveDistance(double distance, double maxV, double maxAcc) {
		requires(Robot.driveTrain);
		profile = new TrapezoidalMotionProfile(distance, maxV, maxAcc);
		this.distance = distance;
	}
	
	public DriveDistance(double distance, double maxV, double maxAcc, double vi, double vf) {
		requires(Robot.driveTrain);
		profile = new TrapezoidalMotionProfile(distance, maxV, maxAcc, vi, vf);
		this.vf = vf;
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncoder();
		ProfileDriveController controller = new ProfileDriveController(profile, Robot.driveTrain.getAngle());
		constants = Constants.getConstants();
		Robot.driveTrain.setController(controller);
		//Robot.driveTrain.setMode(Drivetrain.Mode.CONTROLLED);
	}

	@Override
	protected boolean isFinished() {
		
		return Robot.driveTrain.getDistanceInMeters() >= distance-constants.ToleranceDriveError;
	}
	
	protected void end() {
			Robot.driveTrain.setTankDrive(0, 0);
	}
	
	public DriveDistance timeout(double t) {
		setTimeout(t);
		return this;
	}
}
