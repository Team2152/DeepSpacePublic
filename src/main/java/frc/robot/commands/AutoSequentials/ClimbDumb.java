/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoSequentials;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Antler.AntlerByEncoder;
import frc.robot.commands.Arm.ArmByEncoder;

public class ClimbDumb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbDumb() {
    requires(Robot.antlerSubsystem);
    requires(Robot.armSubsystem);
    Robot.armSubsystem.resetEncoder();
    Robot.antlerSubsystem.antlerEncoderReset();
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
    addParallel(new AntlerByEncoder(.65, 48));
    addSequential(new ArmByEncoder(.50, 48));
    addParallel(new AntlerByEncoder(.25, 10));
    addSequential(new ArmByEncoder(.4, 55));
    addSequential(new Wait(.5));
    addSequential(new ArmByEncoder(.75, 25));
  }
}
