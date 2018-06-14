package robot.commands.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadElevatorControl extends Command {
	
	private final XboxController controller;
	private final int axis;

    public GamepadElevatorControl(XboxController controller, int axis) {
    	requires(Robot.powerpack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {}

    protected void execute() {
    	double power = -Math.pow(controller.getRawAxis(axis), 3);
    	
    	power = (power > 0) ? (power * 0.45) : (power * 0.25);
   
    	Robot.powerpack.setElevatorPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}