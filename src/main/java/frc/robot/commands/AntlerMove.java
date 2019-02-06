/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AntlerMove extends Command {

double speed;

  public AntlerMove(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.antlerManualSubSystem);
this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.driverXbox.getRawAxis(2) > .1){
      Robot.antlerManualSubSystem.AntlerManualMove(speed * .5);
    }else  if(Robot.m_oi.driverXbox.getRawAxis(3) > .1){
      Robot.antlerManualSubSystem.AntlerManualMove(speed* -.5);
    }else{
      Robot.antlerManualSubSystem.AntlerManualMove(0);
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
    Robot.antlerManualSubSystem.AntlerManualMove(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.antlerManualSubSystem.AntlerManualMove(0);
  }
}
