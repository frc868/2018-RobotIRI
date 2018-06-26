package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.CollectCube;
import robot.auton.util.DriveAngle;
import robot.auton.util.DriveStraight;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class RightScaleCross extends CommandGroup {

    public RightScaleCross() {
    	// TODO timeouts
    	
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	
    	// drive across
    	addSequential(new DriveStraightRamp(100, 0.2, 1));
    	addSequential(new DriveStraightRamp(100, 1, 0.5));
    	addSequential(new DriveAngle(85, 0.4, 0.6));
    	
    	addSequential(new DriveStraightRamp(45, 0.5, 0.75));
    	addParallel(new SetTiltPosition(Tilt.MIDDLE));
    	addSequential(new DriveStraightRamp(45, 0.75, 0.5));
    	addSequential(new DriveStraightRamp(30, 0.5, 0.65));
    	addParallel(new SetElevatorPosition(SetElevatorPosition.SCALE));
    	addSequential(new DriveStraightRamp(65, 0.65, 0));
    	
    	// put in scale
    	//addSequential(new TurnToAngleGyro(-30), 2);   TODO: TURNTOANGLEGYRO
    	addSequential(new DriveStraight(12, 0.4), 2);
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	//addSequential(new TurnToAngleGyro(-160), 2);   TODO:TURNTOANGLEGYRO
    	addParallel(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    	
    	// grab second cube
    	addSequential(new DriveArc(60, 50, 0.5, 0.4), 2);
    	addSequential(new CollectCube(25), 3);
    	
		// retry collection
		addSequential(new CollectCubeRetryConditional());
		addSequential(new CollectCubeRetryConditional());
		addSequential(new CollectCubeRetryConditional());
		
		// drive back to scale
    	///addSequential(new TurnToAngleGyro(-135), 1);   TODO:TURNTOANGLEGYRO
    	addParallel(new SetElevatorPosition(SetElevatorPosition.SCALE));
    	addSequential(new DriveStraight(-60, -0.4), 3);
    	//addSequential(new TurnToAngleGyro(-30), 1);   TODO:TURNTOANGLEGYRO
    	
    	// place in scale
    	addParallel(new SetTiltPosition(Tilt.MIDDLE));
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	// back off and reset
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	//addSequential(new TurnToAngleGyro(-140), 2);   TODO:TURNTOANGLEGYRO
    	addParallel(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    }
}
