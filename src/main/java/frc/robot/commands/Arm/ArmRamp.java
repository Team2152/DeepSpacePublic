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
    if(Robot.m_oi.operatorXbox.getRawAxis(ControllerMap.ARM_JOYSTICK_R) <  -.1){// || Robot.m_oi.driverXbox.getRawButton(ControllerMap.ARM_BUMP_L)){
      if(Robot.armSubsystem.getEncoderValue() >= 20){
        Robot.armSubsystem.setSpeed(speed*.75);
      }else{
        Robot.armSubsystem.setSpeed(speed*Robot.m_oi.operatorXbox.getRawAxis(ControllerMap.ARM_JOYSTICK_R));
      }
    }else if(Robot.m_oi.operatorXbox.getRawAxis(ControllerMap.ARM_JOYSTICK_R) > .1){// || Robot.m_oi.driverXbox.getRawButton(ControllerMap.ARM_BUMP_R)){
      if(Robot.armSubsystem.getEncoderValue() <= 8){
        Robot.armSubsystem.setSpeed(-speed*.75);
      }else{
        Robot.armSubsystem.setSpeed(-speed* Robot.m_oi.operatorXbox.getRawAxis(ControllerMap.ARM_JOYSTICK_R));
      }
    }else{
      Robot.armSubsystem.setSpeed(0);
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
