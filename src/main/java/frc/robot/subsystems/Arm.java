/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.utilities.PIDConstants;
import frc.robot.utilities.MotorControllerPIDSource;
import frc.robot.utilities.NumericConstants;
import frc.robot.commands.Arm.ArmMove;
import frc.robot.commands.Arm.ArmRamp;
/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // private WPI_TalonSRX left;
  // private WPI_TalonSRX right;



  private CANSparkMax left;
  private CANSparkMax right;
  private CANEncoder  leftEncoder;
  private DigitalInput stowedSwitch;
  

  public Arm(){


    
    left  = new CANSparkMax(RobotMap.ARM_CANID_L, CANSparkMaxLowLevel.MotorType.kBrushless);
    left.setIdleMode(IdleMode.kBrake);
    leftEncoder = new CANEncoder(left);
    leftEncoder.setPosition(0);
     
    right = new CANSparkMax(RobotMap.ARM_CANID_R, CANSparkMaxLowLevel.MotorType.kBrushless);
     right.setIdleMode(IdleMode.kBrake);
     right.follow(left, true);

     stowedSwitch = new DigitalInput(RobotMap.ARM_STOWED_SWTICH);
     resetEncoder();
  }


public boolean getStowedSwitch(){
  return !stowedSwitch.get();
}
 

public void setSpeed(double speed){
    left.set( speed);
  }

public void setRampRate(){
  left.setClosedLoopRampRate(NumericConstants.ARM_SECOUNDS_TO_FULL);
}

public double getEncoderValue(){  
  return leftEncoder.getPosition();
}

public void resetEncoder(){
  leftEncoder.setPosition(0);
}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    ///setDefaultCommand(new ArmMove(.50));
    setDefaultCommand(new ArmRamp(1));
  }
}
