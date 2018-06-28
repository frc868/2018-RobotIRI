package robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class DriveStraightRampPower extends DriveStraight {
	private final double startPower, endPower;

    public DriveStraightRampPower(double distance, double startPower, double endPower) {
        super(distance, startPower);
        this.startPower = startPower;
        this.endPower = endPower;
    }

    
    public DriveStraightRampPower(double distance, double startPower, double endPower, double targetAngle) {
        super(distance, startPower, targetAngle);
        this.startPower = startPower;
        this.endPower = endPower;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double powerMultiplier = (Robot.drivetrain.getScaledAverageDistance() - initialDistance) / targetDistance;
    	targetPower = startPower + (endPower-startPower)*powerMultiplier;
    	
    	super.execute();
    }
}
