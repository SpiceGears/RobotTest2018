package org.usfirst.frc5883.Automatic.subsystems;

import org.usfirst.frc5883.Automatic.PortMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    private final DoubleSolenoid doubleSolenoid1 = new DoubleSolenoid(PortMap.rightSolenoidA, PortMap.rightSolenoidB);
    private final DoubleSolenoid doubleSolenoid2 = new DoubleSolenoid(PortMap.leftSolenoidA, PortMap.leftSolenoidB);
    private final Compressor compressor1 = new Compressor(0);
   
    public void initDefaultCommand() {
       // compressor1.stop();
    }
    
    public void compressorOFF() {
    
    	SmartDashboard.putBoolean("SPREZARKA", false);
    	compressor1.stop();
    }
    
    public void compressorOn() {
    	SmartDashboard.putBoolean("SPREZARKA", true);
    	compressor1.start();
    }
    public void solenoidOpen() {
    	doubleSolenoid1.set(Value.kReverse);
    	doubleSolenoid2.set(Value.kForward);
    }
    public void solenoidClose() {
    	doubleSolenoid1.set(Value.kForward);
    	doubleSolenoid2.set(Value.kReverse);
    }
    public void solenoidOff() {
    	doubleSolenoid1.set(Value.kOff);
    	doubleSolenoid2.set(Value.kOff);
    }
    
}

