package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import robot.subsystems.DriveTrain;

public class ArcadeDrive extends Command {
	
	DriveTrain dt = new DriveTrain();
	public boolean finished = false;

    public ArcadeDrive() {
        requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dt.setTrans(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	dt.setPOut(OI.getDLY() - OI.getDRX(), OI.getDLY() + OI.getDRX());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	finished = true;
    }
}