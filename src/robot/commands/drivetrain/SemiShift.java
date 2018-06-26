package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import robot.Robot;
import robot.subsystems.DriveTrain;

public class SemiShift extends Command {
	
	DriveTrain dt = Robot.drivetrain;
	private final double minShift = 0.5;
	public boolean finished = false;
	
    public SemiShift() {
        requires(dt);
    }

    protected void initialize() {
    	dt.setTrans(false);
    	dt.setPOut(0, 0);
    }

    protected void execute() {
    	
    	double forward = OI.getDLY();
    	double turn = OI.getDRX();
    	double trigger = OI.getDRT();
    	
    	if(trigger > 0 && Math.abs(forward) >= 0.95){
    		dt.setTrans(true);
    		forward = forward > 0 ? ((1 - minShift) * (trigger + 1)) - 1 : ((1 - minShift) * (trigger - 1)) + 1;
    	}else{
    		dt.setTrans(false);
    	}
    	
    	dt.setPOut(forward - turn, forward + turn);
    	
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    }

    protected void interrupted() {
    	finished = true;
    }
}
