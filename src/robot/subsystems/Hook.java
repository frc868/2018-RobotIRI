package robot.subsystems;
import robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hook extends Subsystem {
	
	//parts of the hook
	private final Solenoid hook = new Solenoid(RobotMap.HOOK);
	
	public static final boolean DEBUG = false;
	
	//creates actual hook
	public Hook() {
	}
	
	public void setPosition(boolean up) {
		hook.set(up);
	}
	
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