package robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.HoundTalon;
import robot.OI;
import robot.Robot;
import robot.RobotMap;
import robot.commands.drivetrain.ArcadeDrive;

public class DriveTrain extends Subsystem {
	
	//parts of the drivetrain
	private HoundTalon trainRight;
	private HoundTalon trainRight2;
	private HoundTalon trainLeft;
	private HoundTalon trainLeft2;
	private Solenoid trans;
	private PIDController pidRight;
	private PIDController pidLeft;
	
	private MotionProfileStatus leftStatus;
	private MotionProfileStatus rightStatus;

	
	//limitations of the drivetrain motor + other constants
	public static final double MAX_DRIVE_SPEED = 0.75;
	public static final double MIN_DRIVE_SPEED = 0.3; // TODO: set deadband?
	public static final double COUNTS_PER_INCH = 422;
	
	
	//creating the actual drivetrain and setting up pid values for the houndtalons 
	// and setting up pid controllers for turning the bot
	public DriveTrain(){
		leftStatus = new MotionProfileStatus();
		rightStatus = new MotionProfileStatus();

		trainRight = new HoundTalon(RobotMap.DRIVETRAINRIGHT, "Drivetrain", "trainRight");
		trainRight2 = new HoundTalon(RobotMap.DRIVETRAINRIGHT2, "Drivetrain", "trainRight2");
		trainLeft = new HoundTalon(RobotMap.DRIVETRAINLEFT, "Drivetrain", "trainLeft");
		trainLeft2 = new HoundTalon(RobotMap.DRIVETRAINLEFT2, "Drivetrain", "trainLeft2");
		trans = new Solenoid(RobotMap.DRIVETRANS);
		
		trainRight2.follow(trainRight);
		trainLeft2.follow(trainLeft);
		
		trainRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		trainLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		trainRight.config_kP(0, .1, 10);
		trainLeft.config_kP(0, .1, 10);
		
		trainRight.config_kI(0, 0, 10);
		trainLeft.config_kI(0, 0, 10);
		
		trainRight.config_kD(0, 0, 10);
		trainLeft.config_kD(0, 0, 10);
		
		trainLeft.setInverted(true);
		trainLeft2.setInverted(true);
		
		pidRight = new PIDController(.1, 0, 0, Robot.gyro.getGyro(), trainRight);
		pidLeft = new PIDController(.1, 0, 0, Robot.gyro.getGyro(), trainLeft);
		
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
	
	public void setPosition(double position) {
		trainRight.set(ControlMode.Position, position);
		trainLeft.set(ControlMode.Position, position);
	}
	
	public void toggleTrans(){
		trans.set(!trans.get());
	}
	
	public double getEncoderLeftDistance(){
		return trainLeft.getSensorCollection().getQuadraturePosition() /COUNTS_PER_INCH;
	}
	
	public double getEncoderRightDistance(){
		return trainRight.getSensorCollection().getQuadraturePosition() /COUNTS_PER_INCH;
	}
	
	public double getEncoderLSpeed(){
		return trainLeft.getSelectedSensorVelocity(0)/COUNTS_PER_INCH;
	}
	
	public double getEncoderRSpeed(){
		return trainRight.getSelectedSensorVelocity(0)/COUNTS_PER_INCH;
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
	
	public PIDController getPIDRight() {
		return pidRight;
	}
	
	public PIDController getPIDLeft() {
		return pidLeft;
	}
	
	public HoundTalon getTalonLeft() {
		return trainLeft;
	}
	
	public HoundTalon getTalonRight() {
		return trainRight;
	}
	
	public MotionProfileStatus getLeftStatus() {
		trainLeft.getTalon().getMotionProfileStatus(leftStatus);
		return leftStatus;
	}
	
	public MotionProfileStatus getRightStatus() {
		trainRight.getTalon().getMotionProfileStatus(rightStatus);
		return rightStatus;
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