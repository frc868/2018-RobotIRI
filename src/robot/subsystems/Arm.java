package robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

/**
 * Represents the pneumatic arms that squeeze the box.
 */
public class Arm extends Subsystem {
	
	private Solenoid arm;
	
	public Arm () {
		arm = new Solenoid(RobotMap.ARM);
	}
	
	/**
	 * set the state to open, or true
	 */
	public void open () {
		arm.set(true);
	}
	
	/**
	 * set the state to closed, or false
	 */
	public void close () {
		arm.set(false);
	}
	
	/**
	 * 
	 * @param state the state that the arm should be set to
	 */
	public void setState(boolean state) {
		arm.set(state);
	}
	
	/** 
	 * switch to the other state
	 */
	public void toggle() {
		arm.set(!getState());
	}

	/**
	 * 
	 * @return the state of the arm (true = open, false = closed)
	 */
	public boolean getState() {
		return arm.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
