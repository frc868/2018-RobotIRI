package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class ToggleArm extends Command {
	
	

	protected void initialize() {
		Robot.arm.toggle();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}

}
