package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.CollectCube;
import robot.auton.util.DelayedCommand;
import robot.auton.util.DriveStraight;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.commands.tilt.SetTiltPosition.TiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	// TODO timeouts
    	
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SCALE), 1.5)); // TODO wait

    	// drive up & curve
    	addSequential(new DriveStraightRamp(25, 0.5, 1)); // TODO faster
    	addSequential(new DriveStraight(15, 1));
    	addSequential(new DriveStraight(150, 1, -10), 4);
    	addSequential(new DriveStraightRamp(80, 1, 0.3)); // TODO faster
    	
    	// eject the cube
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addSequential(new SetIntakePower(-0.50), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.75));
    	//addSequential(new TurnToAngleGyro(-170), 2);   TODO: TURNTOANGLEGYRO
    	
    	// grab second cube
    	addSequential(new DriveStraight(25, 0.45), 3);
    	addSequential(new CollectCube(25), 2);
    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//
    	// place second cube
    	//addSequential(new TurnToAngleGyro(-167), 1); // TODO: TURNTOANGLEGYRO
    	addParallel(new SetElevatorPosition(SetElevatorPosition.SCALE));
    	addSequential(new DriveStraight(-50, -0.45), 2); // TODO faster
    	//addSequential(new TurnToAngleGyro(-20, 1.5, 0.35)); // TODO: TURNTOANGLEGYRO
    	addSequential(new SetIntakePower(-0.45), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.COLLECT), 0.75));
    	//addSequential(new TurnToAngleGyro(-158), 2);    TODO: TURNTOANGLEGYRO
    	
    	// grab third cube
    	addSequential(new DriveStraight(35, 0.45), 3); // FIXME
    	addSequential(new CollectCube(35), 2); // FIXME
    }
}
