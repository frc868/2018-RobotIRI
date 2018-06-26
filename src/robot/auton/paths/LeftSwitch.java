package robot.auton.paths;


import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.DelayedCommand;
import robot.auton.util.DriveStraight;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;

/**
 *
 */
public class LeftSwitch extends CommandGroup {

    public LeftSwitch() {
    	// set to 45 & switch
    	addParallel(new SetTiltPosition(SetTiltPosition.TiltPosition.DOWN));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SWITCH), 1));
    	
    	// drive up
    	addSequential(new DriveStraight(140, 0.75), 4);
    	//addSequential(new TurnByAngleGyro(90), 2); TODO TURN BY ANGLE GYRO
    	addSequential(new DriveStraight(20, 0.5), 2);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 1);
    	
    	// back up
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	addSequential(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    }
}
