package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class OpenArm extends Command {
	
	

	protected void initialize() {
		Robot.arm.open();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}

}
