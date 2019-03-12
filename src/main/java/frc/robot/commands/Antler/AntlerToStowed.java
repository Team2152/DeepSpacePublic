/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Antler;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AntlerToStowed extends Command {
  double speed;
  public AntlerToStowed(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.antlerSubsystem);
    this.speed = (Math.abs(speed));
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.antlerSubsystem.getEncoderValue() <= 5){
      Robot.antlerSubsystem.setSpeed(speed*.75);
    }else{
    Robot.antlerSubsystem.setSpeed(speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.antlerSubsystem.getStowedSwitch()){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.antlerSubsystem.setSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.antlerSubsystem.setSpeed(0);
  }
}
