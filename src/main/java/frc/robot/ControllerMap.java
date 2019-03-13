package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Holds all button ID and command joystick information
 */
public class ControllerMap {

	//-----Commands-----
	
		//-----DriveTrain-----
	public static Joystick DriveTrain_Drive_Joystick;
	public static final int DRIVETRAIN_THROTTLE_ID = 1;
	public static final int DRIVETRAIN_TURN_ID     = 4;
	public static final int DRIVETRAIN_QUICK_TURN  = 5;
	//-----Hatch-----
	public static final int HATCH_TRIGGER_L = 2;
	public static final int HATCH_TRIGGER_R = 3;
	
	//-----Cargo-----
	public static final int CARGO_TRIGGER_L = 2;
	public static final int CARGO_TRIGGER_R = 3;
	public static final int CARGO_BUMP_L = 5;
	public static final int CARGO_BUMP_R = 6;

	//-----ARMS-----
	public static final int ARM_JOYSTICK_R = 5;
	// public static final int ARM_BUMP_R    = 5;
	// public static final int ARM_BUMP_L    = 6;

	//-----ANTLER-----
	public static final int ANTLER_BUTTON_Y = 4;
	public static final int ANTLER_BUTTON_B = 2;

	public static void setControllers(Joystick driver, Joystick operator) {
		DriveTrain_Drive_Joystick = driver;
	}

}
