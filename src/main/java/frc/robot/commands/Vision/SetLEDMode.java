/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class SetLEDMode extends Command {


  /*
  Led Modes
  0 - Pipeline settings
  1 - Force off
  2 - Force blink
  3 - Force on
  */
  private boolean on;
  
  public SetLEDMode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.limelightSubsystem);
    on = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(on){
      Robot.limelightSubsystem.setLedMode(3);
      on = false;
    }else{
      Robot.limelightSubsystem.setLedMode(1);
      on = true;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //  Robot.limelightSubsystem.setLedMode(mode);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  
    return true;
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
