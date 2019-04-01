/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Arm;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmByEncoder extends Command {
  double speed;
  double encoderTicks;
  boolean moveBackwards;
  Timer timer;
  double time;
  public ArmByEncoder(double speed, double encoderTicks, double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.armSubsystem);
    timer = new Timer();
    this.speed = Math.abs(speed);
    this.encoderTicks = encoderTicks;
    this.time = time;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  timer.reset();
  timer.start();
if(Robot.armSubsystem.getEncoderValue() - encoderTicks >= 0){
  moveBackwards = true;
} else {
  moveBackwards = false;
}
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(moveBackwards == false){
    Robot.armSubsystem.setSpeed(speed);
    }else{
      Robot.armSubsystem.setSpeed(-speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if((moveBackwards == true && Robot.armSubsystem.getEncoderValue() <= encoderTicks) || 
    (moveBackwards == false && Robot.armSubsystem.getEncoderValue() >= encoderTicks) || timer.get() >= time){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.armSubsystem.setSpeed(0);
    timer.stop();
    
   
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  
  }
}
