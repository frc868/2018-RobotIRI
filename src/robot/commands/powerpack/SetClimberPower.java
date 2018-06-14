package robot.commands.powerpack;

import edu.wpi.first.wpilibj.command.Command;

import robot.Robot;
/**
 *
 */
public class SetClimberPower extends Command {

    private double power;

	public SetClimberPower(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.powerpack);
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.powerpack.setClimberPower(power);
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
