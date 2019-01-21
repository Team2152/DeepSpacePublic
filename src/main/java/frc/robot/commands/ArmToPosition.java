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
import edu.wpi.first.wpilibj.Timer;


public class ArmToPosition extends Command implements PIDOutput{

  PIDController armPosition;
  double distanceOutput;
  double setPoint;

  public ArmToPosition(double setPoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.stageOneArmSubsystem);
    this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.stageOneArmSubsystem.setRampRate();
    armPosition = new PIDController(PIDConstants.ENCODER_ARM_kP, PIDConstants.ENCODER_ARM_kI, PIDConstants.ENCODER_ARM_kD, Robot.stageOneArmSubsystem.getSparkMAxPIDSource(), this);
    armPosition.disable();
    armPosition.setAbsoluteTolerance(PIDConstants.DISTANCE_TOLARANCE);
    armPosition.setContinuous(false);
    armPosition.setOutputRange(PIDConstants.MAX_RETURN_SPEED, PIDConstants.MAX_RETURN_SPEED);
    armPosition.setInputRange(PIDConstants.MININUM_INPUT_RANGE, PIDConstants.MAXIMUM_INPUT_RANGE);
    armPosition.enable(); 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.stageOneArmSubsystem.isArmStowed()){
      Robot.stageOneArmSubsystem.resetEncoder();
    }
    
    Robot.stageOneArmSubsystem.stageOneSpeed(armPosition.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(armPosition.onTarget()){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    armPosition.disable();
    Robot.stageOneArmSubsystem.stageOneSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    armPosition.disable();
    Robot.stageOneArmSubsystem.stageOneSpeed(0);
  }

  @Override
  public void pidWrite(double output){

  }


}
