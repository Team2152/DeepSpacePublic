/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utilities.PIDConstants;

public class ShooterToPosition extends Command implements PIDOutput{

PIDController shooterPosition;
double distanceOutput;
double setPoint;


  public ShooterToPosition(double setPoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.stageTwoArmSubsystem);
    this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.stageTwoArmSubsystem.setRampRate();

    if(DriverStation.getInstance().isTest() == true){
      shooterPosition = new PIDController(Robot.spaceDash.getStage2PID(0), Robot.spaceDash.getStage2PID(1), Robot.spaceDash.getStage2PID(2), Robot.stageTwoArmSubsystem.getIMUPIDSource(), this);
    } else {
      shooterPosition = new PIDController(PIDConstants.ST_IMU_kP, PIDConstants.ST_IMU_kI, PIDConstants.ST_IMU_kD, Robot.stageTwoArmSubsystem.getIMUPIDSource(), this);
    }

    shooterPosition.disable();
    shooterPosition.setAbsoluteTolerance(PIDConstants.ST_DISTANCE_TOLARANCE);
    shooterPosition.setContinuous(false);
    shooterPosition.setOutputRange(PIDConstants.ST_MAX_RETURN_SPEED, PIDConstants.ST_MAX_FORWARD_SPEED);
    shooterPosition.setInputRange(PIDConstants.SO_MININUM_INPUT_RANGE, PIDConstants.ST_MAXIMUM_INPUT_RANGE);
    shooterPosition.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.stageTwoArmSubsystem.isArmStowed()){
      Robot.stageTwoArmSubsystem.resetiMU();
    }
    Robot.stageTwoArmSubsystem.stageTwoSpeed(shooterPosition.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(shooterPosition.onTarget()){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooterPosition.disable();
    Robot.stageTwoArmSubsystem.stageTwoSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    shooterPosition.disable();
    Robot.stageTwoArmSubsystem.stageTwoSpeed(0);
  }


  @Override
  public void pidWrite(double output){

  }

}
