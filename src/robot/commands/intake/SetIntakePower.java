package robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class SetIntakePower extends Command {

	private double power;
    public SetIntakePower(double power) {
    	this.power = power;
        requires(Robot.intake);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.turnOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
