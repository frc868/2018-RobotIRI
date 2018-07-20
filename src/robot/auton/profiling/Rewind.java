package robot.auton.profiling;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.ctre.phoenix.motion.MotionProfileStatus;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class Rewind extends Command {
	
	private ArrayList<Point> points;
	private long startTime; 
	private long totalTime; 

	private String filename;
	
	public Rewind (String filename) {
		requires(Robot.drivetrain);
		points = new ArrayList<Point>();
		this.filename = filename;
	}
	

	protected void initialize() {
		String csvFile = Recorder.PATH;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] point_data = line.split(cvsSplitBy);
                
                int number = Integer.parseInt(point_data[0]);
            	double left_power = Double.parseDouble(point_data[1]);
            	double right_power = Double.parseDouble(point_data[2]);
            	double left_counts = Double.parseDouble(point_data[3]);
            	double right_counts = Double.parseDouble(point_data[4]);
            	long time = Long.parseLong(point_data[5]);
            	
            	points.add(new Point(number, left_power, right_power, left_counts, right_counts, time));
          
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
				
		totalTime = points.get(points.size()-1).getTime();
		startTime = System.nanoTime();
		
	}
	
	
	protected void execute() {
		while (totalTime - points.get(points.size()-1).getTime()  < (System.nanoTime() - startTime)) {
			points.remove(points.size()-1);
		}
		
		Robot.drivetrain.setPOut(points.get(points.size()-1).getPowerRight(), points.get(points.size()-1).getPowerLeft());
	}

	@Override
	protected boolean isFinished() {
		return points.size() <= 0;
	}
	
	protected void end() {
		Robot.drivetrain.turnOff();
	}
}

