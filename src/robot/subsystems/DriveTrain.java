package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class DriveTrain extends Subsystem {
	
	private TalonSRX trainRight;
	private TalonSRX trainRight2;
	private TalonSRX trainLeft;
	private TalonSRX trainLeft2;
	private Solenoid trans;
	private boolean autoTrans = false; //value for whether methods use raw input or automagically uses transmission and adjusted motor values
	private double shiftAt = 0.5; //value for changing when it shifts
	private double shiftTo = 0.5; //value for changing what value it shifts to (min motor speed at 2nd gear)
	
	public DriveTrain(){
		trainRight = new WPI_TalonSRX(RobotMap.DRIVETRAINRIGHT);
		trainRight2 = new WPI_TalonSRX(RobotMap.DRIVETRAINRIGHT2);
		trainLeft = new WPI_TalonSRX(RobotMap.DRIVETRAINLEFT);
		trainLeft2 = new WPI_TalonSRX(RobotMap.DRIVETRAINLEFT2);
		
		trainRight2.follow(trainRight);
		trainLeft2.follow(trainLeft);
		
		trainRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		trainLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		trainRight.config_kP(0, 0, 0);
		trainLeft.config_kP(0, 0, 0);
		
		trainRight.config_kI(0, 0, 0);
		trainLeft.config_kI(0, 0, 0);
		
		trainRight.config_kD(0, 0, 0);
		trainLeft.config_kD(0, 0, 0);
	}
	
	//main method for setting motor speeds
	public void setPOut(double right, double left){
		trainRight.set(ControlMode.PercentOutput, checkPOut(right));
		trainLeft.set(ControlMode.PercentOutput, checkPOut(left));
	}
	
	public void setTrans(boolean trans){
		this.trans.set(trans);
	}
	
	public int getEncoderLeft(){
		return trainLeft.getSensorCollection().getQuadraturePosition();
	}
	
	public int getEncoderRight(){
		return trainRight.getSensorCollection().getQuadraturePosition();
	}
	
	public double getEncoderLSpeed(){
		return trainLeft.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getEncoderRSpeed(){
		return trainRight.getSensorCollection().getQuadratureVelocity();
	}
	
	public void setAutoTrans(boolean autoTrans){
		this.autoTrans = autoTrans;
	}
	
	public double checkPOut(double in){
		in = in >= 1 ? in : 1;
		in = in <= -1 ? in : -1;
		return in;
	}
	
	protected void initDefaultCommand() {
		
	}
}
