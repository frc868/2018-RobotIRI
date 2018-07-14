package robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HoundTalon extends WPI_TalonSRX {
	
	public static final int CONFIG_TIMEOUT = 10;

	public HoundTalon(int deviceNumber, String subsystem, String name) {
		super(deviceNumber);
		setName(subsystem, name);
		config();
	}

	public TalonSRX getTalon() {
		return (WPI_TalonSRX) this;
	}
	
	public void config() {
		// OUTPUTS
		setInverted(false);
		setNeutralMode(NeutralMode.Coast);
		configPeakOutputForward(1, CONFIG_TIMEOUT);
		configPeakOutputReverse(-1, CONFIG_TIMEOUT);
		configNeutralDeadband(0.05, CONFIG_TIMEOUT);
		configNominalOutputForward(0, CONFIG_TIMEOUT);
		configNominalOutputReverse(0, CONFIG_TIMEOUT);
		
		// POWER CONTROL
		enableCurrentLimit(false);
		configContinuousCurrentLimit(0, CONFIG_TIMEOUT);
		configPeakCurrentDuration(0, CONFIG_TIMEOUT);
		configPeakCurrentLimit(0, CONFIG_TIMEOUT);
		configVoltageCompSaturation(12, CONFIG_TIMEOUT);
		enableVoltageCompensation(true);
		configOpenloopRamp(0, CONFIG_TIMEOUT);
//		talon.configVoltageMeasurementFilter(5, CONFIG_TIMEOUT);

		// WPIlib FEATURES
//		talon.setExpiration(CONFIG_TIMEOUT);
		setSafetyEnabled(false);
		
		// SENSORS
		configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, 
											 LimitSwitchNormal.Disabled, CONFIG_TIMEOUT);
		configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, 
											 LimitSwitchNormal.Disabled, CONFIG_TIMEOUT);
//		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, CONFIG_TIMEOUT);
//		talon.configVelocityMeasurementWindow(5, CONFIG_TIMEOUT);
		configForwardSoftLimitEnable(false, CONFIG_TIMEOUT);
		configForwardSoftLimitThreshold(0, CONFIG_TIMEOUT);
//		talon.configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal, timeoutMs)
		configReverseSoftLimitEnable(false, CONFIG_TIMEOUT);
		configReverseSoftLimitThreshold(0, CONFIG_TIMEOUT);
		configSelectedFeedbackSensor(FeedbackDevice.None, 0, CONFIG_TIMEOUT);
//		talon.configSensorTerm(sensorTerm, feedbackDevice, timeoutMs)
//		talon.overrideLimitSwitchesEnable(false);
//		talon.overrideSoftLimitsEnable(false);
		
		// MOTION PROFILING & MAGIC
		changeMotionControlFramePeriod(5);
//		talon.configMotionAcceleration(1, CONFIG_TIMEOUT);
//		talon.configMotionCruiseVelocity(1, CONFIG_TIMEOUT);
		configMotionProfileTrajectoryPeriod(5, CONFIG_TIMEOUT);
		
		// PID CONTROLS
		config_kP(0, 0, CONFIG_TIMEOUT);
		config_kI(0, 0, CONFIG_TIMEOUT);
		config_kD(0, 0, CONFIG_TIMEOUT);
		config_kF(0, 0, CONFIG_TIMEOUT);
//		talon.config_IntegralZone(0, 0, CONFIG_TIMEOUT);
		configAllowableClosedloopError(0, 10, CONFIG_TIMEOUT);
		;configClosedloopRamp(0, CONFIG_TIMEOUT);
//		talon.configMaxIntegralAccumulator(0, 0, CONFIG_TIMEOUT);
//		talon.setIntegralAccumulator(0, 0, CONFIG_TIMEOUT);
	}

}
