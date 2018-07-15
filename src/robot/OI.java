/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;



import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.commands.arm.CloseArm;
import robot.commands.arm.GrabCube;
import robot.commands.arm.OpenArm;
import robot.commands.auton.TurnToAngleGyro;
import robot.commands.drivetrain.HoundButton;
import robot.commands.hook.ToggleHookPosition;
import robot.commands.intake.PullCube;
import robot.commands.intake.SetIntakePower;
import robot.commands.intake.TurnIntakeOff;
import robot.commands.powerpack.GamepadClimberControl;
import robot.commands.powerpack.GamepadElevatorControl;
import robot.commands.powerpack.SetElevatorLimits;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.powerpack.SetElevatorPower;
import robot.commands.powerpack.SetPowerPackHold;
import robot.commands.tilt.SetTiltPosition;
import robot.commands.tilt.SetTiltPosition.TiltPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final XboxController driver = new XboxController(0);
	public static final XboxController op = new XboxController(1);
	public static boolean driveDirection = true;
	
	// TODO tune this, 5% to 10%
	public static final double CONTROLLER_DEADBAND = 0.02;

	public OI() {}

	/**
	 * Binds triggers/buttons to commands for the driver
	 */
	public static void setupDriver() {

		// Drivetrain default command is ArcadeDrive
//		Robot.drivetrain.setDefaultCommand(new ArcadeDrive(driver, 1, 4));
//		
//		// Give both driver & operator intake control
//		Robot.intake.setDefaultCommand(new DualGamepadIntakeControl(driver, operator, 3, 2));
		
		//HoundButton.create(driver, 1).pressed(new SetElevatorPosition(SetElevatorPosition.COLLECT)).released(new SetPowerPackHold());

		Button bA = new JoystickButton(driver, 1);
		bA.whenPressed(new SetElevatorPosition(SetElevatorPosition.COLLECT));
		bA.whenReleased(new SetPowerPackHold());
		
		Button bB = new JoystickButton(driver, 2);
		bB.whenPressed(new SetElevatorPosition(SetElevatorPosition.SWITCH));
		bB.whenReleased(new SetPowerPackHold());

		Button bY = new JoystickButton(driver, 4);
		bY.whenPressed(new SetElevatorPosition(SetElevatorPosition.SCALE));
		bY.whenReleased(new SetPowerPackHold());
		
		Button bX = new JoystickButton(driver, 3);
		bX.whenPressed(new SetElevatorPosition(SetElevatorPosition.LOW_SCALE));
		bX.whenReleased(new SetPowerPackHold());

		Button RB = new JoystickButton(driver, 6);
		// high gear while held, low when released
//		RB.whenPressed(new SetTransmission(true));
//		RB.whenReleased(new SetTransmission(false));
		
		Button LB = new JoystickButton(driver, 5);
		// open arm while held, close when released
		LB.whenPressed(new OpenArm());
		LB.whenReleased(new CloseArm());
		
		Button start = new JoystickButton(driver, 8);
		// record motion profile
//		start.toggleWhenPressed(new ProfileRecorder(0.02));
		
		Button select = new JoystickButton(driver, 7);
		// flip drive direction
//		select.whenPressed(new SetDriveDirection(true));

//		Trigger cubeDetect = new CubeDetectedTrigger();
//		// rumble when we first get a cube
//		cubeDetect.whenActive(new RumbleDriver(1));
//		cubeDetect.whenActive(new SetLEDs(0, 255, 0));
//		cubeDetect.whenInactive(new SetLEDs(0, 0, 0));
//		
//		Trigger sixtySeconds = new MatchTimeTrigger(75);
//		// rumble when 60 seconds remain
//		sixtySeconds.whenActive(new RumbleDriver(2));
//		sixtySeconds.whenActive(new FlashLEDs(255, 255, 0, 0.5, 3));
		
		
	}

	/**
	 * Binds triggers/buttons to commands for the operator
	 */
	public static void setupOperator() {
//		Trigger cubeRumble = new CubeDetectedTrigger();
//		// rumble when we first lose a cube
//		cubeRumble.whenActive(new RumbleOperator(1));
//		cubeRumble.whenInactive(new RumbleOperator(1));
		
		Button bA = new JoystickButton(op, 1);
		// Set elevator to DOWN position
		bA.whenPressed(new SetElevatorPosition(SetElevatorPosition.COLLECT));
		bA.whenReleased(new SetPowerPackHold());

		Button bB = new JoystickButton(op, 2);

		// Set elevator to SWITCH position
		bB.whenPressed(new SetElevatorPosition(SetElevatorPosition.SWITCH));
		bB.whenReleased(new SetPowerPackHold());

		Button bX = new JoystickButton(op, 3);
		// Actuate collector arms
		bX.whenPressed(new OpenArm());
		bX.whenReleased(new CloseArm());

		Button bY = new JoystickButton(op, 4);
		// Set elevator to SCALE position
		bY.whenPressed(new SetElevatorPosition(SetElevatorPosition.SCALE));
		bY.whenReleased(new SetPowerPackHold());

		Button LB = new JoystickButton(op, 5);
		// OperatorClimberControl (joystick)
		LB.whileHeld(new GamepadClimberControl(op, 1));

		Button RB = new JoystickButton(op, 6);
		// OperatorElevatorControl (joystick)
		RB.whileHeld(new GamepadElevatorControl(op, 5));

		Button arrowUp = getPOVButton(op, 0);
		// Set Tilt to UP
		arrowUp.whenPressed(new SetTiltPosition(TiltPosition.UP));

		Button arrowRight = getPOVButton(op, 90);
		
		// Set Tilt to MIDDLE
		arrowRight.whenPressed(new SetTiltPosition(TiltPosition.MIDDLE));

		Button arrowDown = getPOVButton(op, 180);
		// Set Tilt to DOWN
		arrowDown.whenPressed(new SetTiltPosition(TiltPosition.DOWN));
		
		Button arrowLeft = getPOVButton(op, 270);
		CommandGroup intakeGroup = new CommandGroup();
		intakeGroup.addParallel(new SetTiltPosition(TiltPosition.DOWN));
		intakeGroup.addParallel(new GrabCube());
		intakeGroup.addParallel(new PullCube());
		arrowLeft.whileHeld(intakeGroup); //might get some weird results with it spam open/close arms
		
		Button start = new JoystickButton(op, 8);
		// flash LED signal to human player
//		start.whenPressed(new FlashLEDs(0, 0, 255, 0.25, 3));
		
		Button select = new JoystickButton(op, 7);
		// Enable/disable elevator limits
		select.whileHeld(new SetElevatorLimits(false));
		select.whenReleased(new SetElevatorLimits(true));
	}
	
	//get driver right X
	public static double getDRX(){
		return driver.getRawAxis(4);
	}
	
	//get driver right Y
	public static double getDRY(){
		return driver.getRawAxis(5);
	}
	
	//get driver left X
	public static double getDLX(){
		return driver.getRawAxis(0);
	}
	
	//get driver left Y
	public static double getDLY(){
		return driver.getRawAxis(1);
	}
	
	//get driver right trigger
	public static double getDRT(){
		return driver.getRawAxis(3);
	}
	
	//get driver left trigger
	public static double getDLT(){
		return driver.getRawAxis(2);
	}
	
	//get operator right X
	public static double getORX(){
		return op.getRawAxis(4);
	}
	
	//get operator right Y
	public static double getORY(){
		return op.getRawAxis(5);
	}
	
	//get operator left X
	public static double getOLX(){
		return op.getRawAxis(0);
	}
	
	//get operator left Y
	public static double getOLY(){
		return op.getRawAxis(1);
	}
	
	//get operator right trigger
	public static double getORT(){
		return op.getRawAxis(3);
	}
	
	//get operator left trigger
	public static double getOLT(){
		return op.getRawAxis(2);
	}
	
	public void smartDashUpdate(){
		
	}

	/**
	 * Helper method to treat the POV inputs as buttons.
	 * 
	 * @param controller
	 * @param angle
	 * @return
	 */
	private static Button getPOVButton(GenericHID controller, int angle) {
		return new Button() {
			@Override
			public boolean get() {
				return controller.getPOV() == angle;
			}
		};
	}
	
	public static void rumbleDriver(boolean rumble) {
		if (rumble) {
			driver.setRumble(RumbleType.kLeftRumble, 1);
			driver.setRumble(RumbleType.kRightRumble, 1);
		} else {
			driver.setRumble(RumbleType.kLeftRumble, 0);
			driver.setRumble(RumbleType.kRightRumble, 0);
		}
	}	
	
	public static void rumbleOperator(boolean rumble) {
		if (rumble) {
			op.setRumble(RumbleType.kLeftRumble, 1);
			op.setRumble(RumbleType.kRightRumble, 1);
		} else {
			op.setRumble(RumbleType.kLeftRumble, 0);
			op.setRumble(RumbleType.kRightRumble, 0);
		}
	}
}
