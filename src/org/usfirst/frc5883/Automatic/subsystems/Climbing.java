package org.usfirst.frc5883.Automatic.subsystems;


import org.usfirst.frc5883.Automatic.Constants;
import org.usfirst.frc5883.Automatic.PortMap;
import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climbing extends Subsystem {

    private VictorSP leftClimbingMotor;
    private VictorSP rightClimbingMotor;
//    private Encoder leftEncoder = new Encoder(PortMap.leftClimbingEncoderA, PortMap.leftClimbingEncoderB);
    private Encoder rightEncoder;
    Constants constants = Constants.getConstants();
    

    public Climbing() {
    	leftClimbingMotor = new VictorSP(PortMap.leftClimbingMotor);
    	rightClimbingMotor = new VictorSP(PortMap.rightClimbingMotor);
    	//rightEncoder = new Encoder(PortMap.rightClimbingEncoderA, PortMap.rightClimbingEncoderB);
    }

    public void initDefaultCommand() {
       // setDefaultCommand(new Climb());
    }
    
    public void prepareToClimbing() {
    	//double srednia = (leftEncoder.getDistance() + rightEncoder.getDistance()) /2;
//    	if(rightEncoder.getDistance() < constants.climbingPrepare) {
//    		setPower(0.8);
//    	}
//    	SmartDashboard.putNumber("CLIMBING RIGHT", rightEncoder.getDistance());
    }
    
    public void climbing() {
    	setPower(1);
    }
    
    public void backClimbing() {
    	setPower(-1);
    }
    
    public void setPower(double power) {
    	leftClimbingMotor.set(power);
    	rightClimbingMotor.set(power);
    }
    public void stopClimbing() {
    	leftClimbingMotor.set(0);
    	rightClimbingMotor.set(0);
    }
}

