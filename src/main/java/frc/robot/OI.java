/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.utilities.SharedButton;
// import frc.robot.commands.LimeDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.CommandUtils.CompressorToggle;
import frc.robot.commands.DriveTrain.DriveToggleInversion;
import frc.robot.commands.Hatch.AutoExpelSolenoidToggle;
import frc.robot.commands.Hatch.ExpellHatch;
import frc.robot.commands.AutoSequentials.ClimbDumb;
import frc.robot.commands.Hatch.HatchExpelSolenoidToggle;
import frc.robot.commands.Hatch.HatchExpelSolenoidToggle;
import frc.robot.commands.CommandUtils.StopAll;;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // public final static int SPEED_STICK = 999999; // Deoderant...always wear it
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public final static int POV_0 = 0;
	public final static int POV_45 = 45;
	public final static int POV_90 = 90;
	public final static int POV_135 = 135;
	public final static int POV_180 = 180;
	public final static int POV_225 = 225;
	public final static int POV_270 = 270;
	public final static int POV_315 = 315;

	public final static int OPERATOR_XBOX = 1;
	public final static int DRIVER_XBOX = 0;

	// === Axis ids; they are the same for each joystick
	public final static int XBOX_LEFT_XAXIS = 0;
	public final static int XBOX_LEFT_YAXIS = 1;
	public final static int XBOX_RIGHT_XAXIS = 4;
	public final static int XBOX_RIGHT_YAXIS = 5;

	public final static int XBOX_LEFT_TRIGGER = 2;
	public final static int XBOX_RIGHT_TRIGGER = 3;

	public final static int buttonAid = 1;
	public final static int buttonBid = 2;
	public final static int buttonXid = 3;
	public final static int buttonYid = 4;
	public final static int buttonBumpLid = 5;
	public final static int buttonBumpRid = 6;
	public final static int buttonBackid = 7;
	public final static int buttonStartid = 8;
	public final static int buttonLClickid = 9;
	public final static int buttonRClickid = 10;

	public Joystick operatorXbox;
	private Button oButtonA;
	private Button oButtonB;
	private Button oButtonX;
	private Button oButtonY;
	private Button oButtonBumpL;
	private Button oButtonBumpR;
	private Button oButtonBack;
	private Button oButtonStart;
	private Button oButtonLClick;
	private Button oButtonRClick;
	private POVButton oPOV0;
	private POVButton oPOV45;
	private POVButton oPOV90;
	private POVButton oPOV135;
	private POVButton oPOV180;
	private POVButton oPOV225;
	private POVButton oPOV270;
	private POVButton oPOV315;

	public Joystick driverXbox;
	private Button dButtonA;
	private Button dButtonB;
	private Button dButtonX;
	private Button dButtonY;
	private Button dButtonBumpL;
	private Button dButtonBumpR;
	private Button dButtonBack;
	private Button dButtonStart;
	private Button dButtonLClick;
    private Button dButtonRClick;
  
	private POVButton dPOV0;
	private POVButton dPOV45;
	private POVButton dPOV90;
	private POVButton dPOV135;
	private POVButton dPOV180;
	private POVButton dPOV225;
	private POVButton dPOV270;
    private POVButton dPOV315;

	private SharedButton expelHatch;
	//private SharedTriggers expelCargo;
  public OI() {
		// Setup driver joystick
		try {
			driverXbox = new Joystick(DRIVER_XBOX);
			dButtonA = new JoystickButton(driverXbox, buttonAid);
			dButtonB = new JoystickButton(driverXbox, buttonBid);
			dButtonX = new JoystickButton(driverXbox, buttonXid);
			dButtonY = new JoystickButton(driverXbox, buttonYid);
			dButtonBumpL = new JoystickButton(driverXbox, buttonBumpLid);
			dButtonBumpR = new JoystickButton(driverXbox, buttonBumpRid);
			dButtonBack = new JoystickButton(driverXbox, buttonBackid);
			dButtonStart = new JoystickButton(driverXbox, buttonStartid);
			dButtonLClick = new JoystickButton(driverXbox, buttonLClickid);
			dButtonRClick = new JoystickButton(driverXbox, buttonRClickid);
			dPOV0 = new POVButton(driverXbox, POV_0);
			dPOV90 = new POVButton(driverXbox, POV_90);
			dPOV135 = new POVButton(driverXbox, POV_135);
			dPOV180 = new POVButton(driverXbox, POV_180);
			dPOV225 = new POVButton(driverXbox, POV_225);
			dPOV270 = new POVButton(driverXbox, POV_270);
			dPOV315 = new POVButton(driverXbox, POV_315);
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup driver joystick: " + e.toString());
		}

		try {
			operatorXbox = new Joystick(OPERATOR_XBOX);
			oButtonA = new JoystickButton(operatorXbox, buttonAid);
			oButtonB = new JoystickButton(operatorXbox, buttonBid);
			oButtonX = new JoystickButton(operatorXbox, buttonXid);
			oButtonY = new JoystickButton(operatorXbox, buttonYid);
			oButtonBumpL = new JoystickButton(operatorXbox, buttonBumpLid);
			oButtonBumpR = new JoystickButton(operatorXbox, buttonBumpRid);
			oButtonBack = new JoystickButton(operatorXbox, buttonBackid);
			oButtonStart = new JoystickButton(operatorXbox, buttonStartid);
			oButtonLClick = new JoystickButton(operatorXbox, buttonLClickid);
			oButtonRClick = new JoystickButton(operatorXbox, buttonRClickid);
			oPOV0 = new POVButton(operatorXbox, POV_0);
			oPOV90 = new POVButton(operatorXbox, POV_90);
			oPOV135 = new POVButton(operatorXbox, POV_135);
			oPOV180 = new POVButton(operatorXbox, POV_180);
			oPOV225 = new POVButton(operatorXbox, POV_225);
			oPOV270 = new POVButton(operatorXbox, POV_270);
      oPOV315 = new POVButton(operatorXbox, POV_315);
      
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup operator joystick: " + e.toString());
    }
		ControllerMap.setControllers(driverXbox,operatorXbox);

		setupOperatorButtons();
		setupDriverXboxButtons();

		try {
	  // Shared commands here

			expelHatch = new SharedButton(new Button[] {dButtonX,oButtonX});
		//	expelCargo = new SharedTriggers(new Trigger[] {X});


			setupSharedCommands();
		} catch (Exception e) {
			Robot.m_logger.console("OI: Unable to setup shared commands: " + e.toString());
			
		}
		
	}

	public void setupOperatorButtons() {
		oButtonBack.whenReleased(new CompressorToggle());
		oButtonA.whenReleased(new HatchExpelSolenoidToggle());
	}

	public void setupDriverXboxButtons() {
		//dButtonBack.whenReleased(new HatchExpelSolenoidToggle());
		dButtonStart.whenPressed(new ClimbDumb());
		dButtonBack.whenPressed(new StopAll());
		dPOV180.whenReleased(new DriveToggleInversion());
		
		
	
  }

  public void setupSharedCommands() {
	expelHatch.whenPressed(new ExpellHatch(1));
  }
  
}
