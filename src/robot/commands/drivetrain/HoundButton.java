package robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class HoundButton extends JoystickButton {

	private HoundButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		// TODO Auto-generated constructor stub
	}
	
	public static HoundButton create (GenericHID joystick, int buttonNumber) {
		return new HoundButton(joystick, buttonNumber);
	}
	

	public HoundButton pressed(Command command) {
		super.whenPressed(command);
		
		return this;
	}
	
	public HoundButton held(Command command) {
		super.whileHeld(command);
		
		return this;
	}
	
	public HoundButton released(Command command) {
		super.whenReleased(command);
		
		return this;
	}

}
