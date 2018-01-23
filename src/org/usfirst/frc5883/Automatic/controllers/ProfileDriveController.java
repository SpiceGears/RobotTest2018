package org.usfirst.frc5883.Automatic.controllers;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.motion.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ProfileDriveController implements DrivetrainController {
	Constants constants;
	
	double startT;
	double startTheta;
	TrapezoidalMotionProfile profile;
	
	double linearActual, linearSetpoint, linearError;
	public ProfileDriveController(TrapezoidalMotionProfile profile, double theta) {
		constants = Constants.getConstants();
		
		
		this.profile = profile;
		refreshConstants();
		start(theta);
	}

	/*
	 * Configure this controller to begin following the profile, making t=0 the current moment.
	 * @param theta The desired angle for drivestraight correction
	 */
	public void start(double theta) {
		startT = Timer.getFPGATimestamp();
		this.startTheta = theta;
	}
	
	@Override
	public boolean update() {
		double t = Timer.getFPGATimestamp() - startT;
		ProfilePoint point = profile.getAtTime(t);
		
		double feedforward = (point.vel * constants.DriveProfile_kV) + (point.acc * constants.DriveProfile_kA);
		
		linearActual = Robot.driveTrain.getDistanceInMeters();
		linearSetpoint = point.pos;
		double error = linearSetpoint-linearActual;
		double output = (error * constants.DriveProfile_kP) +  feedforward;
		
		linearError = error;
		double turn = (Robot.driveTrain.getAngle() -startTheta)*constants.DriveProfile_theta_kP;
		Robot.driveTrain.setTankDrive(output-turn, output+turn);
		SmartDashboard.putNumber("Output-turn", output-turn);
		SmartDashboard.putNumber("Output+turn", output+turn);
		
		return t >= profile.getDuration() && Math.abs(error) < constants.AllowedDriveError;
	}

	public void reset() {
		startT = Timer.getFPGATimestamp();
	}

	@Override
	public void refreshConstants() {
	}

	@Override
	public double getLinearError() {
		return linearError;
	}

	@Override
	public double getLinearActual() {
		return linearActual;
	}

	@Override
	public double getLinearSetpoint() {
		return linearSetpoint;
	}

	@Override
	public double getAngularError() {
		return 0;
	}

	@Override
	public double getAngularActual() {
		return 0;
	}

	@Override
	public double getAngularSetpoint() {
		return 0;
	}
}

