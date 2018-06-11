package quizmaster;

import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;
import quizmaster.FysiekeReacties;
import quizmaster.Francois;


public class DierenQuiz extends Francois{

	// Variabelen
	private int [] antwoorden;
	private final int MAX_AANTAL_VRAGEN = 10;
	private TextLCD display;
	private int vraagnummer;
	private int keuze;
	private int totaalScore = 0;
		
	
	// no args constructor
	public DierenQuiz(Brick brick) {
		super(brick);
		this.display = brick.getTextLCD();
		antwoorden = new int[MAX_AANTAL_VRAGEN];
	}
	
			
	//Vraag 1
	public void vraag1() {
		display.clear();
		String vraagR1 = "Waarmee proeven";
		String vraagR2 = "vlinders?";
		String optie1 = "met hun tong";
		String optie2 = "met hun poten";
		vraagnummer = 1;
		keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();				
	}
	
	//Vraag 2
	public void vraag2() {
		display.clear();
		String vraagR1 = "Welke hond heeft";
		String vraagR2 = "een blauwe tong?";
		String optie1 = "bouvier";
		String optie2 = "chow chow";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();	
	}
	
	//Vraag 3
	public void vraag3() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "fout";
		String optie2 = "goed";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
	}
	
	//Vraag 4
	public void vraag4() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "goed";
		String optie2 = "fout";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
	}
	
	//Vraag 5
	public void vraag5() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "fout";
		String optie2 = "goed";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
	}
	
	
	//Vraag 6
	public void vraag6() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "goed";
		String optie2 = "fout";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
	}
	
	
	//Vraag 7
	public void vraag7() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "goed";
		String optie2 = "fout";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
	}
	
	
	//Vraag 8
	public void vraag8() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "fout";
		String optie2 = "goed";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsFout();
	}
	
	
	//Vraag 9
	public void vraag9() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "goed";
		String optie2 = "fout";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
	}
	
	
	//Vraag 10
	public void vraag10() {
		display.clear();
		String vraagR1 = "";
		String vraagR2 = "";
		String optie1 = "goed";
		String optie2 = "fout";
		vraagnummer++;
		int keuze = super.stelVraag(vraagR1, vraagR2, optie1, optie2);
		linksIsGoed();
	}
	
	
	//Totaalscore berekenen; het telt de waardes in de antwoorden array bij elkaar op. 
	public void berekenTotaalScore() {
		for (int index = 0; index < antwoorden.length; index++) {
			totaalScore = totaalScore + antwoorden[index];
		}
	}
	
	
	
	
	
	
	
	
	
	
	// Wissen van de antwoord opties op de display en weergeven goed of fout antwoord.
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
			antwoorden[vraagnummer-1] = 1;
			Delay.msDelay(3000);
		} else {
			wisRegels();
			display.drawString("Dat is fout", 0, 7);
			antwoorden[vraagnummer-1] = 0;
			Delay.msDelay(3000);
		}
	}
	
	// Evaluatie en punten toewijzen als het eerste antwoord fout is.
	public void linksIsFout() {
		if (keuze == ANTWOORD_LINKS) {
			wisRegels();
			display.drawString("Dat is fout", 0, 7);
			antwoorden[vraagnummer-1] = 0;
			Delay.msDelay(3000);
		} else {
			wisRegels();
			display.drawString("Dat is goed", 0, 7);
			antwoorden[vraagnummer-1] = 1;
			Delay.msDelay(3000);
		}
	}
	
}
