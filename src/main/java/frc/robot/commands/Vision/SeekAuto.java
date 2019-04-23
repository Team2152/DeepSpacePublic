/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ControllerMap;
import frc.robot.Robot;

public class SeekAuto extends Command {
  private double turnKP = 1;
  private double turnMinCommand = .1;
  private double turnTolerance = .5;

  private double throttleKP = .8;
  private double throttleMindCommand = .1;
  private double throttleTolerance = 1;

  private double throttle = 0;
  private double turn = 0;

  public SeekAuto() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.limelightSubsystem);
  
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelightSubsystem.setLedMode(3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(Robot.limelightSubsystem.getTv());
if(Robot.limelightSubsystem.getTv() == 1){
  Robot.driveTrainSubsystem.setRampRate(.05);

        double headingError = Robot.limelightSubsystem.getTx();
        
        double distanceError = Robot.limelightSubsystem.getTy();

        double steeringAdjust = 0;
        if(Robot.limelightSubsystem.getTx() > 1){
          steeringAdjust = turnKP* headingError-turnMinCommand;
        }else if(Robot.limelightSubsystem.getTx() < 1){
          steeringAdjust = turnKP* headingError+turnMinCommand;
        }

        double distanceAdjust = 0;
        if(Robot.limelightSubsystem.getTy() > 1){
          distanceAdjust = throttleKP * distanceError - throttleMindCommand;
        } else if(Robot.limelightSubsystem.getTy() < 1){
          distanceAdjust = throttleKP * distanceError + throttleMindCommand;
        }
       turn = steeringAdjust / 30;
       throttle = distanceAdjust / 30;
        Robot.driveTrainSubsystem.arcadeDrive(-throttle, -turn);

    } else {
      // I dont remember if putting a arcadeDrive(0,0) here breaks the command
      // If the robot stutters or whatever this is probably why
      Robot.driveTrainSubsystem.arcadeDrive(0, 0);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.limelightSubsystem.getTx() >= -turnTolerance && Robot.limelightSubsystem.getTx() <= turnTolerance && 
    Robot.limelightSubsystem.getTy() >= -throttleTolerance && Robot.limelightSubsystem.getTy() <= throttleTolerance){
      
      return true;
    } else {
          return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrainSubsystem.setRampRate(0);
    Robot.limelightSubsystem.setLedMode(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
