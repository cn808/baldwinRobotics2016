// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2439.SH16Bot.subsystems;

import org.usfirst.frc2439.SH16Bot.Robot;
import org.usfirst.frc2439.SH16Bot.RobotMap;
import org.usfirst.frc2439.SH16Bot.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SafePWM;;


/**
 *
 */
public class DriveTrain extends Subsystem implements PIDOutput {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Encoder leftEnc = RobotMap.driveTrainLeftEnc;
    private final Encoder rightEnc = RobotMap.driveTrainRightEnc;
    private final DoubleSolenoid shifterSol = RobotMap.driveTrainShifterSol;
    private final SpeedController rightMotor = RobotMap.driveTrainRightMotor;
    private final SpeedController leftMotor = RobotMap.driveTrainLeftMotor;
    private final RobotDrive robotDrive2 = RobotMap.driveTrainRobotDrive2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static boolean inHighGear = false;
    public static boolean sqInputs =true;
    
    private PIDController turnPID;
    private PIDController distPID;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
    static final double t_kP = 0.03;
    static final double t_kI = 0.00;
    static final double t_kD = 0.00;
    static final double t_kF = 0.00;
    
    static final double kToleranceDegrees = 2.0f;
    
    private double rotateToAngleRate;

    public DriveTrain()
    {
        turnPID = new PIDController(t_kP, t_kI, t_kD, t_kF, Robot.navX, this);
        turnPID.setInputRange(-180.0f,  180.0f);
        turnPID.setOutputRange(-1.0, 1.0);
        turnPID.setAbsoluteTolerance(kToleranceDegrees);
        turnPID.setContinuous(true);
        
        /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
        /* tuning of the Turn Controller's P, I and D coefficients.            */
        /* Typically, only the P value needs to be modified.                   */
        LiveWindow.addActuator("DriveSystem", "RotateController", turnPID);
    
    }


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new OperatrorDriveCmd());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    // TEAM 2439 CODE
    public void drive( double lspeed, double rspeed )
    {
    	//System.out.println(" Motor expiration = "+(SafePWM)leftMotor.getExpiration());
    	leftMotor.set( limitSpeed(lspeed) );
    	//left2Motor.set( limitSpeed(lspeed) );
    	rightMotor.set( limitSpeed(rspeed) );
    	//right2Motor.set( limitSpeed(rspeed) );
    	robotDrive2.tankDrive(lspeed,rspeed,true);    	
    	
    	SmartDashboard.putNumber("Left Enc", leftEnc.get() );
    	SmartDashboard.putNumber("Right Enc", rightEnc.get() );
    }
    
    public double limitSpeed( double speed )
    {
    	return limit( speed, 1.0 );
    }
    
    public double limit( double input, double posLimit )
    {
    	if( input>posLimit )  return posLimit;
    	else if( input<-posLimit ) return -posLimit;
    	else  return input;
    }
    
    public void shiftUp()
    {
    	shifterSol.set(DoubleSolenoid.Value.kReverse);
    	System.out.println("Shift up");
    	inHighGear = true;
    }
    
    public void shiftDown()
    {
    	shifterSol.set(DoubleSolenoid.Value.kForward);
    	System.out.println("Shift down");
    	inHighGear = false;
    }
   
    public void pidWrite(double rotateRate) {
        rotateToAngleRate = rotateRate;
    }
    
    public void turnToAngle( double angle )
    {
    	turnPID.setSetpoint(angle);
    }
    
    public void resetEncoders()
    {
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public void getEncoders( double lDist, double rDist )
    {
    	lDist = getLeftDist();
    	rDist = getRightDist();
    }
    
    public double getLeftDist()
    {
    	return Robot.driveTrain.leftEnc.getDistance();
    }
    
    public double getRightDist()
    {
    	return Robot.driveTrain.rightEnc.getDistance();
    }
}

