package main;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import lijnvolgen.PIDController;
import muziek.MuziekLezer;
import tests.*;

public class RobotLauncher {
	
		/*
		 * Comments:
		 * Het openen van locaties van objecten kun je doen door
		 * met de cursos op het object te gaan staan, en de I aan te klikken.
		 */
	
	
		//geeft lego ding een naam
		Brick brick;										// Variable aangemaakt, naam brick.
		
		// Nieuwe robot constructor, lego ding
		// Constructor voor de main.
		public RobotLauncher() {
			super();
			brick = LocalEV3.get();					// LocalEV 3 te benaderen met de cursor erop te staan en op C klikken
		}
		
		//main method, nieuwe Marvin gedefinieerd en roept test aan
		public static void main(String[] args) {
		RobotLauncher deRijdendeRobot = new RobotLauncher();			// Aanmaken object
		deRijdendeRobot.startMenu();									// Openen menu
		}
	
	
		
	public void startMenu() {
		boolean menuLoop = true;
		
		System.out.println("Kies welk programma u wilt draaien:");
		System.out.println ("Links: Lijn");
		System.out.println ("Midden:  Muziek");
		System.out.println ("Rechts: Volgt nog");
		
		
		while (menuLoop) {
			int selection = 0;
			if(Button.LEFT.isDown())  selection = 1;
			if(Button.ENTER.isDown()) selection = 2;
			if(Button.RIGHT.isDown()) selection = 3;


			switch (selection) {
				case 1:
					System.out.println ("Lijn volgen");
					PIDController pid = new PIDController(); 				
					while (true) {
						if (Button.ESCAPE.isDown()) {
							Motor.B.stop();
							Motor.C.stop();
							menuLoop = false;
							break;
						}
					pid.run(); 
					//Delay.msDelay(20);		//delay om hele snelle loop te voorkomen - minder chrashes?
					} 
					break; 
				case 2:
					MuziekLezer MuziekReader = new MuziekLezer();
					MuziekReader.testRun();
					break; 
				case (3):
					System.out.println ("Volgt nog");
					break; 
			}
		}
		System.out.println("Gestopt");
		Delay.msDelay(1000);
	}
	
	
	
	
}
