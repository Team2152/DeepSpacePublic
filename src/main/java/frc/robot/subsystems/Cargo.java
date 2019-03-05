/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.CargoMove;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  WPI_TalonSRX topRoller;
  WPI_TalonSRX bottomRoller;

  public Cargo(){
    topRoller = new WPI_TalonSRX(RobotMap.CARGO_TOP_ROLLER_CANID);
    
    bottomRoller = new WPI_TalonSRX(RobotMap.CARGO_BOTTOM_ROLLER_CANID);
    bottomRoller.follow(topRoller);
  }

  /**
   * Sets the speed of the rollers
   * @param speed in percent output (-1 to 1)
   */
  public void setSpeed(double speed){
    topRoller.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoMove(0.75));
  }
}
