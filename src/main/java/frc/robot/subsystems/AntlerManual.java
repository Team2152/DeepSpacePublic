/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.commands.AntlerMove;
/**
 * Add your docs here.
 */
public class AntlerManual extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax right;
  private CANSparkMax left;
  

  public AntlerManual(){
  

    right = new CANSparkMax(RobotMap.ANTLER_CANID_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
    right.setInverted(false);

    left = new CANSparkMax(RobotMap.ANTLER_CANID_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    left.follow(right, true);
    
  } 


  public void antlerManMove(double speed){
    right.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AntlerMove(.25));
  }
}
