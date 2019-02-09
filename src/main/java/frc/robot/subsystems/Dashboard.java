/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.utilities.PIDConstants;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Creates the widgets displayed on Shuffleboard for Testing and Competitive Use
 */
public class Dashboard extends Subsystem {
  
  private ShuffleboardTab testTab = Shuffleboard.getTab("squishCat"); //Trevor named it.

  private double[] s1_PID = new double[3];
  private double[] s2_PID = new double[3];
  
  private NetworkTableEntry motorGains = testTab.add("Motor Gains", 1).withWidget(BuiltInWidgets.kTextView).getEntry();

  private NetworkTableEntry s1_P = testTab.add("S1 P", PIDConstants.SO_ENCODER_ARM_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s1_I = testTab.add("S1 I", PIDConstants.SO_ENCODER_ARM_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s1_D = testTab.add("S1 D", PIDConstants.SO_ENCODER_ARM_kD).withWidget(BuiltInWidgets.kTextView).getEntry();

  private NetworkTableEntry s2_P = testTab.add("S2 P", PIDConstants.ST_IMU_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s2_I = testTab.add("S2 I", PIDConstants.ST_IMU_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s2_D = testTab.add("S2 D", PIDConstants.ST_IMU_kD).withWidget(BuiltInWidgets.kTextView).getEntry();

  /**
   * returns the motor gains value stored on Shuffleboard
   */
  public double getMotorGains() {
    return motorGains.getDouble(1);
  }

  /**
   * @param value 0 - P, 1 - I, 2 - D
   */
  public double getStage1PID(int value){
    s1_PID[0] = s1_P.getDouble(0);
    s1_PID[1] = s1_I.getDouble(0);
    s1_PID[2] = s1_D.getDouble(0);
    return s1_PID[value];
  }

  public double getStage2PID(int value){
    s2_PID[0] = s2_P.getDouble(0);
    s2_PID[1] = s2_I.getDouble(0);
    s2_PID[2] = s2_D.getDouble(0);
    return s2_PID[value];
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
