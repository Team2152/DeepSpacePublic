
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.DriveTrain.ScoopDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax right1;
	private CANSparkMax right2;
	private CANSparkMax left1;
  private CANSparkMax left2;

	private DifferentialDrive drive;
	
	private boolean isInverted;

  public DriveTrain(){
    right1 = new CANSparkMax(RobotMap.DRIVETRAIN_CANID_RIGHT1, CANSparkMaxLowLevel.MotorType.kBrushless);
      right1.setIdleMode(CANSparkMax.IdleMode.kBrake);
      right1.setInverted(true);

    right2 = new CANSparkMax(RobotMap.DRIVETRAIN_CANID_RIGHT2, CANSparkMaxLowLevel.MotorType.kBrushless);
      right2.setIdleMode(CANSparkMax.IdleMode.kBrake); 
			right2.follow(right1, false);
			
    left1 = new CANSparkMax(RobotMap.DRIVETRAIN_CANID_LEFT1, CANSparkMaxLowLevel.MotorType.kBrushless);
      left1.setIdleMode(CANSparkMax.IdleMode.kBrake);
      left1.setInverted(true);
			

    left2 = new CANSparkMax(RobotMap.DRIVETRAIN_CANID_LEFT2, CANSparkMaxLowLevel.MotorType.kBrushless);
      left2.setIdleMode(CANSparkMax.IdleMode.kBrake);
			left2.follow(left1, false);
		

		setInverted(false);
		isInverted = false;
		
    drive = new DifferentialDrive(left1, right1);
  }

  /***
	 * 
	 * Move motors using Tank Drive
	 * 
	 * @param leftSpeed
	 *            from -1 to 1
	 * @param rightSpeed
	 *            from -1 to 1
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	/**
	 * Arcade drive implements single stick driving.
	 * 
	 * @param forward
	 *            The value to use for forwards/backwards
	 * @param turn
	 *            The value to use for the rotate right/left
	 */
	public void arcadeDrive(double forward, double turn) {
		drive.arcadeDrive(forward, turn, false);
		
		
		
	}

	/**
	 * Set the speed of the right motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setRightSpeed(double speed) {
		right1.set(speed);
		
	
	}

	/**
	 * Set the speed of the left motors
	 * 
	 * @param speed
	 *            from -1 to 1
	 */
	public void setLeftSpeed(double speed) {
		left1.set(speed);
		
	}
	
	/**
	 * Getter method for drive inversion status
	 * @return inversion status
	 */
	public boolean isInverted(){
		return isInverted;
	}



	public void setInverted(boolean no){
		isInverted = no;
	}



  /**
	 * Toggles drive inversion (true <-> false)
	 */
	public void toggleInversion(){
		if(isInverted){
		isInverted = false;
		} else {
			isInverted = true;
		}
	}



	/**
   * Limits the given input to the given magnitude.
	 * @return COD
   */
  public static double limit(double v, double limit) {
    return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
	}
	
	

  
   @Override
   public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
     setDefaultCommand(new ScoopDrive());
   }
 }
