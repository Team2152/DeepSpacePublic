/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControllerMap;
import frc.robot.Robot;

public class Aim extends Command {
  private double Kp = -0.1;
  private double minCommand = .1;
  private double left;
  private double right;
 
  public Aim() {
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
    if(Robot.m_oi.operatorXbox.getRawButton(ControllerMap.AIM_BUTTON_Y)){
        double headingError = -Robot.limelightSubsystem.getTx();
        double steeringAdjust = 0;
        if(Robot.limelightSubsystem.getTx() > 1){
          steeringAdjust = Kp* headingError-minCommand;
        }else if(Robot.limelightSubsystem.getTx() < 1){
          steeringAdjust = Kp* headingError+minCommand;
        }
       left += steeringAdjust / 100;
       right -= steeringAdjust / 100;
       System.out.println("qqqqqqqqqqqqqq  " + left + "    " + right );
        Robot.driveTrainSubsystem.tankDrive(left, right);
    }
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
