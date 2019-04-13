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

public class Seek extends Command {
  private double turnKP = 1;
  private double turnMinCommand = .1;

  private double throttleKP = .9;
  private double throttleMindCommand = .1;

  private double throttle = 0;
  private double turn = 0;

  public Seek() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.limelightSubsystem);
    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

if(Robot.limelightSubsystem.getTv() == 1){
  Robot.driveTrainSubsystem.setRampRate(.05);
    if(Robot.m_oi.driverXbox.getRawButton(6)){
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
    }else if(Robot.limelightSubsystem.getTv() == 0){
      Robot.driveTrainSubsystem.arcadeDrive(0, 0);
    }
  }else{
    Robot.driveTrainSubsystem.setRampRate(0);
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
          return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrainSubsystem.setRampRate(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
