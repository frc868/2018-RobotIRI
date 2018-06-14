package robot.commands.powerpack;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.subsystems.PowerPack;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadClimberControl extends Command {

	private final XboxController controller;
	private final int axis;

    public GamepadClimberControl(XboxController controller, int axis) {
    	requires(Robot.powerpack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {}

    protected void execute() {
    	double power = Math.pow(controller.getRawAxis(axis), 3);
    	
    	power = (power > 0) ? (power * PowerPack.PEAK_CLIMBER_FWD) : (-power * PowerPack.PEAK_CLIMBER_REV);
    	
    	Robot.powerpack.setClimberPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}