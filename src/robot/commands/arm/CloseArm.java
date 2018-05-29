package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class CloseArm extends Command {
	
	

	protected void initialize() {
		Robot.arm.close();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}

}
