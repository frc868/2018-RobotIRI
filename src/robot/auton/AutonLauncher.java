package robot.auton;

import robot.auton.FieldState.Position;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.auton.paths.Baseline;
import robot.auton.paths.CenterLeftSwitch;
import robot.auton.paths.CenterRightSwitch;
import robot.auton.paths.LeftCross;
import robot.auton.paths.LeftScale;
import robot.auton.paths.LeftScaleCross;
import robot.auton.paths.LeftScaleSide;
import robot.auton.paths.LeftSwitch;
import robot.auton.paths.RightCross;
import robot.auton.paths.RightScale;
import robot.auton.paths.RightScaleCross;
import robot.auton.paths.RightScaleSide;
import robot.auton.paths.RightSwitch;

/**
 * This is the main entry point for our match autonomous mode.
 * This command should be constructed & started at the start of auton -
 * constructing it too soon means it won't be able to read from the SmartDashboard
 * to start the correct auton mode.
 */
public class AutonLauncher {

	enum Objective {
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
		if (field.getRobotPosition() == Position.Right && field.getScalePosition() == Position.Right) {
			return new RightScaleSide();
		} else if (field.getRobotPosition() == Position.Left && field.getScalePosition() == Position.Left) {
			return new LeftScaleSide();
		} else {
			return getSwitch(field);
		}
	}
	
	public static Command getAssistScale(FieldState field) {
		if (field.getRobotPosition() == Position.Right) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScaleSide();
			} else {
				return new LeftCross();
			}
		} else if (field.getRobotPosition() == Position.Left) {
			if (field.getScalePosition() == Position.Left) {
				return new LeftScaleSide();
			} else {
				return new RightCross();
			}
		} else {
			return getBaseline();
		}
	}

	public static Command getSwitch(FieldState field) {
		if (field.getRobotPosition() == Position.Middle) {
			if (field.getSwitchPosition() == Position.Right) {
				return new CenterRightSwitch();
//				return new CenterRightSwitch();
			} else if (field.getSwitchPosition() == Position.Left) {
				return new CenterLeftSwitch();
//				return new CenterLeftSwitch();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotPosition() == Position.Right && field.getSwitchPosition() == Position.Right) {
			return new RightSwitch();

		} else if (field.getRobotPosition() == Position.Left && field.getSwitchPosition() == Position.Left) {
			return new LeftSwitch();
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAnyScale(FieldState field) {
		if (field.getRobotPosition() == Position.Right) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScale();
			} else if (field.getScalePosition() == Position.Left) {
				return new LeftScaleCross();
			} else {
				return getBaseline();
			}
		} else if (field.getRobotPosition() == Position.Left) {
			if (field.getScalePosition() == Position.Right) {
				return new RightScaleCross();
			} else if (field.getScalePosition() == Position.Left) {
				return new LeftScale();
			} else {
				return getBaseline();
			}
		} else {
			return getBaseline();
		}
	}
	
	public static Command getAheadScale(FieldState field) {
		if (field.getRobotPosition() == field.getScalePosition()) {
			return getAnyScale(field);
		} else {
			return getSwitch(field);
		}
	}
}
