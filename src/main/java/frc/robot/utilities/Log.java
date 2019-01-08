package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Log {

	public final static String LOG_SETTING = "Enable Logging?";
	public final static boolean LOG_ENABLE = true;
	public final static boolean LOG_DISABLE = false;
	public final static boolean LOG_DEFAULT = LOG_DISABLE;

	private boolean bConsoleLog = true;

	public Log(boolean enable) {
		bConsoleLog = enable;
	}

	public void setLogToConsole(boolean value) {
		bConsoleLog = value;
	}

	public void console(String message) {
		bConsoleLog = SmartDashboard.getBoolean(LOG_SETTING, LOG_DEFAULT);
		if (bConsoleLog == true)
			System.out.println(message);
	}
}
