/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ControllerMap;
import frc.robot.Robot;
import frc.robot.subsystems.ArmTest;

public class armMove extends Command {
 private double armSpeed = 0;
 Joystick driverJoystick;
 
public armMove(Double armSpeed) {
    requires(Robot.armTest);
    this.armSpeed = armSpeed;

  }

  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Robot.m_oi.driverXbox.getRawAxis(2) > 0.1){
      Robot.armTest.armMover(-armSpeed*Robot.m_oi.driverXbox.getRawAxis(2));
    }
    else  if(Robot.m_oi.driverXbox.getRawAxis(3) > 0.1){
      Robot.armTest.armMover(armSpeed*Robot.m_oi.driverXbox.getRawAxis(3));
    }else {
      Robot.armTest.armMover(0);
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
    Robot.hatchSubsystem.hatchMover(0);

  }
}
