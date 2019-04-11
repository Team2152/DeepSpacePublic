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



/**
 * Add your docs here.
 */
public class Hatch extends Subsystem { 
  
  private DoubleSolenoid expelSolenoid;
  private DoubleSolenoid extendSolenoid;

  
public Hatch(){
  
  expelSolenoid = new DoubleSolenoid(0, 1);
  extendSolenoid = new DoubleSolenoid(2, 3); 
  
//fix naming convention
  extendClose();
  retractHatch();

  expelSolenoid.set(DoubleSolenoid.Value.kOff);
  extendSolenoid.set(DoubleSolenoid.Value.kOff);

}

public void setExtendSolenoid(DoubleSolenoid.Value direction){
  extendSolenoid.set(direction);
}

public void setExpelSolenoid(DoubleSolenoid.Value direction){
  expelSolenoid.set(direction);
}
public void extendOpen() {
  expelSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public void extendClose() {
  expelSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void extendToggle(){
  if(expelSolenoid.get() == DoubleSolenoid.Value.kForward){
    extendOpen();
  }else{
    extendClose();
  }
}

public void extendHatch(){
  extendSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void retractHatch(){
  extendSolenoid.set(DoubleSolenoid.Value.kReverse);

}

public void extensionSolenoidToggle(){
  if(extendSolenoid.get() == DoubleSolenoid.Value.kForward){
    retractHatch();
  } else {
    extendHatch();
  }
}

public void clearStickyFaults(){
  expelSolenoid.clearAllPCMStickyFaults();
}

public boolean getStickyFaults(){
  return expelSolenoid.getPCMSolenoidVoltageStickyFault();
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
