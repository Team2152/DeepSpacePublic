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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.utilities.PIDConstants;
import frc.robot.utilities.SparkMaxPIDSource;

/**
 * Add your docs here.
 */
public class StageOneArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax topRight;
  private CANSparkMax topLeft;
  private CANSparkMax bottom;
  private SparkMaxPIDSource sparkMaxPIDSource;  
  private CANEncoder topRightEncoder;
  private CANEncoder topLeftEncoder;
  private CANEncoder bottemEncoder;
  private Encoder armEncoder;
  private DigitalInput zeroSwitch;
  private double averageNeoEncoderValue;

  public StageOneArm(){

    topRight = new CANSparkMax(RobotMap.STAGE_ONE_CANID_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    topRightEncoder = topRight.getEncoder();
    
    topLeft  = new CANSparkMax(RobotMap.STAGE_ONE_CANID_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    topLeft.follow(topRight);
    topLeftEncoder = topLeft.getEncoder();

    bottom   = new CANSparkMax(RobotMap.STAGE_ONE_CANID_B, CANSparkMaxLowLevel.MotorType.kBrushless);
    bottom.follow(topRight);
    bottemEncoder = bottom.getEncoder();    
    
 //   sparkMaxPIDSource = new SparkMaxPIDSource(topRight);
    
    zeroSwitch = new DigitalInput(RobotMap.STAGE_ONE_SWTICH);
  //  armEncoder = new Encoder(RobotMap.STAGE_ONE_ENCODER_SOURCE_A, RobotMap.STAGE_ONE_ENCODER_SOURCE_B);
  }
 

public void stageOneSpeed(double speed){
    topRight.set(speed);
  }

public void setRampRate(){
  topRight.setRampRate(PIDConstants.SO_SECOUNDS_TO_FULL);
}

public double getEncoderValue(){  
  return armEncoder.get();
}

public double getNeoEncoderValue(){
averageNeoEncoderValue = (topRightEncoder.getPosition() + topLeftEncoder.getPosition() + bottemEncoder.getPosition());
return (averageNeoEncoderValue/3)*11/3;
}

public boolean isArmStowed(){
  return zeroSwitch.get();
}

public void resetEncoder(){
  armEncoder.reset();
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
