package robot.auton;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class FieldState {
	public static final boolean DEBUG = false;

	public enum Position {
		Unknown, Left, Right, Middle
	}
	
	private final SendableChooser<Position> robotPositionChooser = new SendableChooser<>();
	private boolean stateKnown = false;

	private Position switchPos = Position.Unknown;
	private Position scalePos = Position.Unknown;
	private Position robotStartPos = Position.Unknown;
	
	public void reset() {
		stateKnown = false;

		switchPos = Position.Unknown;
		scalePos = Position.Unknown;
		robotStartPos = Position.Unknown;
	}
	
	public boolean isStateKnown() {
		return stateKnown;
	}
	
	public boolean areAnyUnknown() {
		return switchPos == Position.Unknown || scalePos == Position.Unknown || robotStartPos == Position.Unknown;
	}
	
	public Position getRobotStartPos() {
		return robotStartPos;
	}
	
	public Position getSwitchPos() {
		return switchPos;
	}
	
	public Position getScalePos() {
		return scalePos;
	}
	
	public boolean isSwitchAhead() {
		return robotStartPos == Position.Middle || robotStartPos == switchPos;
	}
	
	public boolean isSwitchAccross() {
		return !isSwitchAhead();
	}
	
	
	public boolean isScaleAhead() {
		return robotStartPos == scalePos;
	}
	
	public boolean isScaleAccross() {
		return !isScaleAhead();
	}
	
	public void readData() {
		reset();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		robotStartPos = (Position) robotPositionChooser.getSelected();
		
		if (gameData.length() >= 2) {
			if (gameData.charAt(0) == 'L') {
				switchPos = Position.Left;
			} else if (gameData.charAt(0) == 'R') {
				switchPos = Position.Right;
			}
			if (gameData.charAt(1) == 'L') {
				scalePos = Position.Left;
			} else if (gameData.charAt(1) == 'R') {
				scalePos = Position.Right;
			}
		}
		
		stateKnown = areAnyUnknown();
	}
}
