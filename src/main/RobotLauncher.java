package main;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import lijnvolgen.PIDController;
import muziek.MuziekLezer;
import tests.*;


public class RobotLauncher {
	
	
	//geeft lego ding een naam
	Brick brick;
	
	//nieuwe robot constructorlego ding
	public RobotLauncher() {
		super();
		brick = LocalEV3.get();
	}
	
	//main method, nieuwe Marvin gedefinieerd en roept test aan
	public static void main(String[] args) {
		RobotLauncher deRijdendeRobot = new RobotLauncher();		
		
		
		

		
		
		
//		 Code: Bobby (Object PIDController)
		PIDController pid = new PIDController(); 
			pid.run(); 
			
			MuziekLezer MuziekReader = new MuziekLezer();
			MuziekReader.testRun();
		}
	
	
	
	
	
}
