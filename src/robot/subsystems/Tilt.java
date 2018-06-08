/**
 * 
 */
package robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 *
 *
 */
public class Tilt extends Subsystem {
	
	private TalonSRX tilt = new WPI_TalonSRX(RobotMap.TILT);

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		tilt.
	}

}
