/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.PathFollowing;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;


public class FollowPath extends Command {

  Trajectory leftTrajectory, rightTrajectory;
  boolean reverse;

  public FollowPath(String leftTrajectory, String rightTrajectory, boolean reverse) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrainSubsystem);
    requires(Robot.pathFollowerSubsystem);
    try {
      this.leftTrajectory  = PathfinderFRC.getTrajectory(leftTrajectory);
      this.rightTrajectory = PathfinderFRC.getTrajectory(rightTrajectory); 
    } catch (Exception e) {
      System.out.println("Didn't work");      
      leftTrajectory = null;
      rightTrajectory = null;
      
    }

    this.reverse = reverse;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pathFollowerSubsystem.setTrajectory(leftTrajectory, rightTrajectory, reverse);
    Robot.pathFollowerSubsystem.startPathFollowing();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.pathFollowerSubsystem.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.pathFollowerSubsystem.stopPathFollowing();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
