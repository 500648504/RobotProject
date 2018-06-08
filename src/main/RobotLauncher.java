package main;
import doolhof.Mazerunner;
import quizmaster.Francois;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
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
			//TextLCD display = brick.getTextLCD();
		}
		
		//main method, nieuwe Marvin gedefinieerd en roept test aan
		public static void main(String[] args) {
		RobotLauncher deRijdendeRobot = new RobotLauncher();			// Aanmaken object
		deRijdendeRobot.startMenu();									// Openen menu
		
		


		}
	
	
		
	public void startMenu() {
		
		// Variabelen
		boolean menuLoop = true;
		TextLCD display = brick.getTextLCD();
		
		showMenu(display);
		
		
		while (menuLoop) {
			int selection = 0;
			if(Button.LEFT.isDown())  selection = 1;
			if(Button.ENTER.isDown()) selection = 2;
			if(Button.RIGHT.isDown()) selection = 3;
			if(Button.ESCAPE.isDown()) selection = 4;


			switch (selection) {
				case 1:
					display.drawString("Start Lijn Volgen", 0, 8);
					PIDController pid = new PIDController(); 				
					while (true) {
						if (Button.ESCAPE.isDown()) {
							Motor.B.stop();
							Motor.C.stop();
							showMenu(display);
							break;
						}

						pid.run(); 
					} 
					break; 
				case 2:
					display.drawString("Start Muziek Speler", 0, 8);
					MuziekLezer MuziekReader = new MuziekLezer();
					MuziekReader.testRun();
					showMenu(display);
					break;
				case (3):
					//System.out.println ("\nStart Maze Runner");
					//Mazerunner Mazerunner = new Mazerunner();	
					//Mazerunner.runMaze();
					display.drawString("Start de Quiz", 0, 8);
					Francois boulanger = new Francois(brick);
					boulanger.runQuiz();
					showMenu(display);
					break; 
				case (4):
					menuLoop=false;
					break;
					
					}
		}
		
		display.drawString("Gestopt", 0, 8);
		
	}
	public static void showMenu(TextLCD display) {
		
		display.drawString("Kies programma:", 0, 1);
		display.drawString("Links: Lijn", 0, 2);
		display.drawString("Midden:Muziek", 0, 3);
		display.drawString("Rechts:Quiz", 0, 4);
		display.drawString("Escape:EV3 menu", 0, 5);
	}
	
	
	
	
}
