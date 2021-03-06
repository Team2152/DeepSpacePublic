/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.CommandUtils.SensorLog;
import frc.robot.commands.PathFollowing.FollowPath;
import frc.robot.utilities.PathNames;

public class LeftAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LeftAuto() {
    requires(Robot.driveTrainSubsystem);
    requires(Robot.limelightSubsystem);
    requires(Robot.hatchSubsystem);
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  Robot.driveTrainSubsystem.resetEncoder();
  Robot.driveTrainSubsystem.pigeon.setFusedHeading(0);
  Robot.driveTrainSubsystem.pigeon.setYaw(0);
  
  addSequential(new FollowPath(PathNames.leftT2Right, PathNames.leftT2Left, true));
  addSequential(new SeekAndDestroy());
  addSequential(new SensorLog());
   addSequential(new FollowPath(PathNames.leftToPlayerLeft, PathNames.leftToPlayerRight, false)); 
   addSequential(new SeekAndPickup());
   addSequential(new SensorLog());
   addSequential(new FollowPath(PathNames.leftPlayerToRocketLeft, PathNames.leftPlayerToRocketRight, false));
   addSequential(new SeekAndDestroy());
  }
}
