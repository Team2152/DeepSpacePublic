/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.utilities.PIDConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Antler.AntlerMove;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Antler extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax left;
  private CANSparkMax right;
  private CANEncoder  leftEncoder;
  private DigitalInput stowedSwitch;
  

  public Antler(){
    
    left  = new CANSparkMax(RobotMap.ANTLER_CANID_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    leftEncoder = left.getEncoder();
    
    right = new CANSparkMax(RobotMap.ANTLER_CANID_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
      right.follow(left, true);
    
    stowedSwitch = new DigitalInput(RobotMap.ANTLER_STOWED_SWITCH);
  
    resetEncoder();

  }
  public void setSpeed(double speed){
    left.set(speed);
  }

  public void setRampRate(){
    left.setClosedLoopRampRate(PIDConstants.A_SECOUNDS_TO_FULL);
  }

  public double getEncoderValue(){
   return Math.abs(leftEncoder.getPosition());
  }
  
 public void resetEncoder(){
   leftEncoder.setPosition(0);
 }

 /**
  * @return the stowedSwitch
  */
 public boolean getStowedSwitch() {
   return !stowedSwitch.get();
 }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AntlerMove(.50));
  }
}
