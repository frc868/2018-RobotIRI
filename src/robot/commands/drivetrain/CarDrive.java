package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import robot.subsystems.DriveTrain;

/**
 *
 */
public class CarDrive extends Command {
	
	DriveTrain dt = new DriveTrain();

    public CarDrive() {
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double gas = OI.getDRT();
    	double brake = OI.getDLT();
    	double turn = OI.getDLX();
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
