package frc.robot.utilities;
/**
 * This class is used to hold gain and deadzone values to be applied to inputs.
 */
public class Gain {
	public final static String DRIVE_TRAIN_GAIN = "Drive Train Gain";
	public final static String DRIVE_TRAIN_GAIN_CRAB = "Drive Train Gain Crab";
	public final static String DRIVE_TRAIN_GAIN_ROTATION = "Drive Train Gain Rotation";
	public final static double PCT_0 = 0.0;
	public final static double PCT_25 = 0.25;
	public final static double PCT_50 = 0.5;
	public final static double PCT_75 = 0.75;
	public final static double PCT_100 = 1.0;
	public final static double DEFAULT_DEADBAND = 0.10; // Suited to drive
														// train, but not
														// necessarily other
														// types of motor/servos
	
	public final static double XBOX_DEADBAND = 0.25;
	private double gain = PCT_100; // === default is FULL POWER ===
	private double deadBand = DEFAULT_DEADBAND; // === default deadband value

	public Gain() {
		// === use default gains ===
	}

	public Gain(double g, double d) {
		// === set specific gain on create ===
		gain = g;
		deadBand = d;
	}

	/**
	 * Getter method for gain
	 * @return gain
	 */
	public double getGain() {
		return gain;
	}

	/**
	 * Setter method for gain
	 * @param g value to replace gain
	 * 
	 */
	public void setGain(double g) {
		gain = g;
	}

	/**
	 * Getter method for deadband
	 * @return deadband
	 */
	public double getDeadBand() {
		return deadBand;
	}

	/**
	 * Setter method for deadband
	 * @param d value to replace deadband
	 */
	public void setDeadBand(double d) {
		deadBand = d;
	}

	/**
	 * Modifies the Provided input value using gain multiplier and deadzone. <br>
	 * Deadband controls the minumum input value that will be recognized as a nonzero input.
	 * Gain controls the maximum value of the output.
	 * @param value 
	 * value to be modified
	 * @return modified input
	 */
	public double applyGain(double value) {
		double absVal = Math.abs(value);
		if (absVal < deadBand)
			return 0;
		else {
			return (value * gain);
		}
	}
}