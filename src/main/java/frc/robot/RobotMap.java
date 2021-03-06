/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


//-----DriveTrain-----
  public static final int DRIVETRAIN_CANID_RIGHT1    = 1;
  public static final int DRIVETRAIN_CANID_RIGHT2    = 2;

  public static final int DRIVETRAIN_CANID_LEFT1     = 3;
  public static final int DRIVETRAIN_CANID_LEFT2     = 4;

  public static final int PIGEONIMU_CANID            = 0;

  //-----Hatch-----
  public static final int HATCH_CANID                = 5;  
           
  //-----StageOneArm-----
  public static final int ARM_CANID_R          = 6;
  public static final int ARM_CANID_L          = 7;
  public static final int ARM_STOWED_SWTICH           = 9;

   //-----Cargo-----
   public static final int CARGO_TOP_ROLLER_CANID     = 8;
   public static final int CARGO_BOTTOM_ROLLER_CANID  = 9;

  //-----Antlers-----
  public static final int ANTLER_CANID_RIGHT         = 12;
  public static final int ANTLER_CANID_LEFT          = 13;
  public static final int ANTLER_STOWED_SWITCH       = 0;
}