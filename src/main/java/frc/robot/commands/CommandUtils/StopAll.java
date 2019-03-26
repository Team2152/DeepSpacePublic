/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandUtils;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class StopAll extends InstantCommand {
  /**
   * Add your docs here.
   */
  public StopAll() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchSubsystem);
    requires(Robot.driveTrainSubsystem);
    requires(Robot.armSubsystem);
    requires(Robot.antlerSubsystem);
    requires(Robot.cargoSubsystem);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.driveTrainSubsystem.tankDrive(0, 0);
    Robot.armSubsystem.setSpeed(0);
    Robot.antlerSubsystem.setSpeed(0);
    Robot.cargoSubsystem.setSpeed(0);
  }

}
