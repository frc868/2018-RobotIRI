package robot.auton.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.DriveStraight;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class LeftCross extends CommandGroup {

    public LeftCross() {
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	
    	// drive across
    	addSequential(new DriveStraight(200, 0.7), 8);
    	addSequential(new DriveStraight(18, 0.4), 2);
    	//addSequential(new TurnToAngleGyro(-85), 3); TODO TURN TO ANGLE
    	addParallel(new SetTiltPosition(Tilt.MIDDLE));
    	addSequential(new DriveStraight(120, 0.6), 8);
    }
}
