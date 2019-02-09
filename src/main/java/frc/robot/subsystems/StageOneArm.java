/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.utilities.PIDConstants;
import frc.robot.utilities.SparkMaxPIDSource;
import frc.robot.commands.LiftMove;;
/**
 * Add your docs here.
 */
public class StageOneArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX left;
  private WPI_TalonSRX right;



  // private CANSparkMax left;
  // private CANSparkMax right;
  //private CANEncoder  leftEncoder;
  private SparkMaxPIDSource sparkMaxPIDSource;  
 
  private DigitalInput zeroSwitch;
 

  public StageOneArm(){


    left = new WPI_TalonSRX(RobotMap.STAGE_ONE_CANID_L);
    right = new WPI_TalonSRX(RobotMap.STAGE_ONE_CANID_R);
      right.follow(left);
      right.setInverted(true);

    
    // left  = new CANSparkMax(RobotMap.STAGE_ONE_CANID_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
     
    // right = new CANSparkMax(RobotMap.STAGE_ONE_CANID_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    //  right.follow(left, true);

  //  sparkMaxPIDSource = new SparkMaxPIDSource(left, leftEncoder);

    zeroSwitch = new DigitalInput(RobotMap.STAGE_ONE_SWTICH);
   
  }
 

public void stageOneSpeed(double speed){
    left.set(speed);
  }

// public void setRampRate(){
//   left.setRampRate(PIDConstants.SO_SECOUNDS_TO_FULL);
// }

// public double getEncoderValue(){  
//   return armEncoder.get();
// }



public boolean isArmStowed(){
  return zeroSwitch.get();
}

//add reset to spark in update

public SparkMaxPIDSource getSparkMAxPIDSource(){
  return sparkMaxPIDSource;
}




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LiftMove(.75));
  }
}
