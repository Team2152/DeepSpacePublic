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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Hatch.HatchMove;


/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  private WPI_TalonSRX left;  
  
  private DoubleSolenoid expelSolenoid;

  
public Hatch(){
  
  left = new WPI_TalonSRX(RobotMap.HATCH_CANID);
    left.setNeutralMode(NeutralMode.Brake);
    left.setSafetyEnabled(false);
    
    left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  
  expelSolenoid = new DoubleSolenoid(0, 1);
  expelSolenoidOpen();
  resetEncoder();


}

public  void hatchSpeed(double speed) {
  left.set(ControlMode.PercentOutput, speed);
}



public int returnEncoderValue(){
  return left.getSelectedSensorPosition();
}

public void setRampRate(double seconds){
  left.configOpenloopRamp(seconds);
}

public void resetEncoder(){
  left.setSelectedSensorPosition(0);
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




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HatchMove(.5));
  }
}
