/**
 * 
 */
package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;
import robot.Utilities;

/**
 *     NOTE: this is my first time ever writing robot code please don't yell at me
 *
 */
public class Tilt extends Subsystem {
	
	// TODO insert actual numbers for these values
	public static final double UP = 0;
	public static final double MIDDLE = 0;
	public static final double DOWN = 0;
	
	private WPI_TalonSRX tiltMotor;

	// the initial method
	public Tilt()	{
		tiltMotor = Utilities.getTalon(RobotMap.TILT, "Tilt", "Tilt");
		tiltMotor.setInverted(false);
		config(tiltMotor);
		
	}
	
	// configures the talon
	private void config(WPI_TalonSRX talon)	{
		
		
	}
	
	// sets motor to a percent power
	public void setPower(double power)	{
		tiltMotor.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	// gets the position of the encoder
	public double getPosition()	{
		return tiltMotor.getSelectedSensorPosition(0);
	}
	
	// the default command
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		
	}

}
