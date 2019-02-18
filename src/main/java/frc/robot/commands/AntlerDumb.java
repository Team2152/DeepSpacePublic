/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AntlerDumb extends Command {
  double speed;
  double encoderTicks;
  boolean moveBackwards;
  public AntlerDumb(double speed, double encoderTicks) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.antlerSubsystem);
    this.speed = speed;
    this.encoderTicks = encoderTicks;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
if(Robot.antlerSubsystem.getEncoderValue() - encoderTicks >= 0){
  moveBackwards = true;
}
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(moveBackwards == false){
    Robot.antlerSubsystem.AntlerSpeed(-speed);
    }else{
      Robot.antlerSubsystem.AntlerSpeed(speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if((moveBackwards == true && Robot.antlerSubsystem.getEncoderValue() <= encoderTicks) || 
    (moveBackwards == false && Robot.antlerSubsystem.getEncoderValue() >= encoderTicks)){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.antlerSubsystem.AntlerSpeed(0);
   
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.antlerSubsystem.AntlerSpeed(0);
  
  }
}
