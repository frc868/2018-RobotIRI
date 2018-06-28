package robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class DriveStraightUntilCubeDetected extends DriveStraight {
	
	private double countsCubeIsDetected = 0;
	private final double COUNTS_NEEDED = 5;

    public DriveStraightUntilCubeDetected(double targetDistance, double targetPower) {
       super(targetDistance, targetPower);
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	super.execute();
    	
    	
    	if (!Robot.intake.isCubeDetected()) {
    		countsCubeIsDetected = 0;
    	} else {
    		countsCubeIsDetected++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished() || countsCubeIsDetected > COUNTS_NEEDED;
    }  
}
