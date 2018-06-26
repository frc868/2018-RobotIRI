package robot.auton.paths;


import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.DriveStraight;
import robot.commands.tilt.SetTiltPosition;

/**
 *
 */
public class LeftScale extends CommandGroup {

    public LeftScale() {    	    	
       	// Set tilt/elevator
    	addParallel(new SetTiltPosition(SetTiltPosition.TiltPosition.DOWN));    	
    	//addSequential(new DriveStraightRamp(40, 0.4, 1)); TODO DRIVE STRAIGHT RAMP
    	//addSequential(new DriveArc(80, 90, 0.9, 1)); TODO DRIVE ARC
    	addSequential(new DriveStraight(40, 1));
    	//addSequential(new DriveStraightRamp(100, 1, 0)); TODO DRIVE STRAIGHT RAMP
    	
    
    }
}
