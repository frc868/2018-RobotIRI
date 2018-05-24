package robot;

import edu.wpi.first.wpilibj.Joystick;

//return port numbers for different gamepad/joystick types as ints

public class ControllerMap {
	
	//ports for different controller inputs, base reference is XBOX
	private final int[] xbox = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	private final int[] ps = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	private final int[] logitech = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	//puts port numbers into array for referencing through ControllerType via an int
	private final int[][] controlPort = {xbox, ps, logitech};
	//sets default controller type
	public int controllerType = 0;
	
	public void setControllerType(int controllerType){
		this.controllerType = controllerType >= 0 && controllerType <= 2 ? 0 : controllerType;
	}
	
	public int getRXPort(){
		return controlPort[controllerType][12];
	}
	
	public int getRYPort(){
		return controlPort[controllerType][13];
	}
	
	public int getLXPort(){
		return controlPort[controllerType][14];
	}
	
	public int getLYPort(){
		return controlPort[controllerType][15];
	}
	
	public int getRTPort(){
		return controlPort[controllerType][16];
	}
	
	public int getLTPort(){
		return controlPort[controllerType][17];
	}
	
	public int APort(){
		return controlPort[controllerType][0];
	}
	
	public int BPort(){
		return controlPort[controllerType][0];
	}
	
}