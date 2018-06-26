package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import robot.auton.util.CollectCube;
import robot.auton.util.DelayedCommand;
import robot.commands.arm.GrabCube;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorLimits;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.PowerPack;
import robot.subsystems.Tilt;
import robot.auton.util.DriveStraight;

/**
 *
 */
public class CenterLeftSwitch extends CommandGroup {

    public CenterLeftSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(SetTiltPosition.TiltPosition.DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SWITCH), 1.5));
    	
    	// drive up to switch
    	addSequential(new DriveAngle(-35, 0.4, 0), 1);
    	addSequential(new DriveStraight(65, 0.6), 3);
    	addSequential(new DriveStraight(30, 0.4), 1);
    	addSequential(new TurnToAngleGyro(-10), 0.5);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// LAST CHECK
    	
    	// back up to starting position
//    	addSequential(new DriveStraight(-10, -0.4), 1);
//    	addSequential(new TurnToAngleGyro(-30), 1);
    	addSequential(new DriveAngle(-30, 0, -0.4), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-55, -0.6), 3);
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	
//    	// grab another one
    	addSequential(new TurnToAngleGyro(0), 1.5);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraightUntilDetected(40, 0.5), 1);
    	addSequential(new DriveStraightUntilDetected(10, 0.3), 0.5);
    	
    	// line up to switch again
//    	addSequential(new WaitCommand(0.5));
    	addSequential(new TurnToAngleGyro(-70), 1);
    	addSequential(new DriveStraight(40, 0.5), 2);
    	addParallel(new SetElevatorPosition(ElevatorPosition.SWITCH));
    	addSequential(new TurnToAngleGyro(-10), 1);
    	addSequential(new DriveStraight(12, 0.4), 1);
    	
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-26, -0.4), 1);
    	addSequential(new TurnToAngleGyro(55), 1);
    	addSequential(new CollectCube(35), 2);
    	
    }
}
