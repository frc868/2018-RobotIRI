package robot.commands.tilt;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.Tilt;

public class SetTiltPosition extends Command {

	public enum TiltPosition 	{
		
		UP(Tilt.UP), MIDDLE(Tilt.MIDDLE), DOWN(Tilt.DOWN);

		public final double setpoint;
		TiltPosition(double setpoint) {
			this.setpoint = setpoint;
		}
	}

	private double setpoint;
	
	public SetTiltPosition(TiltPosition position)	{
		this(position.setpoint);
	}

	public SetTiltPosition(double setpoint) {
		// TODO Auto-generated constructor stub
		this.setpoint = setpoint;
		requires(Robot.tilt);
		System.out.println("tilt");
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("tilt executed");
    	double position = Robot.tilt.getPosition();
    	double power = 0;
    	double difference = setpoint - position;
    	
    	if (setpoint > Tilt.DOWN)	{ // need to go up?
    		if(difference > 25)	{
    			power = 1;
    		} else if (difference > 5)	{ //go up
    			power = 0.75;
    		} else if (difference < 5)	{ //go down
    			power = -0.2;
    		} else	{ // stay
    			power = 0.15;
    		}
    	} else	{ // need to go down? 
    		if (difference < 5) 	{ //go down (this stuff was too complicated for me, kinda copied)
				double proportion = (position - Tilt.DOWN) / ((Tilt.UP) - (Tilt.DOWN));
				power = -0.15 - (proportion * 1.25);    			
    		} else	{
    			power = 0;
    		}
    	}
    	
    	Robot.tilt.setTiltPower(power);
    }
    
    
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated constructor stub
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
