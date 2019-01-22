/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class Dashboard extends Subsystem {
  
  private ShuffleboardTab mainTab = Shuffleboard.getTab("squishCat"); //Trevor named it.

  private NetworkTableEntry motorGains = mainTab.add("Motor Gains", 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1, "block increment", 0.05)).getEntry();
  // Used to test getMotorGains()
  public NetworkTableEntry motorGainsOutput = mainTab.add("Motor Gains Output", 0).withWidget(BuiltInWidgets.kNumberBar).getEntry();
  
  /**
   * returns the motor gains value stored on Shuffleboard
   */
  public double getMotorGains() {
    double d = motorGains.getDouble(1);
    return d;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
