
package org.usfirst.frc5883.Automatic.subsystems;

import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.Robot;
import org.usfirst.frc5883.Automatic.RobotMap;
import org.usfirst.frc5883.Automatic.commands.Drive;
import org.usfirst.frc5883.Automatic.controllers.DrivetrainController;
import org.usfirst.frc5883.Automatic.controllers.ProfileDriveController;
import org.usfirst.frc5883.Automatic.motion.SpeedPID;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {

    private final SpeedController speedController3 = RobotMap.driveTrainSpeedController3;
    private final SpeedController speedController0 = RobotMap.driveTrainSpeedController0;
    private final SpeedController speedController1 = RobotMap.driveTrainSpeedController1;
    private final SpeedController speedController2 = RobotMap.driveTrainSpeedController2;
    private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
    public final Encoder encoder = new Encoder(10, 11, true, EncodingType.k4X);
    public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    
    private Constants constants; 
    private long oldTime = 0;
    private double speed = 0;
    private double powerForSpeed = 0; 
    private boolean onTarget;
    private SpeedPID speedPID;
    private double turn = getAngle()* 0.05;
    DrivetrainController controller;
    
    public DriveTrain() {
    	
        speedPID = new SpeedPID();
    	encoder.setMaxPeriod(.1);
    	encoder.setMinRate(10);
    	encoder.setDistancePerPulse(1);
    	encoder.setReverseDirection(false);
    	encoder.setSamplesToAverage(7);
    	constants = Constants.getConstants();
    	SmartDashboard.putNumber("Dystans w metrach", getDistanceInMeters());

    }

    public void initDefaultCommand() {
       

    	setDefaultCommand(new Drive());

	}

    public double getDistanceInMeters(){
    	return encoder.getDistance()/4332;
    	
    }
    
    public void setSpeedRobot(double speed) {
        SmartDashboard.putNumber("Speed z Trapezu", speed);
        
        if (actualSpeed() < speed) {
        	powerForSpeed += 0.01;
        }else if (actualSpeed() >= speed) {
        	powerForSpeed -= 0.01;
        } else {
        	powerForSpeed = 0;
        }
        
        SmartDashboard.putNumber("Speed na silniki", speedPID.calculate(speed, actualSpeed()));
        
    	robotDrive41.arcadeDrive(speedPID.calculate(speed, actualSpeed()), gyro.getAngle()*constants.Gyro_kP);
        //robotDrive41.arcadeDrive(powerForSpeed, gyro.getAngle()*constants.Gyro_kP);
    }

    
    public void driveStraight(double speed, double dystansMeter){
    	
    	double angle = gyro.getAngle();
    	robotDrive41.arcadeDrive(speed, angle*0.08);
    	
    }
    
    public void driveWithJoysticks(){
    	
    	SmartDashboard.putNumber("encoder DriveTrain", encoder.getDistance());
    	long newTime = System.currentTimeMillis();
    	//SmartDashboard.putNumber("newTime", newTime);
    	double deltaTime = newTime - oldTime;
    	double joySpeedValue = Robot.oi.getJoystick1().getRawAxis(1);
    	 //SmartDashboard.putDouble("time new", newTime);
    	 SmartDashboard.putNumber("encoder", getDistanceInMeters());
    	 
    	 
    	 //This value reach 100% power motor in 0,5s
    	 double speedAddicional = 0.1;
    	 speedAddicional = (deltaTime/50) * speedAddicional;
    	 
    	 
    	 
    	 if(Math.abs(joySpeedValue) > 0.2){
    		 joySpeedValue = 0;
    	 }
    	 
    	 SmartDashboard.putNumber("speed acicional?", speedAddicional);
    	 
    	if(-Robot.oi.getJoystick1().getRawAxis(1) > speed){
    		SmartDashboard.putNumber("delta50", deltaTime);
    		speed = speed + speedAddicional;
    		oldTime = System.currentTimeMillis();
    	} else if (-Robot.oi.getJoystick1().getRawAxis(1) < speed){
    		speed = speed - speedAddicional; 
    		oldTime = System.currentTimeMillis();
    	} else {
    		speed = speed;
    		oldTime = System.currentTimeMillis();
    	 }
    	 
    	 if (speed > 1){
    		 speed = 1;
    	 }
    	 else if
    		 (speed < -1){
    		speed = -1;
    	 }
    	 
    	 double turn = Robot.oi.getJoystick1().getRawAxis(2) * constants.DRIVERotacion_kP;
    	 
    	 robotDrive41.tankDrive(speed + turn, speed - turn);
    	 
    	 SmartDashboard.putNumber("DOBRE pryndkusc", actualSpeed());
    	 
    	 SmartDashboard.putNumber("DYNSTANS W METACH", getDistanceInMeters());
    }
    
    
    public void updateAuto() {
    	if (controller != null) {
    		onTarget = controller.update();
    	}
    	
	}
    
    @SuppressWarnings("deprecation")
	public void stopDrive(){
    	robotDrive41.arcadeDrive(0, 0);
    }
    public void resetGyro(){
    	gyro.reset();
    }
    public void resetSpeed(){
    	speed = 0;
    }
    
    public void resetEncoder(){
    	encoder.reset();
    }
    
    public double actualSpeed(){
    	double speedometer = encoder.getRate()/4332;
    	SmartDashboard.putNumber("Speedometer", speedometer);
    	return speedometer;
    }
    
    public void setTankDrive(double left, double right) {
    	robotDrive41.tankDrive(left, right);
    }
    public double getAngle() {
    	return gyro.getAngle();
    }

	public void setController(ProfileDriveController controller) {
		this.controller = controller;	
	}
	public void arcadeDrive(double leftSpeed, double rightSpeed) {
		speedController0.set(leftSpeed);
		speedController1.set(leftSpeed);
		speedController2.set(rightSpeed);
		speedController3.set(rightSpeed);

		}
	}
	




