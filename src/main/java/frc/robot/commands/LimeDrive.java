package frc.robot.commands;

 import frc.robot.ControllerMap;
 import frc.robot.Robot;

 import edu.wpi.first.wpilibj.command.Command;

 /**
  * Takes input from the joystick and outputs it to the motors,
  */

 public class LimeDrive extends Command {

 	public LimeDrive() {
 		 //Use requires() here to declare subsystem dependencies
 		 //eg. requires(chassis);
 		requires(Robot.driveTrainSubsystem);
 	}

 	 //Called just before this Command runs the first time
 	protected void initialize() {

 	}

 	 //Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		double outputThrottle = 0;
 		double outputTurn = 0;

 		outputThrottle = Robot.driveTrainJoystickGain.applyGain(ControllerMap.DriveTrain_Drive_Joystick.getRawAxis(ControllerMap.DRIVETRAIN_THROTTLE_ID));
 		outputTurn = -Robot.driveTrainJoystickGain.applyGain(ControllerMap.DriveTrain_Drive_Joystick.getRawAxis(ControllerMap.DRIVETRAIN_TURN_ID));

 		if (Math.abs(outputThrottle) <= 0.25) {
 			Robot.driveTrainSubsystem.arcadeDrive(0.0, (outputTurn * 0.66));
 		} else {
 			Robot.driveTrainSubsystem.arcadeDrive((outputThrottle), -(outputThrottle * outputTurn));
 		}
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
 }