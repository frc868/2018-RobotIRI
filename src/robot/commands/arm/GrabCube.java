package robot.commands.arm;


import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class GrabCube extends Command {
	
	private int countsThatCubeIsDetected = 0;
	private final int COUNTS_NEEDED = 5; 
	
	public GrabCube () {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.toggle();
    }

    protected void execute() {
    	if (Robot.intake.isCubeDetected()) {
    		countsThatCubeIsDetected++;
    	} else {
    		countsThatCubeIsDetected = 0; 
    	}
    }

    protected boolean isFinished() {
        return countsThatCubeIsDetected >= COUNTS_NEEDED;
    }

    protected void end() {
    	Robot.arm.close();
    }

    protected void interrupted() {
    	end();
    }

}
