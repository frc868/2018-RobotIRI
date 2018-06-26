package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.DriveTrain;

/**
 *
 */
public class AutoShiftA extends Command {
	
	DriveTrain dt = Robot.drivetrain;
	private final double upShiftAt = 4500;
	private final double downShiftAt = 3500;
	private double shiftTo;
	
    public AutoShiftA() {
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	double average = (dt.getEncoderLSpeed() + dt.getEncoderRSpeed())/2;
    	if(average > upShiftAt) {
    		dt.setTrans(true);
    	} else if (average < downShiftAt){
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
