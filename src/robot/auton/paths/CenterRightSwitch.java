package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.CollectCube;
import robot.auton.util.DelayedCommand;
import robot.auton.util.DriveAngle;
import robot.auton.util.DriveStraight;
import robot.auton.util.DriveStraightUntilCubeDetected;
import robot.commands.arm.GrabCube;
import robot.commands.intake.PullCube;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;


/**
 *
 */
public class CenterRightSwitch extends CommandGroup {

    public CenterRightSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SWITCH), 1.5));
    	
    	// drive up to switch
    	addSequential(new DriveAngle(26, 0, 0.4), 1);
    	addSequential(new DriveStraight(65, 0.6, 26), 3);
    	addSequential(new DriveStraight(35, 0.4, 26), 1);
    	//addSequential(new TurnToAngleGyro(10), 0.5); TODO TURN TO ANGLE
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// LAST CHECK
    	
    	// back up to starting position
//    	addSequential(new DriveStraight(-10, -0.4), 1);
//    	addSequential(new TurnToAngleGyro(-30), 1);
    	addSequential(new DriveAngle(37, -0.4, 0), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-55, -0.6, 37), 3);
    	addSequential(new DriveStraight(-20, -0.4, 37), 2);
    	
//    	// grab another one
    	//addSequential(new TurnToAngleGyro(0), 1.5); TODO TURN TO ANGLE
    	addParallel(new GrabCube(), 3);
    	addParallel(new PullCube(), 3);
    	addSequential(new DriveStraightUntilCubeDetected(40, 0.5), 1);
    	addSequential(new DriveStraightUntilCubeDetected(10, 0.3), 0.5);
    	
    	// line up to switch again
//    	addSequential(new WaitCommand(0.5));
    	//addSequential(new TurnToAngleGyro(70), 1); TODO TURN TO ANGLE
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SWITCH), 0.5));
    	addSequential(new DriveStraight(40, 0.5), 2);
    	//addSequential(new TurnToAngleGyro(10), 1); TODO TURN TO ANGLE
    	addSequential(new DriveStraight(20, 0.4), 1);
    	
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-26, -0.4), 1);
    	//addSequential(new TurnToAngleGyro(-55), 1); TODO TURN TO ANGLE
    	addSequential(new CollectCube(35), 2);
    }
}
