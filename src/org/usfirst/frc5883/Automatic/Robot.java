package org.usfirst.frc5883.Automatic;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc5883.Automatic.commands.*;
import org.usfirst.frc5883.Automatic.commands.auto.Auto2481Left;
import org.usfirst.frc5883.Automatic.commands.auto.Auto2481Right;
import org.usfirst.frc5883.Automatic.commands.auto.DriveAndBack;
import org.usfirst.frc5883.Automatic.commands.auto.DriveThreeMeter;
import org.usfirst.frc5883.Automatic.commands.auto.FocusScaleLeft;
import org.usfirst.frc5883.Automatic.commands.auto.FocusScaleRight;
import org.usfirst.frc5883.Automatic.commands.auto.FocusScaleOuSwitchSide;
import org.usfirst.frc5883.Automatic.commands.auto.FocusSwitchSide;
import org.usfirst.frc5883.Automatic.commands.auto.GoToOppositeScale;
import org.usfirst.frc5883.Automatic.commands.auto.OpenIntakeAuto;
import org.usfirst.frc5883.Automatic.commands.auto.MidAuto;
import org.usfirst.frc5883.Automatic.commands.auto.OnlyScaleLeft;
import org.usfirst.frc5883.Automatic.commands.auto.OnlyScaleRight;
import org.usfirst.frc5883.Automatic.commands.drivetrain.SpeedCommand;
import org.usfirst.frc5883.Automatic.commands.drivetrain.StraightDrive;
import org.usfirst.frc5883.Automatic.commands.intake.IntakeDown;
import org.usfirst.frc5883.Automatic.subsystems.*;

public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser<String> autoChooser;
    NetworkTable table;
    
    public static Intake intake;
    public static Elevator elevator;
    public static OI oi;
    public static DriveTrain driveTrain;
    public static Climbing climbing;
    public static IntakeMotors intakeMotors;
    public static OpenIntake openintake;
    public double debugowanie = 0;
    public UsbCamera camera;
    public void robotInit() {
    	Constants.getConstants().setGameData("");
    	table = NetworkTable.getTable("Cube");
		
    	RobotMap.init();
    	
        driveTrain = new DriveTrain();
        
        intake = new Intake();
        
        intakeMotors = new IntakeMotors();
        
        climbing = new Climbing();
        
        elevator = new Elevator();
        
        openintake = new OpenIntake();
        
        oi = new OI();
        
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Default Program",  "DriveStright");
        //autoChooser.addObject("Test Command", new AutonomousCommand());
        //autoChooser.addObject("SpeedCommand", new SpeedCommand(5, 0.5));
        //autoChooser.addObject("DriveAndBack", new DriveAndBack());
        //autoChooser.addObject("ScaleLeft", "ScaleLeft");
        //autoChooser.addObject("ScaleRight", "ScaleRight");
        autoChooser.addObject("MiddleGOOD", "MIDGOOD");
        autoChooser.addObject("ScaleSwitchRightGOOD", "ScaleSwitchRightGOOD");
        autoChooser.addObject("ScaleSwitchLeftGOOD" , "ScaleSwitchLeftGOOD");
        //autoChooser.addObject("FokusSwitchRight", "FokusSwitchRight");
       // autoChooser.addObject("FokusSwitchLeft", "FokusSwitchLeft");
        //autoChooser.addObject("IntakeDownTest", "4");
        autoChooser.addObject("OnlyScaleRight", "OnlyScaleRight");
        autoChooser.addObject("OnlyScaleLeft", "OnlyScaleLeft");
        autoChooser.addObject("Auto2481Right", "Auto2481Right");
        autoChooser.addObject("Auto2481Left", "Auto2481Left");
        autoChooser.addObject("Nothing1", "Nothing1");

        
        SmartDashboard.putData("Autonomous mode chooser", autoChooser );
        Robot.driveTrain.encoderRight.reset();
        
   	 new Thread(() -> {
      camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(320, 240);
      camera.setFPS(15);
      camera.setBrightness(60);
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);
      
      Mat source = new Mat();
      Mat output = new Mat();
      
      while(!Thread.interrupted()) {
          cvSink.grabFrame(source);
          Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
          outputStream.putFrame(output);
      }
  }).start();
      
    }
     
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() { 
    	
    	
    	
    	String gameData = null;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	int retries = 100;
    	
    	while(gameData.length() < 2 && retries > 0) {
    		retries--;
    		try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				
			}
    		
        	gameData = DriverStation.getInstance().getGameSpecificMessage();

    	}
    	if(gameData.length() > 0) {
    		Constants.getConstants().setGameData(gameData);
    	}
    	
    	
    	switch (autoChooser.getSelected()) {
		case "DriveStright":
			autonomousCommand = new DriveThreeMeter();
			break;
		case "ScaleRight":
			autonomousCommand = new FocusScaleRight();
			break;
		case "MIDGOOD":
			autonomousCommand = new MidAuto();
			break;
		case "ScaleLeft":
			autonomousCommand = new FocusScaleLeft();
			break;
		case "ScaleSwitchLeftGOOD":
			autonomousCommand = new FocusScaleOuSwitchSide(true);
			break;
		case "ScaleSwitchRightGOOD":
			autonomousCommand = new FocusScaleOuSwitchSide(false);
			break;
		case "OnlyScaleRight":
			autonomousCommand = new OnlyScaleRight();
			break;
		case "OnlyScaleLeft":
			autonomousCommand = new OnlyScaleLeft();
			break;
		case "Auto2481Right":
			autonomousCommand = new Auto2481Right();
			break;
		case "Auto2481Left":
			autonomousCommand = new Auto2481Left();
			break;
		case "Nothing1":
			autonomousCommand = null;
			break;
//		case "FokusSwitchLeft":
//			autonomousCommand = new FocusSwitchSide(true);
//			break;
		
		default:
			autonomousCommand = new DriveThreeMeter();
			break;
		}
    	
    	
    	//autonomousCommand = (Command) autoChooser.getSelected();
        autonomousCommand.start();
        
    }


    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Robot.elevator.update();
        Robot.driveTrain.updateAuto();
        
        //SmartDashboard.putNumber("Distance", Robot.driveTrain.getDistanceInMeters());
    }

    public void teleopInit() {
    	driveTrain.resetSpeed();
		//elevator.setDefaultCommand(new ElevatorControl());

        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
    	
    	//DASHBOARD
    	double x = 0;
    	x = table.getNumber("cube", 0);
    	SmartDashboard.putNumber("X", x);     
    	
    	//Elevator
    	SmartDashboard.putNumber("ELEVATOR ENCODER ODCZYT", elevator.getDistane());
		
        
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
