/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.armMove;
/**
 * Add your docs here.
 */
public class ArmTest extends Subsystem {
 private WPI_TalonSRX armMotor;
;

public ArmTest(){
  armMotor = new WPI_TalonSRX(RobotMap.ARM_DELETE_LATER);
  }

public  void armMover(double speed) {
  armMotor.set(speed);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new armMove(.50));
  }
}
