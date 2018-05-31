package robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;
import robot.Utilities;

/**
 *
 */
public class PowerPack extends Subsystem {


	//Basic components of the PowerPack
	private Solenoid climberEngage;
	private Solenoid brakeDisengage;

	private WPI_TalonSRX winchPrimary;
	private WPI_TalonSRX winchSecondary;
	private WPI_TalonSRX winchTertiary;
	private WPI_TalonSRX winchQuaternary;
	
	
	//Initializing all of the basic components of the PowerPack
	public PowerPack() {
		
		climberEngage = new Solenoid(RobotMap.WINCH_TRANSMISSION);
		brakeDisengage = new Solenoid(RobotMap.WINCH_BRAKE);

		winchPrimary = Utilities.getTalon(RobotMap.POWER_PACK_PRIMARY, "Power Pack", "Primary");
		winchSecondary = Utilities.getTalon(RobotMap.POWER_PACK_SECONDARY, "Power Pack", "Secondary");
		winchTertiary = Utilities.getTalon(RobotMap.POWER_PACK_TERTIARY, "Power Pack", "Tertiary");
		winchQuaternary = Utilities.getTalon(RobotMap.POWER_PACK_QUATERNARY, "Power Pack", "Quaternary");

		winchSecondary.follow(winchPrimary);
		winchTertiary.follow(winchPrimary);
		winchQuaternary.follow(winchPrimary);
		
		winchSecondary.setInverted(true);
		winchTertiary.setInverted(true);
		winchQuaternary.setInverted(true);
	}
	
    
    //Setters
    
	//Getters
	
	public boolean isBottomSwitchTripped() {
		return winchPrimary.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	public boolean isTopSwitchTripped() {
		return winchPrimary.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public int getWinchPosition() {
		return winchPrimary.getSelectedSensorPosition(0);
	}
	
	public int getWinchVelocity() {
		return winchPrimary.getSelectedSensorVelocity(0);
	}
	
	public boolean isClimberEngaged() {
		return climberEngage.get();
	}
	
	public boolean isElevatorEngaged() {
		return !isClimberEngaged();
	}

	public boolean isBrakeEngaged() {
		return !brakeDisengage.get();
	}


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}

