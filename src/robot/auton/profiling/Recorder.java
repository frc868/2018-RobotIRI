package robot.auton.profiling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/*
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
*/

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;

/**
 *
 */
public class Recorder extends Command {

	private RecorderRunnable recorder;
	private Notifier notifier;
	private double period;

	protected static final String PATH = "/home/lvuser/";

    public Recorder(double period) {
    	recorder = new RecorderRunnable();
    	notifier = new Notifier(recorder);
    	this.period = period;
    	SmartDashboard.putString("Profile Recorder File Name", "Test");
    }

    protected void initialize() {
    	recorder.saveInitial();
    	notifier.startPeriodic(period);
    	System.out.println("Recording Profile");
    }

    protected void execute() {
    	// Intentionally blank: the Notifier records the data
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	notifier.stop();
    	//recorder.writeToFile(SmartDashboard.getString("Profile Recorder File Name", "Test"));
    }

    protected void interrupted() {
    	end();
    }

    public class RecorderRunnable implements Runnable {

    	/**
    	 * SCHEMA:
    	 * { RIGHT_DIST, RIGHT_VEL, LEFT_DIST, LEFT_VEL }
    	 */
    	ArrayList<Point> data = new ArrayList<Point>();
    	double rightInitialCounts = 0;
    	double leftInitialCounts = 0;
    	long initialTime = System.nanoTime();
    	int number = 0;

		@Override
		public synchronized void run() {
			double left_power = Robot.drivetrain.getRawLeftVelocity();
			double right_power = Robot.drivetrain.getRawRightVelocity();

			double left_counts = Robot.drivetrain.getRawLeftDistance() - leftInitialCounts;
			double right_counts = Robot.drivetrain.getRawRightDistance() - rightInitialCounts;

			long time = System.nanoTime() - initialTime;

			Point point = new Point(number, left_power, right_power, left_counts, right_counts, time);
			data.add(point);

			number++;
		}
		
		public void saveInitial() {
			rightInitialCounts = Robot.drivetrain.getRawRightDistance();
			leftInitialCounts = Robot.drivetrain.getRawLeftDistance();
			initialTime = System.nanoTime();
		}

		public synchronized void writeToFile(String filename) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(PATH));
	            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
	                    .withHeader("Number", "Left Power", "Right Power", "Left Counts", "Right Counts", "Time"));


	            for (Point point : data) {
		            csvPrinter.printRecord(point.getNumber(),
		            						point.getPowerLeft(),
		            						point.getPowerRight(),
		            						point.getCountsLeft(),
		            						point.getCountsRight(),
		            						point.getTime());
	            }

	            csvPrinter.flush();

			} catch (IOException e) {
				System.out.println("Failed to write Recorded Profile to " + filename);
			}
		}

    }
}
