package robot.auton.util;


import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.arm.GrabCube;
import robot.commands.intake.PullCube;

/**
 *
 */
public class CollectCube extends CommandGroup {

	public CollectCube(double distance) {
    	addParallel(new GrabCube());
    	addParallel(new PullCube());
    	addSequential(new DriveStraightUntilCubeDetected(distance, 0.30));
    }
}
