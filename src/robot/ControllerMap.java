package robot;

import edu.wpi.first.wpilibj.Joystick;

//return port numbers for different gamepad/joystick types as ints

public class ControllerMap {
	
	//ports for different controller inputs, base reference is XBOX
	private final int[] xbox = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
	private final int[] ps = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
	private final int[] logitech = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
	//puts port numbers into array for referencing through ControllerType via an int
	private final int[][] controlPort = {xbox, ps, logitech};
	//sets default controller type
	public int controllerType = 0;
	
	public void setControllerType(int controllerType){
		this.controllerType = controllerType >= 0 && controllerType <= 2 ? 0 : controllerType;
	}
	//right x axis
	public int getRXPort(){
		return controlPort[controllerType][13];
	}
	//right y axis
	public int getRYPort(){
		return controlPort[controllerType][14];
	}
	//left x axis
	public int getLXPort(){
		return controlPort[controllerType][15];
	}
	//left y axis
	public int getLYPort(){
		return controlPort[controllerType][16];
	}
	//right trigger
	public double getRT(Joystick stick){
		if(controllerType == 2){
			return stick.getRawButton(controlPort[controllerType][11]) ? 0 : 1;
		}else{
			return stick.getRawAxis(controlPort[controllerType][17]);
		}
	}
	//left trigger
	public double getLT(Joystick stick){
		if(controllerType == 2){
			return stick.getRawButton(controlPort[controllerType][12]) ? 0 : 1;
		}else{
			return stick.getRawAxis(controlPort[controllerType][18]);
		}
	}
	//A button
	public int APort(){
		return controlPort[controllerType][0];
	}
	
	public int BPort(){
		return controlPort[controllerType][0];
	}
	
	public int XPort(){
		return controlPort[controllerType][0];
	}
	
	public int YPort(){
		return controlPort[controllerType][0];
	}
	//right bumper
	public int RBPort(){
		return controlPort[controllerType][0];
	}
	//left bumper
	public int LBPort(){
		return controlPort[controllerType][0];
	}
	//right stick
	public int RSPort(){
		return controlPort[controllerType][0];
	}
	//left stick
	public int LSPort(){
		return controlPort[controllerType][0];
	}
	//menu button
	public int MPort(){
		return controlPort[controllerType][0];
	}
	//start button
	public int SPort(){
		return controlPort[controllerType][0];
	}
	
}