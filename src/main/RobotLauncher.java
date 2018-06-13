package main;

import quizmaster.QuizIntroFrancois;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
import lijnvolgen.LijnVolgen;
import muziek.MuziekLezer;
import theremin.ThereminSpeler;

/**
 * @author Robotgroep E Dit is de main klasse van onze robot. Het Ev3 apparaat,
 *         de brick, heeft een overzichtelijk keuzemenu gekregen. Het apparaat
 *         wordt aangeroepen via RobotLauncher en door middel van input van de
 *         gebruiker kan de gebruiker een programma seleceteren. De vier opties
 *         zijn 'Lijn volgen', 'Muziek spelen', 'Quiz spelen' en 'Theremin
 *         spelen'. Elk programma kan met een andere knop vroeg tijdig beindigd
 *         worden.
 */

public class RobotLauncher {

	Brick brick;												//Variable Brick heet nu brick

	
	public RobotLauncher() {									//Constructor genaamd RobotLauncher
		super();
		brick = LocalEV3.get(); 								//Komt uit de reeds bestaande LocalEV3 klasse
	
	}
	
	public static void main(String[] args) {					// main method, nieuwe robot gedefinieerd en roept test aan
		RobotLauncher deRijdendeRobot = new RobotLauncher(); 	// Aanmaken object, genaamd 'De Rijdende Robot'.
		deRijdendeRobot.startMenu(); 							// Openen menu
		System.out.println("");
		System.out.println("==== gestopt ====");
		Delay.msDelay(2000);									// Laat de boodschap 'gestopt' nog twee seconden zien
		System.exit(0);
	}

	public void startMenu() {									// methode voor het startmenu

		boolean menuLoop = true;
		TextLCD display = brick.getTextLCD();

		showMenu(display);										// In de display wordt het menu getoond. Verschillende knoppen
																// LEFT, ENTER, RIGHT en UP zijn gekoppeld aan een switch.
		while (menuLoop) {
			int selection = 0;
			if (Button.LEFT.isDown())
				selection = 1;
			if (Button.ENTER.isDown())
				selection = 2;
			if (Button.RIGHT.isDown())
				selection = 3;
			if (Button.UP.isDown())
				selection = 4;
			if (Button.DOWN.isDown())
				selection = 5;

			switch (selection) {								//Een switch welke de verschillende onderdelen selecteerd.
			case 1:
				display.clear(7);
				display.drawString("Start Lijn Volgen", 0, 7);
				LijnVolgen pid = new LijnVolgen();
				while (true) {
					if (Button.ESCAPE.isDown()) {
						Motor.A.stop();
						Motor.D.stop();
						showMenu(display);
						break;
					}
					pid.run();
				}
				break;
			case 2:
				display.clear(7);
				display.drawString("Start Muziek", 0, 7);
				MuziekLezer MuziekReader = new MuziekLezer(brick);
				MuziekReader.leesMuziek();
				MuziekReader.speelMuziek();
				showMenu(display);
				break;
			case (3):
				display.clear(7);
				display.drawString("Start de Quiz", 0, 7);
				QuizIntroFrancois QuizIntro = new QuizIntroFrancois(brick);
				QuizIntro.runQuiz();
				showMenu(display);
				break;
			case (4):
				display.clear(7);
				display.drawString("Start Theremin", 0, 7);
				ThereminSpeler ThereminSpeler = new ThereminSpeler(brick);
				ThereminSpeler.thereminInstrument();
				showMenu(display);
				break;
			case (5):
				display.clear();
				menuLoop = false;
				break;
			}
		}

	}

	public static void showMenu(TextLCD display) {							//De tekst die in de display wordt getoond
		display.clear();
		display.drawString("Kies programma:", 0, 0);
		display.drawString("Links:  Lijn", 0, 2);
		display.drawString("Midden: Muziek", 0, 3);
		display.drawString("Rechts: Quiz", 0, 4);
		display.drawString("Omhoog: Theremin", 0, 5);
		display.drawString("Beneden:EV3 menu", 0, 7);
	}

}
