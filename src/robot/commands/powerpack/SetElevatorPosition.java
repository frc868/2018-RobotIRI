package robot.commands.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class SetElevatorPosition extends Command {

    private double position;
    
    public static final double COLLECT = -25000;

	public static final double SWITCH = 300000;

	public static final double SCALE = 700000;
    

	public SetElevatorPosition(double position) {
        requires(Robot.powerpack);
        this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.powerpack.setElevatorPosition(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
