/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.auton.paths.MiddleRightSwitch1;
import robot.commands.arm.CloseArm;
import robot.commands.arm.OpenArm;
import robot.commands.auton.DriveStraight;
import robot.commands.auton.DriveStraightUntilCubeDetected;
import robot.commands.auton.TurnToAngleGyro;
import robot.commands.drivetrain.AutoShiftA;
import robot.subsystems.Arm;
import robot.subsystems.DriveTrain;

import robot.subsystems.Intake;
import robot.subsystems.Gyro;

import robot.subsystems.Hook;
import robot.subsystems.Intake;
import robot.subsystems.PowerPack;
import robot.subsystems.Tilt;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static Arm arm; 
	public static Intake intake;
	public static Hook hook;
	public static DriveTrain drivetrain;

	public static Gyro gyro;

	public static PowerPack powerpack;

	public static Tilt tilt;


	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		arm = new Arm(); 
		intake = new Intake();
		powerpack = new PowerPack();
		

		gyro = new Gyro();
		drivetrain = new DriveTrain();

		tilt = new Tilt();
		hook = new Hook();

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);

		SmartDashboard.putData("arm", Robot.arm);
		SmartDashboard.putData("DriveTrain", drivetrain);
		SmartDashboard.putData("closeArm", new CloseArm());
		SmartDashboard.putData("openArm", new OpenArm());
		
		SmartDashboard.putData("openArm", new DriveStraightUntilCubeDetected(144, .2));

	

		SmartDashboard.putData("Turn Gyro Test", new TurnToAngleGyro(30, 30));
		SmartDashboard.putData("Pid Right", Robot.drivetrain.getPIDRight());
		SmartDashboard.putData("Pid Left", Robot.drivetrain.getPIDLeft());
		SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		m_oi.setupDriver();
		m_oi.setupOperator();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Auton Init");
		Scheduler.getInstance().removeAll();
		gyro.reset();
		new MiddleRightSwitch1();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		new AutoShiftA().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
