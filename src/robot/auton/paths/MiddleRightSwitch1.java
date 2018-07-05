package robot.auton.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.auton.DriveStraight;
import robot.commands.intake.PullCube;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class MiddleRightSwitch1 extends CommandGroup {

    public MiddleRightSwitch1() {
    	
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	addParallel(new DriveStraight(65, .4));
    	addSequential(new PullCube());
    	addSequential(new DriveStraight(10, .4));
    	
    }
}