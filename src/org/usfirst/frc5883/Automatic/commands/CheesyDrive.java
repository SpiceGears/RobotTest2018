package org.usfirst.frc5883.Automatic.commands;

import org.usfirst.frc5883.Automatic.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheesyDrive extends Command {
    double turn_gain = 1;
    double skim_gain = 0;
    double turn_velocity_multiplier_gain = 1.1; 
    public CheesyDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = Robot.oi.getJoystick1().getRawAxis(1);
        double turnInput = Robot.oi.getJoystick1().getRawAxis(2);
        double wheelNonLinearity = 0.5;
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        
        if ( Math.abs(turnInput) < 0.07 )
            turnInput = 0;
        
        double turn = 0;
        if ( Math.abs(throttle) < 0.07 ) {
            throttle = 0;
            turn = turnInput*turn_gain;
        }
        else
            turn = (turnInput*turn_gain)*Math.abs(turn_velocity_multiplier_gain*Robot.oi.getJoystick1().getRawAxis(1));
        
        double left_orig = throttle-turn;
        double right_orig = throttle+turn;

        double left = left_orig+skim(right_orig);
        double right = right_orig+skim(left_orig);

        Robot.driveTrain.setTankDrive(-left, -right);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    private double skim(double v) {
        if (v > 1.0)
            return -((v - 1.0) * skim_gain);
        else if (v < -1.0)
            return -((v + 1.0) * skim_gain);
        return 0;
    }
}
