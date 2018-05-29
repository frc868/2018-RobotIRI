/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int DRIVETRAINRIGHT2 = 0;
	public static final int DRIVETRAINRIGHT = 1;
	public static final int DRIVETRAINLEFT = 2;
	public static final int DRIVETRAINLEFT2 = 4;
	public static final int DRIVETRANS = 3;
	
	
	public static int ARM = 0; //TODO: put the real value in here
	
	// Motors (CAN)
	public static final int DRIVE_LEFT_PRIMARY = 25;
	public static final int DRIVE_LEFT_SECONDARY = 24;
	public static final int DRIVE_RIGHT_PRIMARY = 10;
	public static final int DRIVE_RIGHT_SECONDARY = 11;
	public static final int INTAKE_LEFT = 14;
	public static final int INTAKE_RIGHT = 15;
	public static final int POWER_PACK_PRIMARY = 23;
	public static final int POWER_PACK_SECONDARY = 22;
	public static final int POWER_PACK_TERTIARY = 21;
	public static final int POWER_PACK_QUATERNARY = 20;
	public static final int TILT = 13;
	
	// Pneumatics (PCM)
	public static final int DRIVE_TRANSMISSION = 7;
	public static final int ARMS = 6;
	public static final int WINCH_TRANSMISSION = 5;
	public static final int WINCH_BRAKE = 4;
	public static final int HOOK = 0;
	
	

	
	// Sensors (Analog)
	
	// Sensors (Digital)	
	
	// Drivetrain
}

