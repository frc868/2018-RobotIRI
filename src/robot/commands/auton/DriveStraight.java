package robot.commands.auton;

import robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	
	protected double targetPower;
	protected double initialDistance; 
	protected final double targetDistance;
	protected double targetAngle;
	private boolean needAngle;

    public DriveStraight(double targetDistance, double targetPower) {
		requires(Robot.drivetrain); 
		this.targetDistance = targetDistance * -1;
		this.targetPower = targetPower * -1;
		needAngle = true; 
		System.out.println("Drive Constructed");
    }
    
    public DriveStraight(double targetDistance, double targetPower, double targetAngle) {
    	requires(Robot.drivetrain); 
    	this.targetDistance = targetDistance * -1;
    	this.targetPower = targetPower * -1;
    	this.targetAngle = targetAngle;
    	
    	
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (needAngle) {
    		targetAngle = Robot.gyro.getAngle();
    	}
    	initialDistance = Robot.drivetrain.getScaledAverageDistance();
		System.out.println("Drive Initialized");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		System.out.println("Drive Executing");
    	double powerLeft = targetPower;
    	double powerRight = targetPower;
    	
    	double angleError = Robot.gyro.getAngle() - targetAngle;
    	double angleP = (angleError / 50);
    	
    	if (targetPower < 0) {
    		angleP *= -1;
    	}
    	
    	System.out.println("running, " + targetPower);
    	
    	Robot.drivetrain.setPOut(powerRight * (1-angleP), powerLeft * (1+angleP));
    	    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		System.out.println("Drive Evaluating Is Finished");
    	return Math.abs(Robot.drivetrain.getScaledAverageDistance() - initialDistance) >= Math.abs(targetDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.turnOff();
		System.out.println("Drive Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		System.out.println("Drive Interupted");
    	end();
    }
}
