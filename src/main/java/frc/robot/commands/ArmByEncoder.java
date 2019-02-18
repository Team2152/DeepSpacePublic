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

public class ArmByEncoder extends Command {
  double speed;
  double encoderTicks;
  boolean moveBackwards;
  public ArmByEncoder(double speed, double encoderTicks) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.stageOneArmSubsystem);
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
    Robot.stageOneArmSubsystem.stageOneSpeed(-speed);
    }else{
      Robot.stageOneArmSubsystem.stageOneSpeed(speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if((moveBackwards == true && Robot.stageOneArmSubsystem.getEncoderValue() <= encoderTicks) || 
    (moveBackwards == false && Robot.stageOneArmSubsystem.getEncoderValue() >= encoderTicks)){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.stageOneArmSubsystem.stageOneSpeed(0);
   
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.stageOneArmSubsystem.stageOneSpeed(0);
  
  }
}
