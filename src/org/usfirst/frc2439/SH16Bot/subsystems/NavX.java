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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDSource;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class NavX extends Subsystem implements PIDSource {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	AHRS ahrs;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    //// TEAM 2439 code ////
    
    public NavX()
    {
        try {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            System.out.println("Instantiating NavX");
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

    }
    
    public void zeroGyro()
    {
    	//System.out.println("Resetting gyro");
    	ahrs.reset();
    }
    
    public double getYaw()
    {
    	return ahrs.getYaw();
    }
    
    public void driveStraight( double speed )
    {    	
    	double yaw = ahrs.getYaw();
    	double ks = 0.02;
    	double correct = yaw*ks;
    	//if( speed<0 )  correct = -correct;
    	correct = Robot.driveTrain.limit( correct,  0.3 );
    	double lspeed = speed + correct;
    	double rspeed = speed - correct;
    	//System.out.println( "Navx driving speed: Left = "+lspeed+",  Right = "+rspeed);
    	Robot.driveTrain.drive( lspeed, rspeed );
    }
    
        
    public void outputToDashboard()
    {
    	double yaw = ahrs.getYaw();
    	double ks = 0.05;
    	SmartDashboard.putNumber("Yaw*ks", yaw*ks);    

        /* Display 6-axis Processed Angle Data                                      */
        SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
        
        /* Display tilt-corrected, Magnetometer-based heading (requires             */
        /* magnetometer calibration to be useful)                                   */
        
        //SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
        
        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
        double fheading = ahrs.getFusedHeading();
        if( fheading>180 )  fheading -= 360.;       
        SmartDashboard.putNumber(   "IMU_FusedHeading",     fheading );
        
        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
        /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
        
        //SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
        //SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
        
        SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
        SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

        /* Display estimates of velocity/displacement.  Note that these values are  */
        /* not expected to be accurate enough for estimating robot position on a    */
        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
        /* of these errors due to single (velocity) integration and especially      */
        /* double (displacement) integration.                                       */
        
        //SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
        //SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
        //SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
        //SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
        
        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
        /* NOTE:  These values are not normally necessary, but are made available   */
        /* for advanced users.  Before using this data, please consider whether     */
        /* the processed data (see above) will suit your needs.                     */
        
        //SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
        //SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
        //SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
        //SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
        //SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
        //SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
        ////SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
        //SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
        //SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
        //SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
        
        /* Omnimount Yaw Axis Information                                           */
        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
        AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
        //SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        //SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
        
        /* Sensor Board Information                                                 */
        //SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
        
        /* Quaternion Data                                                          */
        /* Quaternions are fascinating, and are the most compact representation of  */
        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
        /* from the Quaternions.  If interested in motion processing, knowledge of  */
        /* Quaternions is highly recommended.                                       */
        //SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
        //SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
        //SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
        //SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
        
        /* Connectivity Debugging Support                                           */
        //SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
        //SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());
        //SmartDashboard.putString(   "IMU_Firmware vers",    ahrs.getFirmwareVersion());

    }
    
    // override PIDSource abstract methods
    public double pidGet()
    {
    	return ahrs.getYaw();
    }
    
    public PIDSourceType getPIDSourceType()
    {
    	return PIDSourceType.kRate;
    }
    
    public void setPIDSourceType( PIDSourceType type )
    {
    	
    }
}
