package quizmaster;

import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.Brick;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;

/**
 * Deze Class is de class die de hoofd functionaliteit voor de interactieve quiz bevat.
 * Quizmaster Francois vraagt de gebruiker van alles en geeft per vraag twee opties
 * als antwoord. De gebruiker geeft antwoord door op 1 van de 2 druksensoren van de 
 * robot te drukken. Afhankelijk van het antwoord dat de gebruiker geeft, wordt 
 * een andere vraag als volgende vraag gesteld. Voor het exacte schema, zie de
 * bijbehorende documentatie.
 * De bewegingen en andere reacties van de robot worden gedefinieerd in de FysiekeReacties class.
 *
 */

public class Francois {

	//Variabelen
	private final static int ANTWOORD_LINKS = 1;
	private final static int ANTWOORD_RECHTS = 2;
	EV3TouchSensor linksTouch = new EV3TouchSensor(SensorPort.S1);
	EV3TouchSensor rechtsTouch = new EV3TouchSensor(SensorPort.S4);
	private Brick brick;
	private TextLCD display;
	private FysiekeReacties reacties = new FysiekeReacties();
	
	
	// Constructor voor quizmaster Francois
	public Francois(Brick brick) {
		this.brick = brick;
		this.display = brick.getTextLCD();
	}
	
	
	// Start de Quizzzzz!
	public void runQuiz() {
		reacties.schudHoofd();
		reacties.wiggle();
		reacties.pirouette();
		reacties.scared();
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
		
		// Uitlezen welke van de twee was ingedrukt en feedback op scherm weergeven.
		if (antwoordLinks[0] == 1) {
			Delay.msDelay(3000);
			return ANTWOORD_LINKS;
		} else {
			Delay.msDelay(3000);
			return ANTWOORD_RECHTS;
		}
	}
	
	
	// Intro voor quiz, Francois stelt zich voor.
	public void displayIntro() {
		display.clear();
		display.drawString("Welkom! Ik ben uw", 0, 0);
		display.drawString("Quizmaster!", 0, 1);
		display.drawString("Ik heet Francois!", 0, 2);
		Delay.msDelay(4000);
	}
	
	
	// De eerste vraag, volgt direct op de introductie van de Quiz functionaliteit.
	// Voor alle vragen geldt: de opmaak en weergave wordt geregeld in de methode stelVraag().
	public void eersteVraag() {
		display.clear();
		String vraagR1 = "En, en en?";
		String vraagR2 = "Wat vonden jullie?";
		String optie1 = "Geweldig!";
		String optie2 = "Mwoah";
		
		int keuze = stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			// launch methode met vraag om alles nog eens te laten zien (alles = lijn volgen en muziek)
			nogEenKeer();
		} else {
			// launch methode met vraag over Jodi Bernal
			fanJodiBernal();
		}		
	}
	
	
	// Vraag 2.1 om alles nog eens te laten zien
	public void nogEenKeer() {
		display.clear();
		String vraagR1 = "Zal ik alles nog";
		String vraagR2 = "eens laten zien?";
		String optie1 = "Que Si!!";
		String optie2 = "Que No!";
		
		int keuze = stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			display.clear();
			display.drawString("Lijn en muziek", 0, 0);
			display.drawString("starten vanuit", 0, 1);
			display.drawString("het menu", 0, 2);
			dankjewelTotZiens();
			// terug naar menu
			
		} else {
			display.clear();
			display.drawString("Vakantie!!!", 0, 0);
			display.drawString("Ik ga nu naar", 0, 1);
			display.drawString("Jodi Bernal luis-", 0, 2);
			display.drawString("teren!", 0, 3);
			display.drawString("Wat is er?", 0, 5);
			display.drawString("Wat kijk je raar?", 0, 6);
			// launch methode met volgende vraag
			Delay.msDelay(4000);
			fanJodiBernal();
		}
	}
	
	// Vraag 2.2: fan van Jodi Bernal
	public void fanJodiBernal() {
		display.clear();
		String vraagR1 = "Ben jij dan geen";
		String vraagR2 = "fan van Jodi?";
		String optie1 = "Natuurlijk niet!";
		String optie2 = "Zeg ik niet!";
		
		int keuze = stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			// launch methode was de lijn wel ok?
			wasLijnWelGoed();
			
		} else {
			display.clear();
			display.drawString("Dat is jammer!", 0, 0);
			dankjewelTotZiens();
			// terug naar het menu
		}
	}
	
	//Vraag 3: Vraagt of het lijnvolgen wel goed was (reactie op geen
	// fan zijn van Jodi Bernal).
	public void wasLijnWelGoed() {
		display.clear();
		String vraagR1 = "Het lijnvolgen, ";
		String vraagR2 = "was dat wel goed?";
		String optie1 = "Dat ging prima!";
		String optie2 = "Dat kon beter.";
		
		int keuze = stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			display.clear();
			display.drawString("Mooi!", 0, 0);
			dankjewelTotZiens();
			// terug naar het menu
		} else {
			display.clear();
			display.drawString("Jammer...", 0, 0);
			display.drawString("Mijn programmeurs", 0, 1);
			display.drawString("willen graag horen", 0, 2);
			display.drawString("hoe het wel moest", 0, 3);
			dankjewelTotZiens();
			// terug naar het menu
		}
	}

	
	// Algemene stel vraag layout, de methodes met de vragen definieren de strings die ingevuld
	// moeten worden in deze algemene methode.
	public int stelVraag(String vraagR1, String vraagR2, String optie1, String optie2) {
		display.clear();
		display.drawString(vraagR1, 0, 0);
		display.drawString(vraagR2,  0, 1);
		display.drawString(optie1, 0, 4);
		display.drawString(optie2, (18 - optie2.length()), 5);
		display.drawString("(L)", 0, 7);
		display.drawString("(R)", 16, 7);
		return getSensorInput();	
	}
	
	// Methode waarin dankjewel en tot ziens wordt weergegeven (afsluiting en terug naar menu)
	public void dankjewelTotZiens() {
		display.drawString("Dankjewel!", 0, 4);
		display.drawString("Tot Ziens!", 0, 5);
		display.drawString("U keert nu terug", 0, 6);
		display.drawString("naar het menu.", 0, 7);
		linksTouch.close();
		rechtsTouch.close();
		Delay.msDelay(4000);
		display.clear();
	}
	
}
