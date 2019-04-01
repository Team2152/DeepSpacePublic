/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.hal.sim.mockdata.PCMDataJNI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SolenoidBase;
/**
 * Add your docs here.
 */
public class AirCompressor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Compressor compressor;
  private Boolean toggle = false;
  
  public AirCompressor(){
    compressor = new Compressor();
    
  }

  public void startCompressor(){
    compressor.start();
    toggle = true;
  }

  public void stopCompressor(){
    compressor.stop();
    toggle = false;
  }

  public void toggleCompressor(){
    if(toggle == true){
      compressor.stop();
      toggle = false;
    } else {
      compressor.start();
      toggle = true;
    }
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
