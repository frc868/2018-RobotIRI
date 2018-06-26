package robot.auton.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.DriveStraight;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class LeftScaleSide extends CommandGroup {

    public LeftScaleSide() {
    	addSequential(new DriveStraight(275, 0.7), 5);
    	addParallel(new SetTiltPosition(Tilt.MIDDLE));
    	addSequential(new DriveStraight(30, 0.4), 2);
    	//addSequential(new TurnToAngleGyro(90), 2); TODO TURN TO ANGLE
    	addSequential(new DriveStraight(-20, -0.4), 2);
    	addSequential(new SetElevatorPosition(SetElevatorPosition.SCALE), 5);
    	addSequential(new DriveStraight(25, 0.3), 2);
    	addSequential(new SetIntakePower(-0.6), 0.5);
    	addSequential(new DriveStraight(-15, -0.3), 2);
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	addParallel(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    	//addSequential(new TurnToAngleGyro(0), 3); TODO TURN TO ANGLE
    }
}
