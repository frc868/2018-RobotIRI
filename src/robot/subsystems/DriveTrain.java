package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.OI;
import robot.RobotMap;
import robot.commands.drivetrain.ArcadeDrive;

public class DriveTrain extends Subsystem {
	
	private TalonSRX trainRight;
	private TalonSRX trainRight2;
	private TalonSRX trainLeft;
	private TalonSRX trainLeft2;
	private Solenoid trans;
	
	public static final double MAX_DRIVE_SPEED = 0.75;
	public static final double MIN_DRIVE_SPEED = 0.3; // TODO: set deadband?
	public static final double COUNTS_PER_INCH = 422;
	
	
	public DriveTrain(){
		trainRight = new WPI_TalonSRX(RobotMap.DRIVETRAINRIGHT);
		trainRight2 = new WPI_TalonSRX(RobotMap.DRIVETRAINRIGHT2);
		trainLeft = new WPI_TalonSRX(RobotMap.DRIVETRAINLEFT);
		trainLeft2 = new WPI_TalonSRX(RobotMap.DRIVETRAINLEFT2);
		trans = new Solenoid(RobotMap.DRIVETRANS);
		
		trainRight2.follow(trainRight);
		trainLeft2.follow(trainLeft);
		
		trainRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		trainLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		trainRight.config_kP(0, 0, 10);
		trainLeft.config_kP(0, 0, 10);
		
		trainRight.config_kI(0, 0, 10);
		trainLeft.config_kI(0, 0, 10);
		
		trainRight.config_kD(0, 0, 10);
		trainLeft.config_kD(0, 0, 10);
		
		trainLeft.setInverted(true);
		trainLeft2.setInverted(true);
		
	}
	
	//main method for setting percent motor speeds
	public void setPOut(double right, double left){
		right = checkPOut(right);
		left = checkPOut(left);
		if(OI.driver.getBumper(Hand.kRight)){
			right = right * 0.25;
			left = left * 0.25;
		}
		trainRight.set(ControlMode.PercentOutput, checkPOut(right));
		trainLeft.set(ControlMode.PercentOutput, checkPOut(left));
	}
	
	//main method for setting output by any control
	public void setOut(ControlMode control, double right, double left){
		trainRight.set(control, right);
		trainLeft.set(control, left);
	}
	
	public void setTrans(boolean state){
		if(!trans.get() == state) {
			trans.set(state);
		} 
	}
	
	public void toggleTrans(){
		trans.set(!trans.get());
	}
	
	public int getEncoderLeft(){
		return trainLeft.getSensorCollection().getQuadraturePosition();
	}
	
	public int getEncoderRight(){
		return trainRight.getSensorCollection().getQuadraturePosition();
	}
	
	public double getEncoderLSpeed(){
		return trainLeft.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderRSpeed(){
		return trainRight.getSelectedSensorVelocity(0);
	}
	
	public double getRawLeftDistance() {
		return trainLeft.getSelectedSensorPosition(0);
	}

	public double getRawLeftVelocity() {
		return trainLeft.getSelectedSensorVelocity(0);
	}

	public double getRawRightDistance() {
		return trainRight.getSelectedSensorPosition(0);
	}

	public double getRawRightVelocity() {
		return trainRight.getSelectedSensorVelocity(0);
	}

	public double getRawAverageDistance() {
		return (getRawRightDistance() + getRawLeftDistance()) / 2;
	}

	public double getScaledAverageDistance() {
		return getRawAverageDistance() / COUNTS_PER_INCH;
	}
	
	public double getScaledRightDistance() {
		return getRawRightDistance() / COUNTS_PER_INCH;
	}
	
	public double getScaledLeftDistance() {
		return getRawLeftDistance() / COUNTS_PER_INCH;
	}
	
	//checks range of input for motors using percent output
	public double checkPOut(double in){
		if(in > 1) {
			in = 1;
		} else if (in < -1) {
			in = -1;
		}
		return in;
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	public void turnOff() {
		setPOut(0,0);
	}
}