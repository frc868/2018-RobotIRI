package robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DelayedCommand extends CommandGroup {

    public DelayedCommand(Command command, double time) {
    	// wait for the set ammount of time
    	addSequential(new WaitCommand(time));
    	
    	// then run the command
    	addSequential(command);
    }
}
