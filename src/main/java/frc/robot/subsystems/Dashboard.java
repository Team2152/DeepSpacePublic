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
  
  public ShuffleboardTab testTab = Shuffleboard.getTab("squishCat"); //Trevor named it.

  //-----PID ARRAYS-----
  private double[] s1_PID   = new double[3];
  private double[] s2_PID   = new double[3];
  private double[] ant_PID  = new double[3];
  private double[] hat_PID  = new double[3];
  
  public NetworkTableEntry encoderGraph = testTab.add("Encoder Value",0).withWidget(BuiltInWidgets.kGraph).getEntry();
  
  //-----MotorGains-----
  private NetworkTableEntry motorGains = testTab.add("Motor Gains", 1).withWidget(BuiltInWidgets.kTextView).getEntry();

  //-----StageOneArm-----
  private NetworkTableEntry s1_P = testTab.add("S1 P", PIDConstants.SO_ENCODER_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s1_I = testTab.add("S1 I", PIDConstants.SO_ENCODER_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s1_D = testTab.add("S1 D", PIDConstants.SO_ENCODER_kD).withWidget(BuiltInWidgets.kTextView).getEntry();

  //-----StageTwoArm-----
  private NetworkTableEntry s2_P = testTab.add("S2 P", PIDConstants.ST_IMU_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s2_I = testTab.add("S2 I", PIDConstants.ST_IMU_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry s2_D = testTab.add("S2 D", PIDConstants.ST_IMU_kD).withWidget(BuiltInWidgets.kTextView).getEntry();

  //-----Antler------
  private NetworkTableEntry antP = testTab.add("Antler P", PIDConstants.A_ENCODER_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry antI = testTab.add("Antler I", PIDConstants.A_ENCODER_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry antD = testTab.add("Antler D", PIDConstants.A_ENCODER_kD).withWidget(BuiltInWidgets.kTextView).getEntry();

  //-----Hatch-----
  private NetworkTableEntry hatP = testTab.add("Hatch P", PIDConstants.H_ENCODER_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry hatI = testTab.add("Hatch I", PIDConstants.H_ENCODER_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry hatD = testTab.add("Hatch D", PIDConstants.H_ENCODER_kD).withWidget(BuiltInWidgets.kTextView).getEntry();


  /**
   * @return The value of the Motor Gains
   */
  public double getMotorGains() {
    return motorGains.getDouble(1);
  }

  /**
   * Returns the value of the PID terms for the Stage One Arm
   * @param term 0 - P, 1 - I, 2 - D
   * @return The value of the selected PID Term
   */
  public double getStage1PID(int term){
    s1_PID[0] = s1_P.getDouble(0);
    s1_PID[1] = s1_I.getDouble(0);
    s1_PID[2] = s1_D.getDouble(0);
    return s1_PID[term];
  }

  /**
   * Returns the value of the PID terms for the Stage Two Arm
   * @param term 0 - P, 1 - I, 2 - D
   * @return The value of the selected PID Term
   */
  public double getStage2PID(int term){
    s2_PID[0] = s2_P.getDouble(0);
    s2_PID[1] = s2_I.getDouble(0);
    s2_PID[2] = s2_D.getDouble(0);
    return s2_PID[term];
  }

  /**
   * Returns the value of the PID terms for the Antler
   * @param term 0 - P, 1 - I, 2 - D
   * @return The value of the selected PID Term 
   */
  public double getAntlerPID(int term) {
    ant_PID[0] = antP.getDouble(0);
    ant_PID[1] = antI.getDouble(0);
    ant_PID[2] = antD.getDouble(0);
    return ant_PID[term];
  }

  /**
   * Returns the value of the PID terms for the Hatch Manipulator
   * @param term 0 - P, 1 - I, 2 - D
   * @return The value of the selected PID term
   */
  public double getHatchPID(int term) {
    hat_PID[0] = hatP.getDouble(0);
    hat_PID[1] = hatI.getDouble(0);
    hat_PID[2] = hatD.getDouble(0);
    return hat_PID[term];
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
