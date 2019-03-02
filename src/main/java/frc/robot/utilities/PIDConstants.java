package frc.robot.utilities;

public class PIDConstants{
    //public static final double EXAMPLE_kP           = 0;
	//public static final double EXAMPLE_kI           = 0;
	//public static final double EXAMPLE_kD           = 0;
	//public static final double EXAMPLE_kTolerance   = 0;
	//public static final double EXAMPLE_SPEED        = 0;
	//public static final double EXAMPLE_DRIVE_RAMP_RATE       = 0;
	//public static final int    EXAMPLE_DRIVE_RAMP_TIMEOUT       = 0;


//-----StageOneArm-----
public static final double SO_MAX_FORWARD_SPEED   = 1;
public static final double SO_MAX_RETURN_SPEED    = -1;
public static final double SO_SECOUNDS_TO_FULL    = 0;
public static final double SO_ENCODER_kP      = 0;
public static final double SO_ENCODER_kI      = 0;
public static final double SO_ENCODER_kD      = 0;
public static final double SO_DISTANCE_TOLARANCE  = 0;
public static final double SO_MININUM_INPUT_RANGE = 0;
public static final double SO_MAXIMUM_INPUT_RANGE = 0;


//-----StageTwoArm-----
public static final double ST_MAX_FORWARD_SPEED   =  1;
public static final double ST_MAX_RETURN_SPEED    = -1;
public static final double ST_SECOUNDS_TO_FULL    =  0;
public static final double ST_IMU_kP              =  0;
public static final double ST_IMU_kI              =  0;
public static final double ST_IMU_kD              =  0;
public static final double ST_DISTANCE_TOLARANCE  =  0;
public static final double ST_MININUM_INPUT_RANGE =  0;
public static final double ST_MAXIMUM_INPUT_RANGE =  0;

//-----Antler-----
public static final double A_MAX_FORWARD_SPEED   =  .5;
public static final double A_MAX_RETURN_SPEED    = -.5;
public static final double A_SECOUNDS_TO_FULL    =  .25;
public static final double A_ENCODER_kP      =  1;
public static final double A_ENCODER_kI      =  0;
public static final double A_ENCODER_kD      =  0;
public static final double A_DISTANCE_TOLARANCE  =  0;
public static final double A_MININUM_INPUT_RANGE =  0;
public static final double A_MAXIMUM_INPUT_RANGE =  0;

//-----Hatch-----
public static final double H_MAX_FORWARD_SPEED   = .25;
public static final double H_MAX_RETURN_SPEED    = -.3;
public static final double H_SECOUNDS_TO_FULL    = .75;
public static final double H_ENCODER_kP      =  .75;//.00049;
public static final double H_ENCODER_kI      = .55;
public static final double H_ENCODER_kD      = 1.8;
public static final double H_DISTANCE_TOLARANCE  = 10;
public static final double H_MININUM_INPUT_RANGE = 0;
public static final double H_MAXIMUM_INPUT_RANGE = 5800;
}