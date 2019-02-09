/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utilities.PIDConstants;

public class HatchToPostion extends Command implements PIDOutput {

  PIDController hatchPosition;
  double distanceOutput;
  double setPoint;

  public HatchToPostion(double setPoint) {
   requires(Robot.hatchSubsystem);
   this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatchSubsystem.setRampRate();
    hatchPosition = new PIDController(PIDConstants.H_ENCODER_kP, PIDConstants.H_ENCODER_kI, PIDConstants.H_ENCODER_kD, Robot.hatchSubsystem.getMotorControllerPIDSource(), this);
    hatchPosition.disable();
    hatchPosition.setAbsoluteTolerance(PIDConstants.H_DISTANCE_TOLARANCE);
    hatchPosition.setContinuous(false);
    hatchPosition.setOutputRange(PIDConstants.A_MININUM_INPUT_RANGE, PIDConstants.A_MAXIMUM_INPUT_RANGE);
    hatchPosition.setInputRange(PIDConstants.H_MININUM_INPUT_RANGE, PIDConstants.H_MAXIMUM_INPUT_RANGE);
    hatchPosition.enable();
    Robot.hatchSubsystem.lockSolenoidClose();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.hatchSubsystem.isHatchStowed()){
      Robot.hatchSubsystem.resetEncoder();
    }

    Robot.hatchSubsystem.hatchSpeed(hatchPosition.get());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(hatchPosition.onTarget()){
      Robot.hatchSubsystem.lockSolenoidOpen();
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchPosition.disable();
    Robot.hatchSubsystem.hatchSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchPosition.disable();
    Robot.hatchSubsystem.hatchSpeed(0);
  }


  @Override
  public void pidWrite(double output){

  }


}
