/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControllerMap;
import frc.robot.Robot;

public class TheCoolerCargo extends Command {
  public TheCoolerCargo() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(Robot.m_oi.driverXbox.getRawButton(ControllerMap.CARGO_BUMP_L) || Robot.m_oi.operatorXbox.getRawButton(ControllerMap.CARGO_BUMP_L)){
      Robot.cargoSubsystem.setRamp(1);
      Robot.cargoSubsystem.setSpeed(.5);
      Robot.m_oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 1);
    }else if(Robot.m_oi.driverXbox.getRawButton(ControllerMap.CARGO_BUMP_R) || Robot.m_oi.operatorXbox.getRawButton(ControllerMap.CARGO_BUMP_R)){
      Robot.cargoSubsystem.setRamp(1);
      Robot.cargoSubsystem.setSpeed(-.75);
      Robot.m_oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 1);
    }else{
      Robot.cargoSubsystem.setRamp(0);
      Robot.cargoSubsystem.setSpeed(0);
      Robot.m_oi.operatorXbox.setRumble(RumbleType.kLeftRumble, 0);

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
