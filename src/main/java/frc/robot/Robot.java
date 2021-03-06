/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.utilities.Log;
import frc.robot.subsystems.Dashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PathFollower;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Cargo;
import frc.robot.Auto.LeftAuto;
import frc.robot.commands.DriveTrain.DrivTrainInversion;
import frc.robot.subsystems.AirCompressor;
import frc.robot.subsystems.Antler;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static Log m_logger;

  public static DriveTrain driveTrainSubsystem = new DriveTrain();
  public static Dashboard spaceDash = new Dashboard();
  public static Hatch hatchSubsystem = new Hatch();
  public static Arm armSubsystem = new Arm();
  public static Cargo cargoSubsystem = new Cargo();
  public static Antler antlerSubsystem = new Antler();
  public static LimeLight limelightSubsystem = new  LimeLight();
  public static PathFollower pathFollowerSubsystem = new PathFollower();  
  public static AirCompressor compressorSubsystem = new AirCompressor();
  
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Command> m_preLoad = new SendableChooser<>();



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
   
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);
    compressorSubsystem.stopCompressor(); // Please stop the compressor.
    Shuffleboard.stopRecording(); // Please stop the recordings.
    SmartDashboard.putData("Auto Mode", m_chooser);
    m_chooser.addDefault("No Auto", null);
    m_chooser.addObject("Left", new LeftAuto());
    //limelightSubsystem.setLedMode(1);
    
   

    
   // Scheduler.getInstance().add(new SensorLog());
    driveTrainSubsystem.resetEncoder();
   
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    limelightSubsystem.log();
    SmartDashboard.putNumber("fused heading", driveTrainSubsystem.pigeon.getFusedHeading());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    driveTrainSubsystem.resetEncoder();
    driveTrainSubsystem.pigeon.setFusedHeading(0);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    
     
   
    }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
   
    m_autonomousCommand = m_chooser.getSelected();


    // m_chooser.addDefault("No Auto", null);
    // m_chooser.addObject("Drive Straight", new AutoStraight());
    // m_chooser.addObject("Straight To CargoShip", new StraightToCargoShip());


    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
    Scheduler.getInstance().run();
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    Robot.armSubsystem.resetEncoder();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
   
    SmartDashboard.putNumber("pigeon", driveTrainSubsystem.getYaw());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
