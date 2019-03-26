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

  expelSolenoidOpen();
  retractHatch();

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




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
