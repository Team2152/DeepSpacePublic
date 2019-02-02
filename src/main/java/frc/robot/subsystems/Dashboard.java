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
  
  private ShuffleboardTab mainTab = Shuffleboard.getTab("squishCat"); //Trevor named it.

  private double[] armPID = new double[3];
  
  private NetworkTableEntry motorGains = mainTab.add("Motor Gains", 1).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry armP = mainTab.add("Arm P", PIDConstants.SO_ENCODER_ARM_kP).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry armI = mainTab.add("Arm I", PIDConstants.SO_ENCODER_ARM_kI).withWidget(BuiltInWidgets.kTextView).getEntry();
  private NetworkTableEntry armD = mainTab.add("Arm D", PIDConstants.SO_ENCODER_ARM_kD).withWidget(BuiltInWidgets.kTextView).getEntry();
  

  /**
   * returns the motor gains value stored on Shuffleboard
   */
  public double getMotorGains() {
    return motorGains.getDouble(1);
  }

  /**
   * @param value 0 - P, 1 - I, 2 - D
   */
  public double getPIDValue(int value){
    armPID[0] = armP.getDouble(0);
    armPID[1] = armI.getDouble(0);
    armPID[2] = armD.getDouble(0);
    return armPID[value];
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
