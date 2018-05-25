package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class DriveTrain extends Subsystem {
	
	private TalonSRX trainRight = new WPI_TalonSRX(RobotMap.DRIVETRAINRIGHT);
	private TalonSRX trainLeft = new WPI_TalonSRX(RobotMap.DRIVETRAINLEFT);
	private Solenoid trans = new Solenoid(RobotMap.DRIVETRANS);
	private boolean autoTrans = true; //value for whether methods use raw input or automagically uses transmission and adjusted motor values
	private double shiftAt = 0.5; //value for changing when it shifts
	private double shiftTo = 0.5; //value for changing what value it shifts to (min motor speed at 2nd gear)
	
	//main method for setting motor speeds
	public void setSpeed(ControlMode mode, double right, double left){
		if(autoTrans){
			//automagically adjusts input values to actually motor values when shifting and does transmission
			if(right > shiftAt || right < -shiftAt || left > shiftAt || left < -shiftAt){
				trans.set(true);
				trainRight.set(mode, (((1 - shiftTo)/(1 - shiftAt)) * (right - 1)) + 1);
				trainLeft.set(mode, (((1 - shiftTo)/(1 - shiftAt)) * (left - 1)) + 1);
			}else{
				trans.set(false);
				trainRight.set(mode, (1 / shiftAt) * right);
				trainLeft.set(mode, (1 / shiftAt) * left);
			}
		}else{
			//uses raw input
			trainRight.set(mode, right);
			trainLeft.set(mode, left);
		}
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
	
	protected void initDefaultCommand() {
		
	}
}