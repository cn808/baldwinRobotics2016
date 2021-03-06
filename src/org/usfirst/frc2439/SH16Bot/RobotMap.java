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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainLeftEnc;
    public static Encoder driveTrainRightEnc;
    public static DoubleSolenoid driveTrainShifterSol;
    public static SpeedController driveTrainRightMotor;
    public static SpeedController driveTrainLeftMotor;
    public static RobotDrive driveTrainRobotDrive2;
    public static SpeedController ballIntakeIntakeMotor;
    public static Encoder ballIntakeIntakeEnc;
    public static DigitalInput ballIntakeIntakeLimitSw;
    public static DigitalInput ballIntakeArmLimitSw;
    public static AnalogInput ballIntakeIntakeArm;
    public static DoubleSolenoid gateLiftLiftSol;
    public static SpeedController gateLiftLiftMotor;
    public static DoubleSolenoid gateLiftPinSol;
    public static DigitalInput gateLiftExtendSw;
    public static DigitalInput gateLiftRetractSw;
    public static SpeedController sallyArmUpperArmMotor;
    public static SpeedController sallyArmLowerArmMotor;
    public static Encoder sallyArmUpperEnc;
    public static Encoder sallyArmLowerEnc;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static SpeedController ballIntakeLeftArmMotor;
    public static SpeedController ballIntakeRightArmMotor;

    
    public static Encoder ballIntakeArmEnc;
    public static DigitalInput ballIntakeUpperLimitSw;
    public static DigitalInput ballIntakeLowerLimitSw;
    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftEnc = new Encoder(2,3, false, EncodingType.k1X);
        LiveWindow.addSensor("DriveTrain", "LeftEnc", driveTrainLeftEnc);
        //driveTrainLeftEnc.setDistancePerPulse(85./72208.);
        driveTrainRightEnc = new Encoder(0,1, false, EncodingType.k1X);
        LiveWindow.addSensor("DriveTrain", "RightEnc", driveTrainRightEnc);
        //driveTrainRightEnc.setDistancePerPulse(85./73230.);
        driveTrainShifterSol = new DoubleSolenoid(0, 6,7);  //ORIGINALLY 6, 7 - SWITCHED WITH GATELIFT PIN SOLE
        LiveWindow.addActuator("DriveTrain", "ShifterSol", driveTrainShifterSol);
        
        driveTrainRightMotor = new TalonSRX(0);
        LiveWindow.addActuator("DriveTrain", "RightMotor", (TalonSRX) driveTrainRightMotor);
        
        driveTrainLeftMotor = new TalonSRX(1);
        LiveWindow.addActuator("DriveTrain", "LeftMotor", (TalonSRX) driveTrainLeftMotor);
        
        driveTrainRobotDrive2 = new RobotDrive(driveTrainLeftMotor, driveTrainRightMotor);
        
        driveTrainRobotDrive2.setSafetyEnabled(true);
        driveTrainRobotDrive2.setExpiration(1.0);
        driveTrainRobotDrive2.setSensitivity(0.5);
        driveTrainRobotDrive2.setMaxOutput(1.0);
        driveTrainRobotDrive2.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        driveTrainRobotDrive2.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);

        
        ballIntakeRightArmMotor = new Talon(2);  
        ballIntakeLeftArmMotor = new Talon(3);        

        ballIntakeIntakeMotor = new Talon(4);
        LiveWindow.addActuator("BallIntake", "IntakeMotor", (Talon) ballIntakeIntakeMotor);
        
        ballIntakeIntakeEnc = new Encoder(5, 6, false, EncodingType.k4X);
        LiveWindow.addSensor("BallIntake", "IntakeEnc", ballIntakeIntakeEnc);
        SmartDashboard.putNumber("Ball Intake Enc", ballIntakeIntakeEnc.get());
        ballIntakeIntakeEnc.setDistancePerPulse(1.0);
        ballIntakeIntakeEnc.reset();
        
        ballIntakeUpperLimitSw = new DigitalInput(4);
        ballIntakeLowerLimitSw = new DigitalInput(7);

        
        gateLiftLiftSol = new DoubleSolenoid(0, 2,3);
        LiveWindow.addActuator("GateLift", "LiftSol", gateLiftLiftSol);
        
        gateLiftLiftMotor = new Talon(6);
        LiveWindow.addActuator("GateLift", "LiftMotor", (Talon) gateLiftLiftMotor);
        
        gateLiftPinSol = new DoubleSolenoid(0, 4,5); //ORIGINALLY 4, 5 - SWITCHED WITH DRIVE TRAIN SHIFTER
        LiveWindow.addActuator("GateLift", "PinSol", gateLiftPinSol);
        
        gateLiftExtendSw = new DigitalInput(8);
        LiveWindow.addSensor("GateLift", "ExtendSw", gateLiftExtendSw);
        
        gateLiftRetractSw = new DigitalInput(9);
        LiveWindow.addSensor("GateLift", "RetractSw", gateLiftRetractSw);
        

        sallyArmUpperArmMotor = new Talon(7);  //5
        LiveWindow.addActuator("SallyArm", "UpperArmMotor", (Talon) sallyArmUpperArmMotor);
        
        sallyArmLowerArmMotor = new Talon(8);  // 4
        LiveWindow.addActuator("SallyArm", "LowerArmMotor", (Talon) sallyArmLowerArmMotor);
        
        //sallyArmUpperEnc = new Encoder(7, 8, false, EncodingType.k4X);
        //LiveWindow.addSensor("SallyArm", "UpperEnc", sallyArmUpperEnc);
        //sallyArmUpperEnc.setDistancePerPulse(1.0);
        //sallyArmUpperEnc.setPIDSourceType(PIDSourceType.kRate);
        //sallyArmLowerEnc = new Encoder(9, 10, false, EncodingType.k4X);
        //LiveWindow.addSensor("SallyArm", "LowerEnc", sallyArmLowerEnc);
        //sallyArmLowerEnc.setDistancePerPulse(1.0);
        //sallyArmLowerEnc.setPIDSourceType(PIDSourceType.kRate);
         
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        LiveWindow.addActuator("BallIntake", "IntakeMotor", (Talon) ballIntakeIntakeMotor);
        LiveWindow.addActuator("BallIntake", "RightArmMotor", (Talon) ballIntakeRightArmMotor);
        LiveWindow.addActuator("BallIntake", "LeftArmMotor", (Talon) ballIntakeLeftArmMotor);

    }
}
