package robot.subsystems;
import robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hook extends Subsystem {
	
	/**
	 * hook pneumatics
	 */
	private final Solenoid hook = new Solenoid(RobotMap.HOOK);
	
	/**
	 * debug variable to determine if hook shows up in Smart Dashboard
	 */
	public static final boolean DEBUG = false;
	
	//creates actual hook
	public Hook() {
	}
	
	/**
	 * 
	 * @param up true if the hook should be up, else false
	 */
	public void setPosition(boolean up) {
		hook.set(up);
	}
	
	
	/**
	 * 
	 * @return returns true if the hook is up, false if not
	 */
	public boolean isUp() {
		return hook.get();
	}
	
    public void initDefaultCommand() {}

 
	public void initSD() {
		if (DEBUG) {
			SmartDashboard.putData(this);
		}
	}
	public void updateSD() {
		if (DEBUG) {
		}
	}
}