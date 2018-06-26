package robot.auton.paths;


import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.DelayedCommand;
import robot.auton.util.DriveStraight;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class RightCross extends CommandGroup {

    public RightCross() {
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	
    	// drive across
    	addSequential(new DriveStraight(200, 0.7), 8);
    	addSequential(new DriveStraight(18, 0.4), 2);
    	//addSequential(new TurnToAngleGyro(85), 3);  TODO: TURNTOANGLEGYRO
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.MIDDLE), 4));
    	addSequential(new DriveStraight(100, 0.6), 8);
    }
}
