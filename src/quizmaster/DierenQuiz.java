package quizmaster;

import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;
import quizmaster.Vraag;


/**
 * Dit is de daadwerkelijke quiz die gestart wordt vanuit Francois na de algemene vragen.
 * Per vraag wordt het antwoord opgeslagen in de array antwoorden.
 * Een goed antwoord geeft 1 punt, een fout antwoord geeft 0 punten.
 * Na het beantwoorden van een vraag, krijg je feedback of het goed of fout was.
 * Aan het einde wordt je totaalscore berekend en weergeven, waarna je terugkeert
 * naar het menu.
 * @author Ilona
 */


public class DierenQuiz {

	// Variabelen
	private int[] antwoorden;
	private final int MAX_AANTAL_VRAGEN = 10;
	private final int TIJD_VOOR_SCHERM_LEZEN = 3000;
	private TextLCD display;
	private int vraagnummer;
	private int keuze;
	private int totaalScore = 0;
	public final static int ANTWOORD_LINKS = 1;
	public final static int ANTWOORD_RECHTS = 2;
	private final int ARRAY_POSITIE_CORRECTIE = 1;
	private Vraag vraag;
	private FysiekeReacties reacties;
	
	// Het LeJos EV3 LCD scherm heeft 8 regels. De bovenste regel is regel 0,
		// de onderste regel 7 (y coordinaat). De x-coordinaat geeft de posities op
		// de regel weer, positie 0 (geheel links) tot en met 17 (geheel rechts).
		private final int LCD_LINKS_UITGELIJND = 0;
		private final int LCD_EERSTE_REGEL = 0;
		private final int LCD_TWEEDE_REGEL = 1;
		private final int LCD_VIERDE_REGEL = 3;
		private final int LCD_ZESDE_REGEL = 5;
		private final int LCD_ZEVENDE_REGEL = 6;
		private final int LCD_ACHTSTE_REGEL = 7;

	// constructor
	public DierenQuiz(Vraag vraag, TextLCD display, FysiekeReacties reacties) {
		this.vraag = vraag;
		this.display = display;
		this.reacties = reacties;
		// het display, de vraag en de reacties zijn al nieuw aangemaakt in Francois en 
		// worden meegegeven vanuit Francois bij het aanroepen van de constructor
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout(keuze);
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
		int keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed(keuze);
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
		keuze = vraag.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed(keuze);
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
		display.drawString("We zijn klaar!", LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
		display.drawString("Je hebt ", LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
		display.drawString(naarString(totaalScore), 9, LCD_TWEEDE_REGEL);
		display.drawString("Gefeliciteerd!", LCD_LINKS_UITGELIJND, LCD_VIERDE_REGEL);
		display.drawString("Dit is het einde", LCD_LINKS_UITGELIJND, LCD_ZESDE_REGEL);
		display.drawString("van de quiz!", LCD_LINKS_UITGELIJND, LCD_ZEVENDE_REGEL);
		reacties.pirouette();
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
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
	public void linksIsGoed(int keuze) {
		if (keuze == ANTWOORD_LINKS) {
			wisRegels();
			display.drawString("Dat is goed", LCD_LINKS_UITGELIJND, LCD_ACHTSTE_REGEL);
			antwoorden[vraagnummer - ARRAY_POSITIE_CORRECTIE] = 1;
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		} else {
			wisRegels();
			display.drawString("Dat is fout", LCD_LINKS_UITGELIJND, LCD_ACHTSTE_REGEL);
			antwoorden[vraagnummer - ARRAY_POSITIE_CORRECTIE] = 0;
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		}
	}

	// Evaluatie en punten toewijzen als het eerste antwoord fout is.
	public void linksIsFout(int keuze) {
		if (keuze == ANTWOORD_LINKS) {
			wisRegels();
			display.drawString("Dat is fout", LCD_LINKS_UITGELIJND, 7);
			antwoorden[vraagnummer - ARRAY_POSITIE_CORRECTIE] = 0;
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		} else {
			wisRegels();
			display.drawString("Dat is goed", LCD_LINKS_UITGELIJND, 7);
			antwoorden[vraagnummer - ARRAY_POSITIE_CORRECTIE] = 1;
			Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		}
	}

}
