/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public static final XboxController driver = new XboxController(0);
	public static final XboxController op = new XboxController(1);
	
	Button A;
	Button B;
	Button X;
	Button Y;
	Button RB;
	Button LB;
	Button RS;
	Button LS;
	Button Menu;
	Button Start;
	Button POVU;
	Button POVR;
	Button POVD;
	Button POVL;
	
	private final double DEADZONE = 0.1;

	
	public OI(){}
	
	
	
	public void initDriver(){
		init(driver);
	}
	
	public void initOp(){
		init(op);
	}
	
	public void init(XboxController controller) {
		A = new JoystickButton(controller, 0);
		B = new JoystickButton(controller, 1);
		X = new JoystickButton(controller, 2);
		Y = new JoystickButton(controller, 3);
		RB = new JoystickButton(controller, 4);
		LB = new JoystickButton(controller, 5);
		RS = new JoystickButton(controller, 6);
		LS = new JoystickButton(controller, 7);
		Menu = new JoystickButton(controller, 8);
		Start = new JoystickButton(controller, 9);
		POVU = new Button() {public boolean get(){return controller.getPOV() == 0;}};
		POVR = new Button() {public boolean get(){return controller.getPOV() == 90;}};
		POVD = new Button() {public boolean get(){return controller.getPOV() == 180;}};
		POVL = new Button() {public boolean get(){return controller.getPOV() == 270;}};
	}
	
	public static double getDRX(){
		return driver.getRawAxis(0);
	}
	
	public static double getDRY(){
		return driver.getRawAxis(1);
	}
	
	public static double getDLX(){
		return driver.getRawAxis(2);
	}
	
	public static double getDLY(){
		return driver.getRawAxis(3);
	}
	
	public static double getDRT(){
		return driver.getRawAxis(4);
	}
	
	public static double getDLT(){
		return driver.getRawAxis(5);
	}
	
	public static double getORX(){
		return op.getRawAxis(0);
	}
	
	public static double getORY(){
		return op.getRawAxis(1);
	}
	
	public static double getOLX(){
		return op.getRawAxis(2);
	}
	
	public static double getOLY(){
		return op.getRawAxis(3);
	}
	
	public static double getORT(){
		return op.getRawAxis(4);
	}
	
	public static double getOLT(){
		return op.getRawAxis(5);
	}
	
	public void smartDashUpdate(){
		
	}
}
