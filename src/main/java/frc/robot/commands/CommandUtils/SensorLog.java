/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandUtils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SensorLog extends InstantCommand {
  /**
   * Add your docs here.
   */
  public SensorLog() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    
    
    
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    
    Robot.antlerSubsystem.resetEncoder();
    //Robot.m_logger.console("Antler Encoder: " + Robot.antlerSubsystem.getEncoderValue());

    Robot.armSubsystem.resetEncoder();
    //Robot.m_logger.console("Arm Encoder:" + Robot.armSubsystem.getEncoderValue());

    Robot.hatchSubsystem.clearStickyFaults();
    //Robot.m_logger.console("Stick Fault" + Robot.hatchSubsystem.getStickyFaults());

    Robot.driveTrainSubsystem.pigeon.setYaw(0);
    Robot.driveTrainSubsystem.pigeon.setFusedHeading(0);

    Robot.driveTrainSubsystem.resetEncoder();
  }

}
