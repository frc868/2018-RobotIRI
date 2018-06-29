package robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;

import robot.Robot;

/**
 * ...drives an arc?
 */
public class DriveArc extends Command {
	private double rightTarget;
	private double leftTarget;
	private double rightInitial;
	private double leftInitial;
	private double rightPower;
	private double leftPower;
	
	public DriveArc(double in) {
		this(in, in, in > 0 ? 0.4 : -0.4, in > 0 ? 0.4 : -0.4);
	}

    public DriveArc(double ri, double li, double rp, double lp) {
        requires(Robot.drivetrain);
        this.rightTarget = ri;
        this.leftTarget  = li;
        this.rightPower  = rp;
        this.leftPower   = lp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.rightInitial = Robot.drivetrain.getScaledRightDistance();
    	this.leftInitial  = Robot.drivetrain.getScaledLeftDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double setRight = rightPower;
    	double setLeft  = leftPower;
    	
    	if (isRightFinished()) {
    		setRight = 0;
    	}
    	if (isLeftFinished()) {
    		setLeft = 0;
    	}
    	
    	Robot.drivetrain.setPOut(setRight, setLeft);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isRightFinished() && isLeftFinished();
    }
    
    private boolean isRightFinished() {
    	return Math.abs(Robot.drivetrain.getScaledRightDistance() - rightTarget) > Math.abs(rightTarget); 
    }
    
    private boolean isLeftFinished() {
    	return Math.abs(Robot.drivetrain.getScaledLeftDistance() - leftTarget) > Math.abs(leftTarget);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setPOut(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
