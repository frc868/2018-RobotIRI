package robot.subsystems;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.gyrohelpers.BNO055;

public class Gyro extends Subsystem {
	private GyroBase gyro;
	public static final boolean DEBUG = false;
	
	public Gyro() {
		gyro =  BNO055.getInstance(I2C.Port.kOnboard).createGyroX();
		gyro.reset();
		
		// TODO: livewindow stuff
	}
	
	/**
	 * Gets current rotation of the robot.
	 * @return rotation of the robot (degrees)
	 */
	public double getAngle() {
		return gyro.getAngle();
	}
	
	//TODO: figure out what exactly reset means in this case
	
	public void reset() {
		gyro.reset();
	}
	
    public void initDefaultCommand() {}
}

