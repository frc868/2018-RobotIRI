package robot.auton;

import robot.auton.FieldState.Position;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the main entry point for our match autonomous mode.
 * This command should be constructed & started at the start of auton -
 * constructing it too soon means it won't be able to read from the SmartDashboard
 * to start the correct auton mode.
 */
public class AutonLauncher {

	/*enum Objective {
		SCALE,
		SWITCH,
		BASELINE,
		SCALE_SIDE
	}

	public static final SendableChooser<Objective> firstPriority = new SendableChooser<>();
	public static final SendableChooser<Boolean> enableCross = new SendableChooser<>();

	public static void addChoices() {
		firstPriority.addDefault("Baseline", Objective.BASELINE);
		firstPriority.addObject("Switch", Objective.SWITCH);
		firstPriority.addObject("Scale (Double)", Objective.SCALE);
		firstPriority.addObject("Scale (Single/Assist)", Objective.SCALE_SIDE);
		SmartDashboard.putData("First Cube Priority", firstPriority);

		enableCross.addDefault("Enable Cross", new Boolean(true));
		enableCross.addObject("Disable Cross", new Boolean(false));
		SmartDashboard.putData("Enable Midfield Crossing", enableCross);
	}

	public static Command getAuton(FieldState field) {
		switch(firstPriority.getSelected()) {
		case BASELINE:
			return getBaseline();
		case SWITCH:
			return getSwitch(field);
		case SCALE:
			if (enableCross.getSelected()) {
				return getAnyScale(field);
			} else {
				return getAheadScale(field);
			}
		case SCALE_SIDE:
			if (enableCross.getSelected()) {
				return getAssistScale(field);
			} else {
				return getSideScale(field);
			}
		default:
			return getBaseline();
		}
	}

	public static Command getBaseline() {
		return new Baseline();
	}
	
	public static Command getSideScale(FieldState field) {
		if (field.getRobotStartPos() == Position.Right && field.getScalePos() == Position.Right) {
			return new RightScaleSide();
		} else if (field.getRobotStartPos() == Position.Left && field.getScalePos() == Position.Left) {
			return new LeftScaleSide();
		} else {
			return getSwitch(field);
		}
	}
	
	public static Command getAssistScale(FieldState field) {
		if (field.getRobotStartPos() == Position.Right) {
			if (field.getScalePos() == Position.Right) {
				return new RightScaleSide();
			} else {
				return new LeftCross();
			}
		} else if (field.getRobotStartPos() == Position.Left) {
			if (field.getScalePos() == Position.Left) {
				return new LeftScaleSide();
			} else {
				return new RightCross();
			}
		} else {
			return getBaseline();
		}
	}

	public static Command getSwitch(FieldState field) {
		if (field.getRobotStartPos() == Position.Middle) {
			if (field.getSwitchPos() == Position.Right) {
				return new CenterRightSwitch();
//				return new CenterRightSwitch();
			} else if (field.getSwitchPos() == Position.Left) {
				return new CenterLeftSwitch();
//				return new CenterLeftSwitch();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotStartPos() == Position.Right && field.getSwitchPos() == Position.Right) {
			return new RightSwitch();

		} else if (field.getRobotStartPos() == Position.Left && field.getSwitchPos() == Position.Left) {
			return new LeftSwitch();
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAnyScale(FieldState field) {
		if (field.getRobotStartPos() == Position.Right) {
			if (field.getScalePos() == Position.Right) {
				return new RightScale();
			} else if (field.getScalePos() == Position.Left) {
				return new LeftScaleCross();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotStartPos() == Position.Left) {
			if (field.getScalePos() == Position.Right) {
				return new RightScaleCross();
			} else if (field.getScalePos() == Position.Left) {
				return new LeftScale();
			} else {
				return getBaseline();
			}
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAheadScale(FieldState field) {
		if (field.getRobotStartPos() == field.getScalePos()) {
			return getAnyScale(field);
		} else {
			return getSwitch(field);
		}
	} */
}
