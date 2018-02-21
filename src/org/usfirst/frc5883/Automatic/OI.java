
package org.usfirst.frc5883.Automatic;

import java.util.prefs.PreferenceChangeEvent;

import org.usfirst.frc5883.Automatic.commands.*;
import org.usfirst.frc5883.Automatic.commands.climbing.BackClimbing;
import org.usfirst.frc5883.Automatic.commands.climbing.ClimbingCommand;
import org.usfirst.frc5883.Automatic.commands.climbing.PrepareToClimbing;
import org.usfirst.frc5883.Automatic.commands.elevator.setElevatorControl;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetIntake;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetScale;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorSetSwitch;
import org.usfirst.frc5883.Automatic.commands.elevator.ElevatorTrapezoidal;
import org.usfirst.frc5883.Automatic.commands.intake.OpenClaw;
import org.usfirst.frc5883.Automatic.commands.intake.CompressorOff;
import org.usfirst.frc5883.Automatic.commands.intake.DropMotors;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeDown;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeMotorIntake;
import org.usfirst.frc5883.Automatic.commands.intake.CloseClaw;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeUp;
import org.usfirst.frc5883.Automatic.framework.AxisButton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc5883.Automatic.subsystems.*;

public class OI {

	private Joystick driver;
	private Joystick operator;
	private JoystickButton setIntake;
	private JoystickButton setSwitch;
	private JoystickButton setScale;
	private JoystickButton compressor;
	private JoystickButton elevatorTrapezoidal;
	private JoystickButton setElevatorControl;
	private JoystickButton intakeMotors;
	private JoystickButton dropIntakeMotors;
	private JoystickButton downIntake;
	private JoystickButton upIntake;
	private JoystickButton climbing;
	private JoystickButton prepareClimbing;
	private JoystickButton backClimbing;
	private AxisButton openIntake;
	private AxisButton closeIntake;

    public OI() {
		driver = new Joystick(0);
		operator = new Joystick(1);
		
		//Elevator
		setIntake = new JoystickButton(operator, 3);
		setIntake.whileHeld(new ElevatorSetIntake());
		setSwitch = new JoystickButton(operator, 2);
		setSwitch.whileHeld(new ElevatorSetSwitch());
		setScale = new JoystickButton(operator, 1);
		setScale.whileHeld(new ElevatorSetScale());
		setElevatorControl = new JoystickButton(operator,10);
		setElevatorControl.whenPressed(new setElevatorControl());
		elevatorTrapezoidal = new JoystickButton(operator, 9);
		elevatorTrapezoidal.whenPressed(new ElevatorTrapezoidal());
		
		//Intake
		openIntake = new AxisButton(driver, 3, 0.5);
		openIntake.whileHeld(new CloseClaw());
		closeIntake = new AxisButton(driver, 2, 0.5);
		closeIntake.whileHeld(new OpenClaw());
		compressor = new JoystickButton(operator, 7);
		compressor.whileHeld(new CompressorOff());
		intakeMotors = new JoystickButton(driver, 5);
		intakeMotors.whileHeld(new IntakeMotorIntake());
		dropIntakeMotors = new JoystickButton(driver, 6);
		dropIntakeMotors.whileHeld(new DropMotors());
		
		//Intake Control
		upIntake = new JoystickButton(operator, 12);
		upIntake.whenPressed(new IntakeUp());
		downIntake = new JoystickButton(operator, 11);
		downIntake.whenPressed(new IntakeDown());
		
		//Climbing
		climbing = new JoystickButton(operator, 15);
		climbing.whileHeld(new ClimbingCommand());
		prepareClimbing = new JoystickButton(operator, 13);
		prepareClimbing.whileHeld(new PrepareToClimbing());
		backClimbing = new JoystickButton(operator, 14);
		backClimbing.whileActive(new BackClimbing());
		
    }

	public Joystick getDriverJoystick() {
		return driver;
	}

	public Joystick getOperatorJoystick() {
		return operator;
	}

}

