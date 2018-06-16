/**
 * 
 */
package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.commands.tilt.*;
import robot.HoundTalon;
import robot.RobotMap;
import robot.Utilities;

/**
 *     NOTE: this is my first time ever writing robot code please don't yell at me
 *
 */
public class Tilt extends Subsystem {
	
	// TODO insert actual numbers for these values
	public static final double UP = 921;
	public static final double MIDDLE = 895;
	public static final double DOWN = 847;
	
	private HoundTalon tiltMotor;

	// the initial method
	public Tilt()	{
		tiltMotor = new HoundTalon(RobotMap.TILT, "Tilt", "Tilt");
		tiltMotor.setInverted(false);
		config(tiltMotor);
		
	}
	
	// configures the talon
	private void config(HoundTalon talon)	{

		talon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, Utilities.CONFIG_TIMEOUT);
		talon.setNeutralMode(NeutralMode.Brake);
		
	}
	
	// sets motor to a percent power
	public void setTiltPower(double power)	{
		tiltMotor.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	// gets the position of the encoder
	public double getPosition()	{
		return tiltMotor.getSelectedSensorPosition(0);
	}
	
	//sets the position
	public void setTiltPosition(double setpoint) {
		// TODO Auto-generated method stub
		
	}
	
	// the default command
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

		setDefaultCommand(new SetTiltPosition(Tilt.DOWN));
		
	}



}
