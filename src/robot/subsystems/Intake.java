package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem ;
import robot.RobotMap;
import robot.Utilities;

public class Intake extends Subsystem{
	
	private WPI_TalonSRX intakeLeft;
	private WPI_TalonSRX intakeRight;
	
	public static final boolean DEBUG = false;
	
	public Intake() {
		intakeLeft = Utilities.getTalon(RobotMap.INTAKE_LEFT, "Intake", "Left (Breakout)");
		intakeRight = Utilities.getTalon(RobotMap.INTAKE_RIGHT, "Intake", "Right");
		config(intakeLeft);
		config(intakeRight);
		
		intakeLeft.setInverted(true);
		intakeRight.setInverted(true);
	}
	
	private void config(WPI_TalonSRX talon) {
		talon.configOpenloopRamp(0.1, Utilities.CONFIG_TIMEOUT);
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, Utilities.CONFIG_TIMEOUT);
		talon.overrideLimitSwitchesEnable(false);
		talon.setNeutralMode(NeutralMode.Brake);
	}
	
	public void setPower(double power) {
		setPowerLeft(power);
		setPowerRight(power);
	}
	
	public void setPower(double powerLeft, double powerRight) {
		setPowerLeft(powerLeft);
		setPowerRight(powerRight);
	}
	
	public void setPowerLeft(double power) {
		intakeLeft.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	public void setPowerRight(double power) {
		intakeRight.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	public double getLeftPower() {
		return intakeLeft.get();
	}
	
	public double getRightPower() {
		return intakeRight.get();
	}
	
	public boolean isCubeDetected() {
		return intakeLeft.getSensorCollection().isFwdLimitSwitchClosed();
	}
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}