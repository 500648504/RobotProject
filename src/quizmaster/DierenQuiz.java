package quizmaster;

import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;
import quizmaster.Francois;

/**
 * Dit is de daadwerkelijke quiz die gestart wordt vanuit Francois na de algemene vragen.
 * Per vraag wordt het antwoord opgeslagen in de array antwoorden.
 * Een goed antwoord geeft 1 punt, een fout antwoord geeft 0 punten.
 * Na het beantwoorden van een vraag, krijg je feedback of het goed of fout was.
 * Aan het einde wordt je totaalscore berekend en weergeven, waarna je terugkeert
 * naar het menu.
 */


public class DierenQuiz extends Francois {

	// Variabelen
	private int[] antwoorden;
	private final int MAX_AANTAL_VRAGEN = 10;
	private TextLCD display;
	private int vraagnummer;
	private int keuze;
	private int totaalScore = 0;

	// constructor
	public DierenQuiz(Brick brick) {
		super(brick);
		this.display = brick.getTextLCD();
		antwoorden = new int[MAX_AANTAL_VRAGEN];
	}

	// Vraag 1
	public void vraag1() {
		display.clear();
		String vraagR1 = "Waarmee proeven";
		String vraagR2 = "vlinders?";
		String optie1 = "met hun tong";
		String optie2 = "met hun poten";
		vraagnummer = 1;
		keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		vraag2();
	}

	// Vraag 2
	public void vraag2() {
		display.clear();
		String vraagR1 = "Welke hond heeft";
		String vraagR2 = "een blauwe tong?";
		String optie1 = "bouvier";
		String optie2 = "chow chow";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
		vraag3();
	}

	// Vraag 3
	public void vraag3() {
		display.clear();
		String vraagR1 = "Wat is een";
		String vraagR2 = "buidelbeer?";
		String optie1 = "een kodiakbeer";
		String optie2 = "een koala";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
		vraag4();
	}

	// Vraag 4
	public void vraag4() {
		display.clear();
		String vraagR1 = "Hoeveel poten";
		String vraagR2 = "heeft een kreeft?";
		String optie1 = "10";
		String optie2 = "8";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		vraag5();
	}

	// Vraag 5
	public void vraag5() {
		display.clear();
		String vraagR1 = "Welke vogel komt";
		String vraagR2 = "het meest voor?";
		String optie1 = "duif";
		String optie2 = "kip";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
		vraag6();
	}

	// Vraag 6
	public void vraag6() {
		display.clear();
		String vraagR1 = "Hoeveel magen";
		String vraagR2 = "heeft een vogel?";
		String optie1 = "2";
		String optie2 = "3";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		vraag7();
	}

	// Vraag 7
	public void vraag7() {
		display.clear();
		String vraagR1 = "Wat is huidskleur";
		String vraagR2 = "van een ijsbeer?";
		String optie1 = "zwart";
		String optie2 = "wit";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		vraag8();
	}

	// Vraag 8
	public void vraag8() {
		display.clear();
		String vraagR1 = "Hoe heet een groep";
		String vraagR2 = "hyena's?";
		String optie1 = "een roedel";
		String optie2 = "een clan";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
		vraag9();
	}

	// Vraag 9
	public void vraag9() {
		display.clear();
		String vraagR1 = "Wat is de grootste";
		String vraagR2 = "kever in de EU?";
		String optie1 = "vliegend hert";
		String optie2 = "meikever";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		vraag10();
	}

	// Vraag 10
	public void vraag10() {
		display.clear();
		String vraagR1 = "Wat is een";
		String vraagR2 = "aardmannetje?";
		String optie1 = "stokstaartje";
		String optie2 = "naakte molrat";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
		berekenTotaalScore();
		weergevenResultaat();
	}

	// Totaalscore berekenen; het telt de waardes in de antwoorden array bij elkaar
	// op.
	public void berekenTotaalScore() {
		for (int index = 0; index < antwoorden.length; index++) {
			totaalScore = totaalScore + antwoorden[index];
		}
	}

	// Omzetten naar String van de totaalscore
	public String naarString(int totaalScore) {
		return String.format("%d punten!", totaalScore);
	}

	// Weergeven hoe je het hebt gedaan in de quiz
	public void weergevenResultaat() {
		display.clear();
		display.drawString("We zijn klaar!", 0, 0);
		display.drawString("Je hebt ", 0, 1);
		display.drawString(naarString(totaalScore), 9, 1);
		display.drawString("Gefeliciteerd!", 0, 3);
		display.drawString("Dit is het einde", 0, 5);
		display.drawString("van de quiz!", 0, 6);
		Delay.msDelay(3000);
		display.clear();
	}

	// Wissen van de antwoord opties op de display en weergeven goed of fout
	// antwoord.
	// De vraag blijft staan.
	public void wisRegels() {
		display.clear(4);
		display.clear(5);
		display.clear(7);
	}

	// Evaluatie en punten toewijzen als het eerste antwoord goed is.
	public void linksIsGoed() {
		if (keuze == ANTWOORD_LINKS) {
			wisRegels();
			display.drawString("Dat is goed", 0, 7);
			antwoorden[vraagnummer - 1] = 1;
			Delay.msDelay(3000);
		} else {
			wisRegels();
			display.drawString("Dat is fout", 0, 7);
			antwoorden[vraagnummer - 1] = 0;
			Delay.msDelay(3000);
		}
	}

	// Evaluatie en punten toewijzen als het eerste antwoord fout is.
	public void linksIsFout() {
		if (keuze == ANTWOORD_LINKS) {
			wisRegels();
			display.drawString("Dat is fout", 0, 7);
			antwoorden[vraagnummer - 1] = 0;
			Delay.msDelay(3000);
		} else {
			wisRegels();
			display.drawString("Dat is goed", 0, 7);
			antwoorden[vraagnummer - 1] = 1;
			Delay.msDelay(3000);
		}
	}

}
