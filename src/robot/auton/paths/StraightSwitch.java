package robot.auton.paths;



import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import robot.auton.util.DelayedCommand;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.commands.tilt.SetTiltPosition.TiltPosition;

/**
 *
 */
public class StraightSwitch extends CommandGroup {

    public StraightSwitch() {
    	addParallel(new SetTiltPosition(TiltPosition.MIDDLE));
    	addParallel(new DelayedCommand(new SetElevatorPosition(SetElevatorPosition.SWITCH), 1));
    	addSequential(new DriveArc(110), 3);
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	addSequential(new DelayedCommand(new SetIntakePower(-0.5), 1), 1);
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveArc(-20), 2);
    	addParallel(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    }
}
