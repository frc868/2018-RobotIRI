package robot.auton.profiling;

public class Point {
	
	private int number; 
	private double power_left;
	private double power_right;
	private double counts_left;
	private double counts_right;
	private long time;
	
	public Point(int number, double power_left, double power_right, double counts_left, double counts_right, long time) {
		this.number = number;
		this.power_left = power_left;
		this.power_right = power_right;
		this.counts_left = counts_left;
		this.counts_right = counts_right;
		this.time = time;
	}
	
	public String[] to_array() {
		return new String[] {Integer.toString(number), 
							 Double.toString(power_left),
							 Double.toString(power_right), 
							 Double.toString(counts_left),
							 Double.toString(counts_right),
							 Long.toString(time)};
	}
	
	public int getNumber() {
		return number;
	}
	
	public double getPowerLeft() {
		return power_left;
	}
	public double getPowerRight() {
		return power_right;
	}
	public double getCountsLeft() {
		return counts_left;
	}
	public double getCountsRight() {
		return counts_right;
	}
	public long getTime() {
		return time;
	}


}
