package quizmaster;

import lejos.hardware.lcd.TextLCD;

/**
 * Zowel Francois als DierenQuiz maken gebruik van dezelfde methode om vragen op
 * het LCD schermpje van de LeJos EV3 weer te geven. Daarom is deze functionaliteit 
 * verhuisd naar deze Class.
 *
 */

public class Vraag {

	// Variabelen
	private TextLCD display;
	private TouchSensor sensor = new TouchSensor();
	
	
	// Constructor
	public Vraag() {
		
	}
		
	
	// Algemene stel vraag layout, de methodes met de vragen definieren de strings
	// die ingevuld
	// moeten worden in deze algemene methode.
	public int stelVraag(String vraagR1, String vraagR2, String optie1, String optie2) {
		display.clear();
		display.drawString(vraagR1, 0, 0);
		display.drawString(vraagR2, 0, 1);
		display.drawString(optie1, 0, 4);
		display.drawString(optie2, (18 - optie2.length()), 5);
		display.drawString("(L)", 0, 7);
		display.drawString("(R)", 16, 7);
		return sensor.getSensorInput();
	}
	
	
}
