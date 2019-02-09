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

public class AntlerToPostion extends Command implements PIDOutput {
 
  PIDController antlerPostion;
  double distanceOutput;
  double setPoint;
 
  public AntlerToPostion(double setPoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.antlerSubsystem);
    this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.antlerSubsystem.setRampRate();
    antlerPostion = new PIDController(PIDConstants.A_ENCODER_ARM_kD, PIDConstants.A_ENCODER_ARM_kI, PIDConstants.A_ENCODER_ARM_kD, Robot.antlerSubsystem.getSparkMAxPIDSource(), this);
    antlerPostion.disable();
    antlerPostion.setAbsoluteTolerance(PIDConstants.A_DISTANCE_TOLARANCE);
    antlerPostion.setContinuous(false);
    antlerPostion.setOutputRange(PIDConstants.A_MAX_RETURN_SPEED, PIDConstants.A_MAX_FORWARD_SPEED);
    antlerPostion.setInputRange(PIDConstants.A_MININUM_INPUT_RANGE, PIDConstants.A_MAXIMUM_INPUT_RANGE);
    antlerPostion.enable();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // add reset logic 

    Robot.antlerSubsystem.AntlerSpeed(antlerPostion.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(antlerPostion.onTarget()){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    antlerPostion.disable();
    Robot.antlerSubsystem.AntlerSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    antlerPostion.disable();
    Robot.antlerSubsystem.AntlerSpeed(0);
  }

  @Override
  public void pidWrite(double output){
    
  }
}
