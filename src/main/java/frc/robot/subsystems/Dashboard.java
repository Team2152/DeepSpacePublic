/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class Dashboard extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void createShuffleboard() {
    ShuffleboardTab testTab = Shuffleboard.getTab("Test Tab");
    testTab.add("Test Slider", 1).withSize(2, 1).withPosition(0, 0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
    testTab.add("Test Dial", 1).withSize(1,1).withPosition(2,0).withWidget(BuiltInWidgets.kDial).getEntry();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
