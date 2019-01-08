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
	public static final int DRIVETRAIN_TURN_ID     = 0;



	public static void setControllers(Joystick driver, Joystick operator) {
		DriveTrain_Drive_Joystick = driver;
	}

}
