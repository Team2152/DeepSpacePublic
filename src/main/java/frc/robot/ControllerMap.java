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

	//-----Hatch-----
	public static final int HATCH_AXIS_LT = 2;
	public static final int HATCH_AXIS_RT = 3;

	public static void setControllers(Joystick driver, Joystick operator) {
		DriveTrain_Drive_Joystick = driver;
	}

}