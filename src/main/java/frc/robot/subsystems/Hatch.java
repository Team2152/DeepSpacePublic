/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Hatch.HatchMove;
import frc.robot.utilities.MotorControllerPIDSource;
import frc.robot.utilities.PIDConstants;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  private WPI_TalonSRX left;  
  private Encoder leftEncoder;
  private DoubleSolenoid expelSolenoid;
  private DoubleSolenoid lockSolenoid;
  private MotorControllerPIDSource motorControllerPIDSource;
  
public Hatch(){
  
  left = new WPI_TalonSRX(RobotMap.HATCH_CANID);
    left.setNeutralMode(NeutralMode.Brake);
    left.setSafetyEnabled(false);
    
    left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  lockSolenoid  = new DoubleSolenoid(2, 3);
  expelSolenoid = new DoubleSolenoid(0, 1);
  expelSolenoidOpen();
  
  motorControllerPIDSource = new MotorControllerPIDSource(left);

  

  resetEncoder();


}

public  void hatchSpeed(double speed) {
  left.set(ControlMode.PercentOutput, speed);
}



public int returnEncoderValue(){
  return left.getSelectedSensorPosition();
}

public void setRampRate(){
  left.configClosedloopRamp(PIDConstants.H_SECOUNDS_TO_FULL);
}

public void resetEncoder(){
  left.setSelectedSensorPosition(0);
}

public MotorControllerPIDSource getMotorControllerPIDSource(){
  return motorControllerPIDSource;
}

public void lockSolenoidOpen(){
  lockSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public void lockSolenoidClose(){
  lockSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void expelSolenoidOpen() {
  expelSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public void expelSolenoidClose() {
  expelSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void expelSolenoidToggle(){
  if(expelSolenoid.get() == DoubleSolenoid.Value.kForward){
    expelSolenoidOpen();
  }else{
    expelSolenoidClose();
  }
}

public void lockSolenoidToggle(){
  if(lockSolenoid.get() == DoubleSolenoid.Value.kForward){
    lockSolenoidOpen();
  }else{
    lockSolenoidClose();
  }
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HatchMove(.5));
  }
}
