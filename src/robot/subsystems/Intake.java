package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem ;
import robot.HoundTalon;
import robot.RobotMap;
import robot.Utilities;
import robot.commands.intake.SetIntakePower;

public class Intake extends Subsystem{
	
	/**
	 * left and right intake motors 
	 */
	private HoundTalon intakeLeft;
	private HoundTalon intakeRight;
	
	public static final boolean DEBUG = false;
	
	/**
	 * constructor
	 */
	public Intake() {
		//creates talons
		intakeLeft = new HoundTalon(RobotMap.INTAKE_LEFT, "Intake", "Left (Breakout)");
		intakeRight = new HoundTalon(RobotMap.INTAKE_RIGHT, "Intake", "Right");
		config(intakeLeft);
		config(intakeRight);
		
		//sets intakes to inverted as default
		intakeLeft.setInverted(true);
		intakeRight.setInverted(true);
	}
	
	//connfigures the intake HoundTalons
	private void config(HoundTalon talon) {
		talon.configOpenloopRamp(0.1, HoundTalon.CONFIG_TIMEOUT);
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, HoundTalon.CONFIG_TIMEOUT);
		talon.overrideLimitSwitchesEnable(false);
		talon.setNeutralMode(NeutralMode.Brake);
	}
	
	/**
	 * 
	 * @param power the power the intake should be set to
	 */
	public void setPower(double power) {
		setPowerLeft(power);
		setPowerRight(power);
	}
	
	
	/**
	 * sets intake powers to 0%
	 */
	public void turnOff() {
		setPower(0);
	}
	
	/**
	 * 
	 * @param powerLeft the power the left intake should be set to
	 * @param powerRight the power the right intake should be set to 
	 */
	public void setPower(double powerLeft, double powerRight) {
		setPowerLeft(powerLeft);
		setPowerRight(powerRight);
	}
	
	/**
	 * 
	 * @param power the power the left intake should be set to
	 */
	public void setPowerLeft(double power) {
		intakeLeft.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	/**
	 * 
	 * @param power the power the right intake should be set to
	 */
	public void setPowerRight(double power) {
		intakeRight.set(ControlMode.PercentOutput, Utilities.constrain(power));
	}
	
	/**
	 * 
	 * @return left intake power
	 */
	public double getLeftPower() {
		return intakeLeft.get();
	}
	
	/**
	 * 
	 * @return right intake power
	 */
	public double getRightPower() {
		return intakeRight.get();
	}
	
	/**
	 * 
	 * @return true if the intake's limit switch is closed, meaning a cube is present in the intake
	 */
	public boolean isCubeDetected() {
		return intakeLeft.getSensorCollection().isFwdLimitSwitchClosed();
	}
	

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetIntakePower());
	}

}
