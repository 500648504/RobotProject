package quizmaster;

import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.Brick;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

/**
 * Deze Class is de class die de functionaliteit voor de interactieve quiz bevat.
 * Quizmaster Francois vraagt de gebruiker van alles en geeft per vraag twee opties
 * als antwoord. De gebruiker geeft antwoord door op 1 van de 2 druksensoren van de 
 * robot te drukken. Afhankelijk van het antwoord dat de gebruiker geeft, wordt 
 * een andere vraag als volgende vraag gesteld. Voor het exacte schema, zie de
 * bijbehorende documentatie.
 *
 */

public class Francois {

	//Variabelen
	private final static int ANTWOORD_LINKS = 1;
	private final static int ANTWOORD_RECHTS = 2;
	EV3TouchSensor linksTouch = new EV3TouchSensor(SensorPort.S1);
	EV3TouchSensor rechtsTouch = new EV3TouchSensor(SensorPort.S4);
	Brick brick;
	TextLCD display;
	
	
	// Constructor voor quizmaster Francois
	public Francois(Brick brick) {
		this.brick = brick;
		TextLCD display = brick.getTextLCD();
	}
	
	
	// Start de Quizzzzz!
	public void runQuiz() {
		displayIntro();
		eersteVraag();
	}
	
	//links 1 en rechts 4 als je voor de robot zit
	// Ontvangen en verwerken van antwoord keuzes via de druksensoren
	// op poorten S1 (links) en S4 (rechts). 
	public int getSensorInput() {
		float[] antwoordLinks = new float[linksTouch.sampleSize()];
		float[] antwoordRechts = new float[rechtsTouch.sampleSize()];

		do {
			linksTouch.fetchSample(antwoordLinks, 0);
			rechtsTouch.fetchSample(antwoordRechts, 0);
			// loop blijft lopen totdat 1 van de twee druk sensoren wordt ingedrukt.
		} while (antwoordLinks[0] == 0 && antwoordRechts[0] == 0);

		linksTouch.close();
		rechtsTouch.close();
		
		// Uitlezen welke van de twee was ingedrukt en feedback op scherm weergeven.
		if (antwoordLinks[0] == 1) {
			System.out.println("Links ingedrukt");
			return ANTWOORD_LINKS;
		} else {
			System.out.println("Rechts ingedrukt");
			return ANTWOORD_RECHTS;
		}
	}
	
	
	// Intro voor quiz, Francois stelt zich voor.
	public void displayIntro() {
		display.clear(8);
		display.drawString("Welkom! Ik ben uw", 0, 1);
		display.drawString("Quizmaster!", 0, 2);
		display.drawString("Ik heet Francois!", 0, 3);
		display.drawString("F", 18, 8);
		getSensorInput();
	}
	
	
	// De eerste vraag, volgt direct op de introductie van de Quiz functionaliteit.
	public void eersteVraag() {
		String vraagR1 = "En, en en?";
		String vraagR2 = "Wat vonden jullie?";
		String optie1 = "Geweldig!";
		String optie2 = "Mwoah";
		
		int keuze = stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			// launch methode met vraag om alles nog eens te laten zien (alles = lijn volgen en muziek)
		} else {
			// launch methode met vraag over Jodi Bernal
		}		
	}
	
	
	// Algemene stel vraag layout, de methodes met de vragen definieren de strings die ingevuld
	// moeten worden in deze algemene methode.
	public int stelVraag(String vraagR1, String vraagR2, String optie1, String optie2) {
		display.clear(8);
		display.drawString(vraagR1, 0, 1);
		display.drawString(vraagR2,  0, 2);
		display.drawString(optie1, 0, 5);
		display.drawString(optie2, 0, (18 - optie2.length()));
		display.drawString("(L)", 0, 5);
		display.drawString("(R)", 16, 5);
		return getSensorInput();	
	}
	
}
