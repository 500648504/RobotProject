package quizmaster;

import lejos.hardware.Button;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.Brick;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;
import quizmaster.DierenQuiz;

/**
 * Deze Class is de class die de hoofd functionaliteit voor de interactieve quiz bevat.
 * Quizmaster Francois vraagt de gebruiker van alles en geeft per vraag twee opties
 * als antwoord. De gebruiker geeft antwoord door op 1 van de 2 druksensoren van de 
 * robot te drukken. De sensoren hebben hun functionaliteit in de Class TouchSensor.
 * Eerst is er een aantal vragen over wat de robot hiervoor heeft
 * laten zien (lijn volgen en muziek maken). Afhankelijk van het antwoord dat de gebruiker geeft, 
 * wordt een andere vraag als volgende vraag gesteld. 
 * Na het stukje terugkijken, start de uiteindelijke quiz met vragen over dieren.
 * De dierenvragen staan allemaal in de class DierenQuiz.
 * De bewegingen en andere reacties van de robot worden gedefinieerd in de FysiekeReacties class.
 *
 */

public class Francois {

	//Variabelen
	private Brick brick;
	private TextLCD display;
	public final static int ANTWOORD_LINKS = 1;
	private TouchSensor sensor = new TouchSensor();
	private Vraag vraag;
	private FysiekeReacties reacties = new FysiekeReacties();
	private DierenQuiz dierenquiz = new DierenQuiz();
	private final int TIJD_VOOR_SCHERM_LEZEN = 3500;
	// deze tijd is in ms en geeft de gebruiker tijd om het scherm te lezen
	// voordat het gewist wordt voor meer tekst.
	
	// Het LeJos EV3 LCD scherm heeft 8 regels. De bovenste regel is regel 0,
	// de onderste regel 7 (y coordinaat). De x-coordinaat geeft de posities op
	// de regel weer, positie 0 (geheel links) tot en met 17 (geheel rechts).
	private final int LCD_LINKS_UITGELIJND = 0;
	
	
	
	// Constructor voor quizmaster Francois
	public Francois(Brick brick) {
		this.brick = brick;
		this.display = brick.getTextLCD();
	}
	
		
	// Start de Quizzzzz!
	public void runQuiz() {
		//reacties.schudHoofd();
		//reacties.wiggle();
		//reacties.pirouette();
		//reacties.scared();
		displayIntro();
		eersteVraag();
		
	}
	
	
	// Intro voor quiz, Francois stelt zich voor.
	public void displayIntro() {
		display.clear();
		display.drawString("Welkom! Ik ben uw", 0, 0);
		display.drawString("Quizmaster!", 0, 1);
		display.drawString("Ik heet Francois!", 0, 2);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
	}
	
	
	// De eerste vraag, volgt direct op de introductie van de Quiz functionaliteit.
	// Voor alle vragen geldt: de opmaak en weergave wordt geregeld in de methode stelVraag().
	public void eersteVraag() {
		display.clear();
		String vraagR1 = "En, en en?";
		String vraagR2 = "Wat vonden jullie?";
		String optie1 = "Geweldig!";
		String optie2 = "Mwoah";
		
		int keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		
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

		int keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);

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
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
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
		
		int keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			// launch methode was de lijn wel ok?
			wasLijnWelGoed();
			
		} else {
			display.clear();
			display.drawString("Dat is jammer!", 0, 0);
			startQuiz();
			// Naar de daadwerkelijke quiz
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
		
		int keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		
		if (keuze == ANTWOORD_LINKS) {
			display.clear();
			display.drawString("Mooi!", 0, 0);
			startQuiz();
			// naar de daadwerkelijke quiz
		} else {
			display.clear();
			display.drawString("Jammer...", 0, 0);
			display.drawString("Mijn programmeurs", 0, 1);
			display.drawString("willen graag horen", 0, 2);
			display.drawString("hoe het wel moest", 0, 3);
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
			startQuiz();
			// naar de daadwerkelijke quiz
		}
	}

	
	// De daadwerkelijke start van de quiz, na het kort evalueren van de lijn en muziek functies
	public void startQuiz() {
		display.clear();
		display.drawString("Dankje voor de", 0, 0);
		display.drawString("korte evaluatie", 0, 1);
		display.drawString("Maar nu de echte", 0, 3);
		display.drawString("quizzzz!", 0, 4);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		display.clear();
		display.drawString("Je kan 10 punten", 0, 0);
		display.drawString("verdienen", 0, 1);
		display.drawString("1 punt per vraag", 0, 2);
		display.drawString("Op naar vraag 1", 0, 3);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		quizVragen();
	}
	
	// Naar de vragen van de quiz
	public void quizVragen() {
		dierenquiz.vraag1();
		//vraag 1 roept de volgende vraag aan, enzovoorts
		dankjewelTotZiens();
	}
	
	// Methode waarin dankjewel en tot ziens wordt weergegeven (afsluiting en terug naar menu)
	public void dankjewelTotZiens() {
		display.drawString("Dankjewel!", 0, 4);
		display.drawString("Tot Ziens!", 0, 5);
		display.drawString("U keert nu terug", 0, 6);
		display.drawString("naar het menu.", 0, 7);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		sensor.closeSensors();
		display.clear();
	}
	
}
