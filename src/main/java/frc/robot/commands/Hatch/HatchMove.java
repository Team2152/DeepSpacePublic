/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hatch;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControllerMap;
import frc.robot.Robot;

public class HatchMove extends Command {
 double speed = 0;
 Joystick driverJoystick;
 
  public HatchMove(double speed) {
    requires(Robot.hatchSubsystem);
    this.speed = speed;

  }

  
  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    Robot.hatchSubsystem.setRampRate(1);
    if(Robot.m_oi.operatorXbox.getRawButton(ControllerMap.HATCH_BUMPER_L)){
      Robot.hatchSubsystem.hatchSpeed(.75);
    }
    else  if(Robot.m_oi.operatorXbox.getRawButton(ControllerMap.HATCH_BUMPER_R)){
      Robot.hatchSubsystem.hatchSpeed(.5);
    }
  else {
    Robot.hatchSubsystem.setRampRate(0);
      Robot.hatchSubsystem.hatchSpeed(0);
  }
    }
  

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  
  @Override
  protected void interrupted() {
    Robot.hatchSubsystem.hatchSpeed(0);

  }
}
