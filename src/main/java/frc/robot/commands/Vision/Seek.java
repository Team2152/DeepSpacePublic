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
  private double Turn_Kp = 3;
  private double Turn_minCommand = 0;

  private double Throttle_Kp = 2;
  private double Throttle_minCommand = 0;

  private double throttle;
  private double turn;
 
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
    if(Robot.m_oi.driverXbox.getRawButton(6)){
        double headingError = Robot.limelightSubsystem.getTx();
        double distanceError = Robot.limelightSubsystem.getTy();

        double steeringAdjust = 0;
        if(Robot.limelightSubsystem.getTx() > 1){
          steeringAdjust = Turn_Kp* headingError-Turn_minCommand;
        }else if(Robot.limelightSubsystem.getTx() < 1){
          steeringAdjust = Turn_Kp* headingError+Turn_minCommand;
        }

        double distanceAdjust = 0;
        if(Robot.limelightSubsystem.getTy() > 1){
          distanceAdjust = Throttle_Kp * distanceError - Throttle_minCommand;
        } else if(Robot.limelightSubsystem.getTy() < 1){
          distanceAdjust = Throttle_Kp * distanceError + Throttle_minCommand;
        }
       turn = steeringAdjust / 30;
       throttle = distanceAdjust / 30;
       System.out.println(throttle + " Throttle " + turn +  " turn" );
       SmartDashboard.putNumber("Turn", turn);
       SmartDashboard.putNumber("Throttle", throttle);
       Robot.driveTrainSubsystem.arcadeDrive(-throttle, -turn);
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
