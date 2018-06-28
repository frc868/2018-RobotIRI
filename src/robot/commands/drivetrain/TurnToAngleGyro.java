package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.DriveTrain;
import robot.subsystems.Gyro;

/**
 *
 */
public class TurnToAngleGyro extends Command {

	private double angle;
	private double timeout;
	private DriveTrain drivetrain;
	private PIDController pidRight;
	private PIDController pidLeft;
	private Timer timer;
	private double startTime;
	
    public TurnToAngleGyro(double angle, double timeout) {
    	drivetrain = Robot.drivetrain;
        requires(drivetrain);
        this.angle = angle;
        this.timeout = timeout;
        pidRight = drivetrain.getPIDRight();
        pidLeft = drivetrain.getPIDLeft();
        timer = new Timer();
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	startTime = timer.getFPGATimestamp();
    	pidLeft.setSetpoint(angle);
    	pidRight.setSetpoint(angle);
    	pidRight.enable();
    	pidLeft.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((pidRight.isEnabled() && pidLeft.isEnabled()) || timer.getFPGATimestamp() - startTime <= timeout) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	pidRight.disable();
    	pidLeft.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pidRight.disable();
    	pidLeft.disable();
    }
}
