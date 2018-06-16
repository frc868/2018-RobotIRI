package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class OpenArm extends Command {
	

	public OpenArm()	{
		requires(Robot.arm);
		
	}

	
	
	protected void initialize() {
		Robot.arm.open();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}

}
