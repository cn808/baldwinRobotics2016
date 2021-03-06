// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2439.SH16Bot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc2439.SH16Bot.commands.*;
import org.usfirst.frc2439.SH16Bot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static BallIntake ballIntake;
    public static GateLift gateLift;
    public static SallyArm sallyArm;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static NavX navX;
    
    SendableChooser positionChooser;
    SendableChooser defenseChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    
    	// this must be instantiated before DriveTrain
    	navX = new NavX();
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        ballIntake = new BallIntake();
        gateLift = new GateLift();
        sallyArm = new SallyArm();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        // setting up and getting usb camera object to stream
        CameraServer server = CameraServer.getInstance();
        server.setQuality(25);
        //kSize640x480 = 0
        //kSize320x240 = 1
        //kSize160x120 = 2
        server.setSize(2);
        server.startAutomaticCapture("cam3");
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        //autonomousCommand = new AutoLowBarToGoalCmd();
        autonomousCommand = new AutoChevalCrossCmd();
        //autonomousCommand = new AutoPorticulisBreachCmd();
        //autonomousCommand = new AutoSimpleBreachCmd();
        //autonomousCommand = new AutonomousCommand();
       

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        navX.zeroGyro();
        

        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
}

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       navX.outputToDashboard();
       SmartDashboard.putNumber("Right Dist", Robot.driveTrain.getRightDist());
       Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        navX.zeroGyro();
        //Robot.driveTrain.shiftDown();
        Robot.driveTrain.resetEncoders();
        //RobotMap.sallyArmLowerEnc.reset();
        //RobotMap.sallyArmUpperEnc.reset();
   }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        navX.outputToDashboard(); 
        SmartDashboard.putNumber("Right Dist", Robot.driveTrain.getRightDist());
        SmartDashboard.putNumber("Left Dist", Robot.driveTrain.getLeftDist());
        //SmartDashboard.putBoolean("Ball Arm Upper Sw", ballIntake.isArmUp() );
    	SmartDashboard.putBoolean(" Intake Arm Upper limit switch",!ballIntake.isArmUp());
    	SmartDashboard.putBoolean(" Intake Arm Lower limit switch",!ballIntake.isArmDown());
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
