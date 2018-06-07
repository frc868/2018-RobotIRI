package robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
	
	public static final int PID_TOLERANCE = 5000;
	public static final double PEAK_ELEVATOR_FWD = 1.0;
	public static final double PEAK_ELEVATOR_REV = -0.45;
	public static final double MIN_ELEVATOR_FWD = 0.2;
	public static final double MIN_ELEVATOR_REV = -0.15;
	public static final double ELEVATOR_RAMP = 0.25;
	
	public static final double PEAK_CLIMBER_FWD = 1.0;
	public static final double PEAK_CLIMBER_REV = -1.0;
	public static final boolean DEBUG = false;
	
	public static boolean overrideLimits = true;
	
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
		
		winchPrimary.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Utilities.CONFIG_TIMEOUT);		
		winchPrimary.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Utilities.CONFIG_TIMEOUT);
		winchPrimary.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Utilities.CONFIG_TIMEOUT);
		winchPrimary.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, Utilities.CONFIG_TIMEOUT);
	}
	
	private void configure(WPI_TalonSRX talon) {		
		talon.setNeutralMode(NeutralMode.Brake);
		
		talon.configPeakCurrentLimit(60, Utilities.CONFIG_TIMEOUT);
		talon.configPeakCurrentDuration(250, Utilities.CONFIG_TIMEOUT);
		talon.configContinuousCurrentLimit(40, Utilities.CONFIG_TIMEOUT);
		talon.enableCurrentLimit(true);
		
		talon.configPeakOutputForward(PEAK_ELEVATOR_FWD, Utilities.CONFIG_TIMEOUT);
		talon.configPeakOutputReverse(PEAK_ELEVATOR_REV, Utilities.CONFIG_TIMEOUT);
		talon.configNominalOutputForward(MIN_ELEVATOR_FWD, Utilities.CONFIG_TIMEOUT);
		talon.configNominalOutputReverse(MIN_ELEVATOR_REV, Utilities.CONFIG_TIMEOUT);

		// TODO use constants for these
		talon.config_kP(0, 0.01/2.5, Utilities.CONFIG_TIMEOUT);
		talon.config_kI(0, 0, Utilities.CONFIG_TIMEOUT);
		talon.config_kD(0, 0.25, Utilities.CONFIG_TIMEOUT);
		talon.config_kF(0, 0, Utilities.CONFIG_TIMEOUT);
		talon.configAllowableClosedloopError(0, PID_TOLERANCE, Utilities.CONFIG_TIMEOUT);
		talon.configClosedloopRamp(ELEVATOR_RAMP, Utilities.CONFIG_TIMEOUT);
	}
    
    //Setters
	
	public void setElevatorPower(double power) {
		setElevator();
		setPower(power);
	}
	
	public void setElevatorPosition(double position) {
		setElevator();
		setPosition(position);
	}
	
	public void setClimberPower(double power) {
		setClimber();
		setPower(power);
	}
	
	public void setClimberPosition(double position) {
		setClimber();
		setPosition(position);
	}
	
	public void setBrake() {
		winchPrimary.set(ControlMode.Disabled, 0);
		brakeDisengage.set(false);
	}
	
	public void setOverrideLimits(boolean override) {
		overrideLimits = override;
		winchPrimary.overrideLimitSwitchesEnable(overrideLimits);
	}
	
	private void setElevator() {
		brakeDisengage.set(true);
		climberEngage.set(false);
		winchPrimary.overrideLimitSwitchesEnable(overrideLimits);
		winchPrimary.configPeakOutputForward(PEAK_ELEVATOR_FWD, 0);
		winchPrimary.configPeakOutputReverse(PEAK_ELEVATOR_REV, 0);
	}
	
	private void setClimber() {
		brakeDisengage.set(true);
		climberEngage.set(true);
		winchPrimary.overrideLimitSwitchesEnable(false);
		winchPrimary.configPeakOutputForward(PEAK_CLIMBER_FWD, 0);
		winchPrimary.configPeakOutputReverse(PEAK_CLIMBER_REV, 0);
	}
	
	private void setPower(double power) {
		winchPrimary.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	private void setPosition(double position) {
		winchPrimary.set(ControlMode.Position, position);
	}
	
	
    
	//Getters
	
	public boolean isBottomSwitchTripped() {
		return winchPrimary.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	public boolean isTopSwitchTripped() {
		return winchPrimary.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public int getWinchPosition(){
		return winchPrimary.getSelectedSensorPosition(0);
	}

	public double getWinchVelocity(){
		return winchPrimary.getSelectedSensorVelocity(0);
	}

	/**
	 * @return whether the climber is enabled
	 */
	public boolean isClimberEngaged() {
		return climberEngage.get();
	}
	
	public boolean isElevatorEngaged() {
		return !isClimberEngaged();
	}

	public boolean isBrakeEngaged() {
		return !brakeDisengage.get();
	}

	public boolean onTarget() {
		return Math.abs(winchPrimary.getSelectedSensorPosition(0) - winchPrimary.getClosedLoopTarget(0)) < PID_TOLERANCE;
	}
	
	public boolean getOverrideLimits() {
		return overrideLimits;
	}

	public void initDefaultCommand() {
	
	}

}

