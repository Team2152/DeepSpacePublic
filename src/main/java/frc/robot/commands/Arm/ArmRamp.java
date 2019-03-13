/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.ControllerMap;

public class ArmRamp extends Command {
  double speed;
  public ArmRamp(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.armSubsystem);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double throttle = -Robot.m_oi.operatorXbox.getRawAxis(ControllerMap.ARM_JOYSTICK_R) * .50;
    double multiplier = 1;
    Robot.armSubsystem.setRampRate(1);

    if(throttle < -.1){
      multiplier = 1.5;
    }

    if(Robot.armSubsystem.getEncoderValue() >=  22 || Robot.armSubsystem.getEncoderValue() <= 8){
      multiplier = .25;
      Robot.armSubsystem.setRampRate(0);
    }
    if(Robot.armSubsystem.getStowedSwitch() && throttle < -.1 ){
      multiplier = 0;
    }

    
    Robot.armSubsystem.setSpeed(throttle * multiplier);
   
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
