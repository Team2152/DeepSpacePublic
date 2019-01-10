/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static org.junit.Assume.assumeNoException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchMove;
/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
 private VictorSPX hatchMotor;
 private DoubleSolenoid hatchSolenoid;

public Hatch(){
  hatchMotor = new VictorSPX(RobotMap.HATCH_CANID);

  hatchSolenoid = new DoubleSolenoid(0, 1);
	hatchSolenoidClose();
}

public  void hatchMover(double hatchSpeed) {
  hatchMotor.set(ControlMode.PercentOutput, hatchSpeed);
}

public void hatchSolenoidOpen() {
  hatchSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public void hatchSolenoidClose() {
  hatchSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void hatchSolenoidToggle(){
  if(hatchSolenoid.get() == DoubleSolenoid.Value.kForward){
    hatchSolenoidOpen();
  }else{
    hatchSolenoidClose();
  }
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HatchMove(.5));
  }
}
