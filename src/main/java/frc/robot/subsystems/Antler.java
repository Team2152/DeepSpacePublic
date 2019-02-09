/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.utilities.PIDConstants;
import frc.robot.utilities.SparkMaxPIDSource;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
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
  private DigitalInput zeroSwitch;
  private SparkMaxPIDSource sparkMaxPIDSource; 


  public Antler(){
    left  = new CANSparkMax(RobotMap.ANTLER_CANID_LEFT, MotorType.kBrushless);
    leftEncoder = left.getEncoder();
    

    right = new CANSparkMax(RobotMap.ANTLER_CANID_RIGHT, MotorType.kBrushless);
    right.follow(left);
    
    zeroSwitch = new DigitalInput(RobotMap.ANTLER_SWITCH);
    sparkMaxPIDSource = new SparkMaxPIDSource(left, leftEncoder);
    


  }
  public void AntlerSpeed(double speed){
    left.set(speed);
  }

  public void setRampRate(){
    left.setRampRate(PIDConstants.A_SECOUNDS_TO_FULL);
  }

  public double getEncoderValue(){
   return leftEncoder.getPosition();
  }
  
  //add rest to spark in update

 public boolean isAntlerStowed(){
  return zeroSwitch.get();
 }



  public SparkMaxPIDSource getSparkMaxPIDSource(){
    return sparkMaxPIDSource;
  }



  public SparkMaxPIDSource getSparkMAxPIDSource(){
    return sparkMaxPIDSource;
  }
  







  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}