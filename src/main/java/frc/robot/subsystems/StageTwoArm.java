/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.utilities.IMUSource;
import frc.robot.utilities.PIDConstants;

/**
 * Add your docs here.
 */
public class StageTwoArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX right;
  private WPI_TalonSRX left;
  private PigeonIMU pigeon;
  private double[] getPigeonValue = {3};
  private IMUSource imuSource;
  private DigitalInput zeroSwitch;

  public StageTwoArm(){
  
    right = new WPI_TalonSRX(RobotMap.STAGE_TWO_CANID_R);
    pigeon = new PigeonIMU(right);
    left  = new WPI_TalonSRX(RobotMap.STAGE_TWO_CANID_L);
    left.follow(right);
    zeroSwitch = new DigitalInput(RobotMap.STAGE_TWO_SWITCH);
    
    

  }

  public double getPigeon(){
    pigeon.getYawPitchRoll(getPigeonValue);
    return getPigeonValue[1];
  }

  public void stageTwoSpeed(double speed){
  right.set(speed);
  }

  public boolean isArmStowed(){
    return zeroSwitch.get();
  }

  public void setRampRate(){
    right.configClosedloopRamp(PIDConstants.ST_SECOUNDS_TO_FULL);
  }

  public void resetiMU(){
    getPigeonValue[1] = 0;
  }
  


  public  IMUSource getIMUPIDSource(){
    return imuSource;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
