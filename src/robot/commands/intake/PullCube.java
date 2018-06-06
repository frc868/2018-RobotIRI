package robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class PullCube extends Command {
	
	private final int COUNTS_NEEDED = 5;
	private int counts = 0;
	private final int INTAKE_POWER = 1;

    public PullCube() {
       requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setPower(INTAKE_POWER);
    	if (Robot.intake.isCubeDetected()) {
    		counts++;
    	} else {
    		counts = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counts >= COUNTS_NEEDED;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
