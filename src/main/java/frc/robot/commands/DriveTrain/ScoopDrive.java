package frc.robot.commands.DriveTrain;

 import frc.robot.ControllerMap;
 import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utilities.NumericConstants;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /**
  * Takes input from the joystick and outputs it to the motors,
  */

 public class ScoopDrive extends Command {

	private double oldwheel = 0;
	private double quickStopAccumulator;
	private double throttleDeadband = .1;
	private double wheelDeadband = .1;

 	public ScoopDrive() {
 		 //Use requires() here to declare subsystem dependencies
 		 //eg. requires(chassis);
 		requires(Robot.driveTrainSubsystem);
 	}

 	 //Called just before this Command runs the first time
 	protected void initialize() {

 	}

 	 //Called repeatedly when this Command is scheduled to run
 	protected void execute() {
	 
	
		double wheelNonLinearity;
		double wheel;
		double throttle;
		if(Robot.driveTrainSubsystem.isInverted()){
			wheel = -handleDeadband(Robot.m_oi.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_TURN_ID)*.6, wheelDeadband);
			throttle = handleDeadband(Robot.m_oi.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_THROTTLE_ID)*.7, throttleDeadband);
		} else {
			wheel = -handleDeadband(Robot.m_oi.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_TURN_ID)*.6, wheelDeadband);
			throttle = -handleDeadband(Robot.m_oi.driverXbox.getRawAxis(ControllerMap.DRIVETRAIN_THROTTLE_ID)*.7, throttleDeadband);	
		}
		

		SmartDashboard.putNumber("Wheel", wheel);
		SmartDashboard.putNumber("throttle", throttle);

		double negInertia = wheel - oldwheel;
		oldwheel = wheel;

		wheelNonLinearity = .5;
		// Apply a sin function that's scaled to make it feel better.
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
			Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	
		double left, right, overPower;
		double sensitivity = 1.7;
		double angularPower;
		double linearPower;

		//Wow Negative inertia!
	
		double negInertiaAccumulator = 0;
		double negInertiaScalar;
		if (wheel * negInertia > 0) {
			negInertiaScalar = 2.5;
		  } else {
			if (Math.abs(wheel) > 0.65) {
			  negInertiaScalar = 5.0;
			} else {
			  negInertiaScalar = 3.0;
			}
		  }
		  sensitivity = NumericConstants.sensitivity;

		  double negInertiaPower = negInertia * negInertiaScalar;
		  negInertiaAccumulator += negInertiaPower;

		  wheel = wheel + negInertiaAccumulator;
		  if(negInertiaAccumulator > 1 ){
			  negInertiaAccumulator -= 1;
		  }else if( negInertiaAccumulator < -1){
			  negInertiaAccumulator += 1;
		  }else{
			  negInertiaAccumulator = 0;
		  }
		  linearPower = throttle;

		  //Quick Turn
		if(Robot.m_oi.driverXbox.getRawButton(ControllerMap.DRIVETRAIN_QUICK_TURN)){
			if(Math.abs(linearPower) < .2){
				double alpha = .1;
				quickStopAccumulator = ((1 - alpha) * quickStopAccumulator + alpha * DriveTrain.limit(wheel, 1) * 5);
			}
			overPower = 1;
			sensitivity  = 1;
			angularPower = wheel;

		}else{
			overPower = 0;
			if(throttle != 0){
				angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			} else {
				angularPower = wheel * sensitivity - quickStopAccumulator;
			}
			
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			  } else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			  } else {
				quickStopAccumulator = 0.0;
			  }
		}

		right = left = linearPower;
		left += angularPower;
		right-= angularPower;

		if (left > 1.0) {
			right -= overPower * (left - 1.0);
			left = 1.0;
		  } else if (right > 1.0) {
			left -= overPower * (right - 1.0);
			right = 1.0;
		  } else if (left < -1.0) {
			right += overPower * (-1.0 - left);
			left = -1.0;
		  } else if (right < -1.0) {
			left += overPower * (-1.0 - right);
			right = -1.0;
		  }
		
		  SmartDashboard.putNumber("Left", left);
		  SmartDashboard.putNumber("key", right);
		Robot.driveTrainSubsystem.tankDrive(left, right);
 	}

 	 //Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		return false;
 	}

 	 //Called once after isFinished returns true
 	protected void end() {
 		Robot.driveTrainSubsystem.tankDrive(0, 0);
 	}

 	 //Called when another command which requires one or more of the same
 	 //subsystems is scheduled to run
 	protected void interrupted() {
 		Robot.driveTrainSubsystem.tankDrive(0, 0);
	 }
	 
	 public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	  }
 }