package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.DriveTrain;

/**
 *
 */
public class AutoShiftA extends Command {
	
	private DriveTrain dt = new DriveTrain();
	private final double shiftAt = 10;
	private double shiftTo;

    public AutoShiftA() {
        requires(dt);
    }

    protected void initialize() {
    	dt.setTrans(false);
    	dt.setPOut(0, 0);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    }

    protected void interrupted() {
    }
}
