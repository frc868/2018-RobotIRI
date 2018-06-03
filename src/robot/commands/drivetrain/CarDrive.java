package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import robot.subsystems.DriveTrain;

public class CarDrive extends Command {
	
	private DriveTrain dt = new DriveTrain();
	public boolean finished = false;

    public CarDrive() {
        requires(dt);
    }

    protected void initialize() {
    	dt.setTrans(false);
    	dt.setPOut(0, 0);
    }

    protected void execute() {
    	double gas = OI.getDRT();
    	double brake = OI.getDLT();
    	double turn = OI.getDRX();
    	
    	double forward = gas - brake;
    	dt.setPOut(forward - turn, forward + turn);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    }

    protected void interrupted() {
    	finished = true;
    }
}
