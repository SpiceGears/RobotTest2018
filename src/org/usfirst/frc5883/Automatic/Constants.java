package org.usfirst.frc5883.Automatic;

import java.io.File;



public class Constants {
	
	private static Constants instance;
	private static boolean isPractice = false;
	private static double visionReferenceDistance = 77.0; // 80.0, calibration distance when the front of the bumpers is aligned with the boiler-side of the key tape
	
	public static Constants getConstants() {		
		if ( instance == null ) {
				instance = new Constants();
		}
		return instance;
	}
	
	public double DriveProfile_kV = 0.119;
	public double DriveProfile_kA = 0;
	public double DriveProfile_kP = 1;
	public double DriveProfile_theta_kP = 0.03;
	public double ToleranceDriveError = 0.05;
	
	public double Gyro_kP = 0.3;
	
	public double SpeedPIDkP = 0.8;
	public double SpeedPIDkI = 0.3;
	public double SpeedPIDkD = 0.000;
	public double SpeedPIDkF = 0.1;
	
	public double TurnProfile_kV = 0.02;
	public double TurnProfile_kA = 0;
	public double TurnProfile_kP = -0.059;
	
	public double TurnPID_kP = 0.045; // 0.059
	public double TurnPID_kI = 0.00000015; // 0
	public double TurnPID_kD = 0.0045; // 0.0045
	
	public double MaxDriveVelocity = 8000;
	
	public double LeftDriveVelocityP = 0.3;
	public double LeftDriveVelocityD = 0;
	public double LeftDriveVelocityF = 1023.0 / MaxDriveVelocity;
	
	public double RightDriveVelocityP = 0.3;
	public double RightDriveVelocityD = 0;
	public double RightDriveVelocityF = 1023.0 / MaxDriveVelocity;
	
	public double MaxDriveVelocityLow = 3000;
	
	public double LeftDriveVelocityPLow = 0;
	public double LeftDriveVelocityDLow = 0;
	public double LeftDriveVelocityFLow = 1023.0 / MaxDriveVelocityLow;

	public double RightDriveVelocityPLow = 0;
	public double RightDriveVelocityDLow = 0;
	public double RightDriveVelocityFLow = 1023.0 / MaxDriveVelocityLow;
	
	public double DriveRampRate = 120;
	
	
	public double PivotTurnPID_kP = 0.05;
	public double PivotTurnPID_kI = 0;
	public double PivotTurnPID_kD = 0.002;

	public double AllowedDriveError = 0.25; // 0.25
	public double AllowedTurnError = 1.8;
	public double AllowedTurnPIDError = 1.3;
	public double AllowedPivotTurnError = 4.0;

	
	
	public static class PracticeRobotConstants extends Constants {	
	}
	
	
	public static class ClosedLoopDriveParams {
		public double kP, kD, kF;
		public double maxVelocity;
		
		public ClosedLoopDriveParams(double kP, double kD, double kF) {
			this.kP = kP;
			this.kD = kD;
			this.kF = kF;
			maxVelocity = 1023.0 / kF;
		}
	}
}