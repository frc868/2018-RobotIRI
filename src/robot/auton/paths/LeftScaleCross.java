package robot.auton.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.auton.util.CollectCube;
import robot.auton.util.DelayedCommand;
import robot.auton.util.DriveAngle;
import robot.auton.util.DriveStraight;
import robot.commands.intake.SetIntakePower;
import robot.commands.powerpack.SetElevatorPosition;
import robot.commands.tilt.SetTiltPosition;
import robot.commands.tilt.SetTiltPosition.TiltPosition;
import robot.subsystems.Tilt;

/**
 *
 */
public class LeftScaleCross extends CommandGroup {

    public LeftScaleCross() {
    	// TODO timeouts
    	addParallel(new SetTiltPosition(Tilt.DOWN));
    	
    	// drive across
    	//addSequential(new DriveStraightRamp(40, 0.4, 1, 0)); TODO DRIVE STRAIGHT RAMP
    	addSequential(new DriveStraight(100, 1, 0));
    	//addSequential(new DriveStraightRamp(30, 1, 0.5, 0)); TODO DRIVE STRAIGHT RAMP
//    	addSequential(new TurnToAngleGyro(-85), 3);
    	addSequential(new DriveAngle(-75, 0.6, 0.2));
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.MIDDLE), 0.5));
    	
    	// TODO DRIVE STRAIGHT RAMP
//    	addSequential(new DriveStraightRamp(45, 0.6, 1, -90));
//    	addSequential(new DriveStraightRamp(45, 1, 0.5, -90));
//    	addSequential(new DriveStraightRamp(55, 0.5, 1, -90));
//    	addSequential(new DriveStraightRamp(55, 1, 0.3, -90));
    	
    	// put in scale
    	addParallel(new SetElevatorPosition(SetElevatorPosition.SCALE));
    	//addSequential(new TurnToAngleGyro(20), 1.5); TODO TURN TO ANGLE
    	addSequential(new DriveStraight(40, 0.4, 20), 2);
    	addSequential(new SetIntakePower(-0.35), 0.5);
    	
    	// grab second cube
    	addParallel(new SetTiltPosition(TiltPosition.DOWN));
    	//addSequential(new TurnToAngleGyro(166.5), 1.5); TODO TURN TO ANGLE 
    	addParallel(new SetElevatorPosition(SetElevatorPosition.COLLECT));
    	addSequential(new DriveStraight(20, 0.40, 166.5), 3);
    	addSequential(new CollectCube(35));
    	
    	// place second
    	addSequential(new DriveStraight(-40, -0.4));
    	addParallel(new SetElevatorPosition(SetElevatorPosition.SCALE));
    	//addSequential(new TurnToAngleGyro(20), 1.5); TODO TURN TO ANGLE
    	addSequential(new SetIntakePower(-0.35), 0.5);
    }
}
