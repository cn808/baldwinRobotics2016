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

import org.usfirst.frc2439.SH16Bot.RobotMap;
import org.usfirst.frc2439.SH16Bot.commands.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GateLift extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DoubleSolenoid liftSol = RobotMap.gateLiftLiftSol;
    private final SpeedController liftMotor = RobotMap.gateLiftLiftMotor;
    private final DoubleSolenoid pinSol = RobotMap.gateLiftPinSol;
    private final DigitalInput extendSw = RobotMap.gateLiftExtendSw;
    private final DigitalInput retractSw = RobotMap.gateLiftRetractSw;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    //// TEAM 2439 Code
    final static double MOTOR_SPEED = 0.4;
    
    public void raise()
    {
    	liftSol.set( DoubleSolenoid.Value.kReverse );
    }
    
    public void lower()
    {
    	liftSol.set( DoubleSolenoid.Value.kForward );
    }
    
    public void extend()
    {
    	liftMotor.set(-MOTOR_SPEED);;
    	
    }
    
    public void retract()
    {	
    	liftMotor.set(+MOTOR_SPEED);;
    
    }
    
    public void stopMotor()
    {
    	liftMotor.set(0);;
    }
    
    public void lockPin()
    {
    	pinSol.set( DoubleSolenoid.Value.kForward );
    }
    
    public void unlockPin()
    {
    	pinSol.set( DoubleSolenoid.Value.kReverse );
    }
    
    public boolean isArmExtended()
    {
    	return extendSw.get();
    }
    
    public boolean isArmRetracted()
    {
    	return retractSw.get();
    }

}
