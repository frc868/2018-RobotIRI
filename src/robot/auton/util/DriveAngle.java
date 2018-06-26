package robot.auton.util;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class DriveAngle extends Command {
	
	private final double TARGET_ANGLE; 
	private final double POWER_RIGHT;
	private final double POWER_LEFT;
	
	private double INITIAL_ANGLE;
	
	private final double MAX_ERROR_FOR_SHUTOFF = 10; 

	
    public DriveAngle(double targetAngle, double powerRight, double powerLeft) {
       this.TARGET_ANGLE = targetAngle;
       this.POWER_RIGHT = powerRight;
       this.POWER_LEFT = powerLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	INITIAL_ANGLE = Robot.gyro.getAngle();	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setPOut(POWER_RIGHT, POWER_LEFT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(getChange()) < MAX_ERROR_FOR_SHUTOFF;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.turnOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

    private double getError() {
    	return TARGET_ANGLE - getChange();
    }
   
    private double getChange() { 
    	return Robot.gyro.getAngle() - INITIAL_ANGLE;
    }
}
