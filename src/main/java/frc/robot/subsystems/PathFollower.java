/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 * Add your docs here.
 */
public class PathFollower extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    private static final int ticksPerRev = 11;
    private static final double wheelDiameter  = 0.1524;
    private static final double maxVelocity = 1;
       
    private EncoderFollower leftFollower;
    private EncoderFollower rightFollower;

    private Trajectory leftTrajectory, rightTrajectory;

    private Notifier followerNotifier;

    private boolean reverse = false;

    public PathFollower(){
      leftFollower = new EncoderFollower();
      
      rightFollower = new EncoderFollower();

      leftFollower.configureEncoder(getLeftEncoder(), ticksPerRev, wheelDiameter);
      //tune later
      leftFollower.configurePIDVA(1, 0, 0, 1/maxVelocity, 0);
      //leftFollower.configurePIDVA(kp, ki, kd, kv, ka);
      rightFollower.configureEncoder(getRightEncoder(), ticksPerRev, wheelDiameter);
      //tune later
      
      rightFollower.configurePIDVA(1, 0, 0, 1/maxVelocity, 0);

      followerNotifier = new Notifier(this::followPath);

      Robot.driveTrainSubsystem.pigeon.setFusedHeading(0);
    }

    private void followPath() {
      if (isFinished()) {
        followerNotifier.stop();
     
      } else {
       
        int leftPos = getLeftEncoder();
        int rightPos = getRightEncoder();
      //  print("Right P: " + rightPos + " Left P: " + leftPos);
        double leftSpeed = leftFollower.calculate(leftPos);
        double rightSpeed = rightFollower.calculate(rightPos);
        double heading = getHeading();
        double desired_heading = Pathfinder.r2d(leftFollower.getHeading());
        double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
        double turn =  0.8 * (-1.0/80.0) * heading_difference;
        // double turn = 0;
        if(reverse){
          //Flip left and right and multiply by negative one.
          setSpeeds(-1 * (rightSpeed  - turn) , -1 * (leftSpeed + turn));
        } else{ 
          setSpeeds( -(leftSpeed + turn), rightSpeed - turn);
        }
      }
    }

    public double getHeading(){
      return -1*Robot.driveTrainSubsystem.pigeon.getFusedHeading();
    //  return Robot.driveTrainSubsystem.getYaw();
    }

    public int getLeftEncoder(){
      return (int) Robot.driveTrainSubsystem.getLeftEncoder();
    }

    public int getRightEncoder(){
      return (int) Robot.driveTrainSubsystem.getRightEncoder();
    }

    public void setSpeeds(double left, double right){
      Robot.driveTrainSubsystem.tankDrive(left, right);
    }

  
    
    public boolean isFinished(){
      return leftFollower.isFinished() || rightFollower.isFinished();
    }

    public void startPathFollowing(){
      followerNotifier.startPeriodic(leftTrajectory.get(0).dt);
    }

    public void stopPathFollowing(){
      followerNotifier.stop();
    }

   public void setTrajectory(Trajectory leftTrajectory, Trajectory rightTrajectory, boolean reverse){
      this.leftTrajectory = leftTrajectory;
      this.rightTrajectory = rightTrajectory;
      leftFollower.setTrajectory(leftTrajectory);
      rightFollower.setTrajectory(rightTrajectory);
      this.reverse = reverse;
   }

   


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
