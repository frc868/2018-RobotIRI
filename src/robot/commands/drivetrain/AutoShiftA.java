package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.DriveTrain;

/**
 *
 */
public class AutoShiftA extends Command {
	
	DriveTrain dt = Robot.drivetrain;
	private final double shiftAt = 4000;
	private double shiftTo;

    public AutoShiftA() {
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if((dt.getEncoderLSpeed() + dt.getEncoderRSpeed())/2 > shiftAt) {
    		dt.setTrans(true);
    	} else {
    		dt.setTrans(false);
    	}
    }

    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    }

    protected void interrupted() {
    }
}
