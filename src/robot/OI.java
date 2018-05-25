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
	
	private static final XboxController driver = new XboxController(0);
	private static final XboxController op = new XboxController(1);
	
	private final double DEADZONE = 0.1;
	
	public OI(){}
	
	public void initDriver(){
		//init buttons
		Button A = new JoystickButton(driver, 0);
		Button B = new JoystickButton(driver, 1);
		Button X = new JoystickButton(driver, 2);
		Button Y = new JoystickButton(driver, 3);
		Button RB = new JoystickButton(driver, 4);
		Button LB = new JoystickButton(driver, 5);
		Button RS = new JoystickButton(driver, 6);
		Button LS = new JoystickButton(driver, 7);
		Button Menu = new JoystickButton(driver, 8);
		Button Start = new JoystickButton(driver, 9);
		Button POVU = new Button() {public boolean get(){return driver.getPOV() == 0;}};
		Button POVR = new Button() {public boolean get(){return driver.getPOV() == 90;}};
		Button POVD = new Button() {public boolean get(){return driver.getPOV() == 180;}};
		Button POVL = new Button() {public boolean get(){return driver.getPOV() == 270;}};
		//link buttons to commands
		
	}
	
	public void initOp(){
		//init buttons
		Button A = new JoystickButton(op, 0);
		Button B = new JoystickButton(op, 1);
		Button X = new JoystickButton(op, 2);
		Button Y = new JoystickButton(op, 3);
		Button RB = new JoystickButton(op, 4);
		Button LB = new JoystickButton(op, 5);
		Button RS = new JoystickButton(op, 6);
		Button LS = new JoystickButton(op, 7);
		Button Menu = new JoystickButton(op, 8);
		Button Start = new JoystickButton(op, 9);
		Button POVU = new Button() {public boolean get(){return op.getPOV() == 0;}};
		Button POVR = new Button() {public boolean get(){return op.getPOV() == 90;}};
		Button POVD = new Button() {public boolean get(){return op.getPOV() == 180;}};
		Button POVL = new Button() {public boolean get(){return op.getPOV() == 270;}};
		//link buttons to commands
		
	}
	
	public double getDRX(){
		return driver.getRawAxis(0);
	}
	
	public void smartDashUpdate(){
		
	}
	
}