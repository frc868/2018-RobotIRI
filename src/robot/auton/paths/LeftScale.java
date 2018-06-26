package robot.auton.paths;

import com.techhounds.auton.util.DriveArc;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightRamp;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.SetTiltPosition.TiltPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScale extends CommandGroup {

    public LeftScale() {
    	// TODO timeouts
    	    	
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));    	
//    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SCALE), 1.5));

    	// drive up & curve
    	addSequential(new DriveStraightRamp(40, 0.4, 1));
    	addSequential(new DriveArc(80, 90, 0.9, 1));
    	addSequential(new DriveStraight(40, 1));
    	addSequential(new DriveStraightRamp(100, 1, 0));
    	
    	// eject the cube
//    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
//    	addSequential(new SetIntakePower(-0.4), 0.5);
//    	
//    	// back off and reset
//    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
//    	addSequential(new TurnToAngleGyro(160), 2);
//    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
//    	
//    	// grab second cube
//    	addSequential(new DriveArc(50, 60, 0.4, 0.5), 2);
//    	addSequential(new CollectCube(25), 3);
//    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//
//    	// drive back to scale
//    	addSequential(new TurnToAngleGyro(135), 1);
//    	addParallel(new SetElevatorPosition(ElevatorPosition.SCALE));
//    	addSequential(new DriveStraight(-60, -0.4), 3);
//    	addSequential(new TurnToAngleGyro(30), 1);
//    	
//    	// place in scale
//    	addParallel(new SetTiltPosition(Tilt.POS_MID));
//    	addSequential(new SetIntakePower(-0.5), 0.5);
//    	
//    	// back off and reset
//    	addParallel(new SetTiltPosition(Tilt.POS_DOWN));
//    	addSequential(new TurnToAngleGyro(140), 2);
//    	addParallel(new SetElevatorPosition(ElevatorPosition.COLLECT));
//    	
//    	// grab third cube
//    	addSequential(new DriveArc(80, 60, 0.4, 0.6), 2);
//    	addSequential(new CollectCube(25), 3);
//    	
//		// retry collection
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
//		addSequential(new CollectCubeRetryConditional());
    }
}
