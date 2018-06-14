package quizmaster;

import lejos.hardware.lcd.TextLCD;
import lejos.hardware.Brick;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;
import quizmaster.QuizDieren;
import quizmaster.Vraag;

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
 * @author Ilona
 */

public class QuizIntroFrancois {

	//Variabelen
	private TextLCD display;
	public final static int ANTWOORD_LINKS = 1;
	private Vraag vraag;
	private FysiekeReacties reacties = new FysiekeReacties();
	private QuizDieren dierenquiz;
	private final int TIJD_VOOR_SCHERM_LEZEN = 3500;
	// deze tijd is in ms en geeft de gebruiker tijd om het scherm te lezen
	// voordat het gewist wordt voor meer tekst.
	
	// Het LeJos EV3 LCD scherm heeft 8 regels. De bovenste regel is regel 0,
	// de onderste regel 7 (y coordinaat). De x-coordinaat geeft de posities op
	// de regel weer, positie 0 (geheel links) tot en met 17 (geheel rechts).
	private final int LCD_LINKS_UITGELIJND = 0;
	private final int LCD_EERSTE_REGEL = 0;
	private final int LCD_TWEEDE_REGEL = 1;
	private final int LCD_DERDE_REGEL = 2;
	private final int LCD_VIERDE_REGEL = 3;
	private final int LCD_VIJFDE_REGEL = 4;
	private final int LCD_ZESDE_REGEL = 5;
	private final int LCD_ZEVENDE_REGEL = 6;
	
	
	
	// Constructor voor quizmaster Francois
	public QuizIntroFrancois(Brick brick) {
		this.display = brick.getTextLCD();
		this.vraag = new Vraag(display);
	}
	
		
	// Start de Quizzzzz!
	public void runQuiz() {

		displayIntro();
		reacties.scared();
		eersteVraag();
		
	}
	
	
	// Intro voor quiz, Francois stelt zich voor.
	public void displayIntro() {
		display.clear();
		display.drawString("Welkom! Ik ben uw", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
		display.drawString("Quizmaster!", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
		display.drawString("Ik heet Francois!", LCD_LINKS_UITGELIJND, LCD_DERDE_REGEL);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN*3);
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
			display.drawString("Lijn en muziek", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
			display.drawString("starten vanuit", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
			display.drawString("het menu", LCD_LINKS_UITGELIJND, LCD_DERDE_REGEL);
			vraag.dankjewelTotZiens();
			// terug naar menu

		} else {
			display.clear();
			display.drawString("Vakantie!!!", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
			display.drawString("Ik ga nu naar", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
			display.drawString("Jodi Bernal luis-", LCD_LINKS_UITGELIJND, LCD_DERDE_REGEL);
			display.drawString("teren!", LCD_LINKS_UITGELIJND, LCD_VIERDE_REGEL);
			display.drawString("Wat is er?", LCD_LINKS_UITGELIJND, LCD_ZESDE_REGEL);
			display.drawString("Wat kijk je raar?", LCD_LINKS_UITGELIJND, LCD_ZEVENDE_REGEL);
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
			display.drawString("Dat is jammer!", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
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
			display.drawString("Mooi!", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
			startQuiz();
			// naar de daadwerkelijke quiz
		} else {
			display.clear();
			display.drawString("Jammer...", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
			display.drawString("Mijn programmeurs", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
			display.drawString("willen graag horen", LCD_LINKS_UITGELIJND, LCD_DERDE_REGEL);
			display.drawString("hoe het wel moest", LCD_LINKS_UITGELIJND, LCD_VIERDE_REGEL);
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
			startQuiz();
			// naar de daadwerkelijke quiz
		}
	}

	
	// De daadwerkelijke start van de quiz, na het kort evalueren van de lijn en muziek functies
	public void startQuiz() {
		display.clear();
		display.drawString("Dankje voor de", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
		display.drawString("korte evaluatie", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
		display.drawString("Maar nu de echte", LCD_LINKS_UITGELIJND, LCD_VIERDE_REGEL);
		display.drawString("quizzzz!", LCD_LINKS_UITGELIJND, LCD_VIJFDE_REGEL);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		display.clear();
		display.drawString("Je kan 10 punten", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
		display.drawString("verdienen", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
		display.drawString("1 punt per vraag", LCD_LINKS_UITGELIJND, LCD_DERDE_REGEL);
		display.drawString("Op naar vraag 1", LCD_LINKS_UITGELIJND, LCD_VIERDE_REGEL);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		quizVragen();
	}
	
	// Naar de vragen van de quiz
	public void quizVragen() {
		dierenquiz = new QuizDieren(this.vraag, this.display, this.reacties);
		dierenquiz.vraag1();
		//vraag 1 roept de volgende vraag aan, enzovoorts
		vraag.dankjewelTotZiens();
	}
	
	
	
}
