// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import frc.robot.RobotMap;
// import frc.robot.commands.LimeDrive;

// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.NeutralMode;

// /**
//  * Add your docs here.
//  */
// public class DriveTrain extends Subsystem {
//   // Put methods for controlling this subsystem
//   // here. Call these from Commands.

//   // private WPI_TalonSRX right1;
// 	// private WPI_TalonSRX right2;
// 	// private WPI_TalonSRX left1;
//   // private WPI_TalonSRX left2;

//   private DifferentialDrive drive;

//   public DriveTrain(){
//     right1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_CANID_RIGHT1);
//       right1.setNeutralMode(NeutralMode.Brake);
//       right1.setInverted(true);

//     right2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_CANID_RIGHT2);
//       right2.setNeutralMode(NeutralMode.Brake);
//       right2.set(ControlMode.Follower, RobotMap.DRIVETRAIN_CANID_RIGHT1);
//       right2.setInverted(true);


//     left1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_CANID_LEFT1);
//       left1.setNeutralMode(NeutralMode.Brake);
//       left1.setInverted(true);


//     left2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_CANID_LEFT2);
//       left2.setNeutralMode(NeutralMode.Brake);
//       left2.set(ControlMode.Follower, RobotMap.DRIVETRAIN_CANID_LEFT1);
//       left2.setInverted(true);


//     drive = new DifferentialDrive(left1, right1);
//   }

//   /***
// 	 * 
// 	 * Move motors using Tank Drive
// 	 * 
// 	 * @param leftSpeed
// 	 *            from -1 to 1
// 	 * @param rightSpeed
// 	 *            from -1 to 1
// 	 */
// 	public void tankDrive(double leftSpeed, double rightSpeed) {
// 		drive.tankDrive(leftSpeed, rightSpeed);
// 	}

// 	/**
// 	 * Arcade drive implements single stick driving.
// 	 * 
// 	 * @param forward
// 	 *            The value to use for forwards/backwards
// 	 * @param turn
// 	 *            The value to use for the rotate right/left
// 	 */
// 	public void arcadeDrive(double forward, double turn) {
// 		drive.arcadeDrive(forward, turn, false);

// 	}

// 	/**
// 	 * Set the speed of the right motors
// 	 * 
// 	 * @param speed
// 	 *            from -1 to 1
// 	 */
// 	public void setRightSpeed(double speed) {
// 		right1.set(speed);
// 	}

// 	/**
// 	 * Set the speed of the left motors
// 	 * 
// 	 * @param speed
// 	 *            from -1 to 1
// 	 */
// 	public void setLeftSpeed(double speed) {
// 		left1.set(speed);
//   }
  
//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     // setDefaultCommand(new MySpecialCommand());
//     setDefaultCommand(new LimeDrive());
//   }
// }
