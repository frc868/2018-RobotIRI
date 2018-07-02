package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class CloseArm extends Command {
	
	public CloseArm()	{
		requires(Robot.arm);
		
	}

	
	protected void initialize() {
		Robot.arm.close();
    }

    protected void execute() {}

    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}

}
