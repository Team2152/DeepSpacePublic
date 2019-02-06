/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.commands.AntlerMove;
/**
 * Add your docs here.
 */
public class AntlerManual extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax left;
  private CANSparkMax right;
  

  public AntlerManual(){
    left = new CANSparkMax(RobotMap.ANTLER_CANID_LEFT, MotorType.kBrushless);
    right = new CANSparkMax(RobotMap.ANTLER_CANID_RIGHT, MotorType.kBrushless);
    right.follow(left);
    right.setInverted(true);
  }

  public void AntlerManualMove(double speed){
    left.set(speed);  
  } 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AntlerMove(.5));
  }
}
