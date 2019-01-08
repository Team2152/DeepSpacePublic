package frc.robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	public double getGain() {
		return gain;
	}

	public void setGain(double g) {
		gain = g;
	}

	public double getDeadBand() {
		return deadBand;
	}

	public void setDeadBand(double g) {
		deadBand = g;
	}

	public double applyGain(double value) {
		double absVal = Math.abs(value);
		if (absVal < deadBand)
			return 0;
		else {
			gain = SmartDashboard.getNumber("Motor Gains", 1.0);
			return (value * gain);
		}
	}

	public double applyCrabGain(double value) {
		double absVal = Math.abs(value);
		if (absVal < deadBand)
			return 0;
		else {
			gain = SmartDashboard.getNumber("Motor Gains", 1.0);
			return (value * gain);
		}
	}

	public double applyRotationGain(double value) {
		double absVal = Math.abs(value);
		if (absVal < deadBand)
			return 0;
		else {
			gain = SmartDashboard.getNumber("Motor Gains", 1.0);
			return (value * gain);
		}
	}
}