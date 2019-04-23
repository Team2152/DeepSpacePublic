/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Vision.Seek;

/**
 * Add your docs here.
 */
public class LimeLight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  NetworkTable table;
  NetworkTableEntry tx; 
  NetworkTableEntry ty; 
  NetworkTableEntry ta; 
  NetworkTableEntry ts; 
  NetworkTableEntry tl; 
  NetworkTableEntry tv;
  NetworkTableEntry getpipe;
  NetworkTableEntry camtran;  

  NetworkTableEntry ledMode;
  NetworkTableEntry camMode;
  NetworkTableEntry pipeline;
  NetworkTableEntry stream;
  NetworkTableEntry snapshot;

  double txV;
  double taV;
  double d;
  String camtranV;
  double x,y,z,yaw;
  

  public LimeLight(){

    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx"); 
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    ts = table.getEntry("ts");
    tl = table.getEntry("tl");
    tv = table.getEntry("tv");
    getpipe = table.getEntry("getpipe");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");
    pipeline = table.getEntry("pipeline");
    stream = table.getEntry("stream");
    snapshot = table.getEntry("snapshot");
    camtran = table.getEntry("camtran");

   

    setCamMode(0);
    setLedMode(0);
    setStreamMode(0);
    setPipeline(0);
  }

  public double getTx(){
    return tx.getDouble(0);
  }

  public double getTy(){
    return ty.getDouble(0);
  }

  public double getTv() {
    return tv.getDouble(0.0);
}

/**
 * @return the ta
 */
public double getTa() {
    if(tv.getDouble(0.0)==1) {
        taV = ta.getDouble(taV);
    }
   return taV;
}
  /**
     * @return the ts
     */
    public double getTs() {
      return ts.getDouble(0.0);
  }

  /**
   * @return the tl
   */
  public double getTl() {
      return tl.getDouble(0.0);
  }

  public double getDistance() {
      return 75.315 * Math.pow(getTa(), -.484);
  }

  /*
  Led Modes
  0 - Pipeline settings
  1 - Force off
  2 - Force blink
  3 - Force on
  */
  public void setLedMode(double ledType) {
      ledMode.setNumber(ledType);
  }

  public void setCamMode(double CamType) {
     camMode.setNumber(CamType);
  }

  public void setPipeline(double Pipeline) {
      pipeline.setNumber(Pipeline);
  }

  public void setStreamMode(double Stream) {
      stream.setNumber(Stream);
  }

  public void setSnapshot(double Snapshot) {
      snapshot.setNumber(Snapshot);
  }



  public void log() {
      SmartDashboard.putNumber("tx", getTx());
      SmartDashboard.putNumber("ty", getTy());
      SmartDashboard.putNumber("ta", getTa());
      SmartDashboard.putNumber("tv" , getTv());
      SmartDashboard.putNumber("Distance", getDistance());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
