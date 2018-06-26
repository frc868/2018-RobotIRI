package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class Baseline extends CommandGroup {

    public Baseline() {
    	addParallel(new SetTiltPosition(Tilt.UP));
    	// addSequential(new DriveStraight(100, 0.5), 3); TODO this
    }
}
