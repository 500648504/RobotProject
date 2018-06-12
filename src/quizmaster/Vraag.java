package quizmaster;

import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;
import quizmaster.TouchSensor;

/**
 * Zowel Francois als DierenQuiz maken gebruik van dezelfde methode om vragen op
 * het LCD schermpje van de LeJos EV3 weer te geven. Het zelfde geldt voor de
 * afsluitende boodschap. Daarom is deze functionaliteit 
 * verhuisd naar deze Class.
 *
 */

public class Vraag {

	// Variabelen
	private TextLCD display;	
	private TouchSensor sensor = new TouchSensor();
	private final int TIJD_VOOR_SCHERM_LEZEN = 3500;
	
	
	// Het LeJos EV3 LCD scherm heeft 8 regels. De bovenste regel is regel 0,
	// de onderste regel 7 (y coordinaat). De x-coordinaat geeft de posities op
	// de regel weer, positie 0 (geheel links) tot en met 17 (geheel rechts).
	private final int LCD_LINKS_UITGELIJND = 0;
	private final int LCD_EERSTE_REGEL = 0;
	private final int LCD_TWEEDE_REGEL = 1;
	private final int LCD_VIJFDE_REGEL = 4;
	private final int LCD_ZESDE_REGEL = 5;
	private final int LCD_ZEVENDE_REGEL = 6;
	private final int LCD_ACHTSTE_REGEL = 7;
	
	
	// Constructor
	public Vraag(TextLCD display) {
		this.display = display;
	}
		
	
	// Algemene stel vraag layout, de methodes met de vragen definieren de strings
	// die ingevuld
	// moeten worden in deze algemene methode.
	public int stelVraag(String vraagR1, String vraagR2, String optie1, String optie2) {
		display.clear();
		display.drawString(vraagR1, LCD_LINKS_UITGELIJND, LCD_EERSTE_REGEL);
		display.drawString(vraagR2, LCD_LINKS_UITGELIJND, LCD_TWEEDE_REGEL);
		display.drawString(optie1, LCD_LINKS_UITGELIJND, LCD_VIJFDE_REGEL);
		display.drawString(optie2, (18 - optie2.length()), LCD_ZESDE_REGEL);
		display.drawString("(L)", LCD_LINKS_UITGELIJND, LCD_ACHTSTE_REGEL);
		display.drawString("(R)", 15, LCD_ACHTSTE_REGEL);
		return sensor.getSensorInput();
	}
	
	// Methode waarin dankjewel en tot ziens wordt weergegeven (afsluiting en terug
	// naar menu)
	public void dankjewelTotZiens() {
		display.drawString("Dankjewel!", LCD_LINKS_UITGELIJND, LCD_VIJFDE_REGEL);
		display.drawString("Tot Ziens!", LCD_LINKS_UITGELIJND, LCD_ZESDE_REGEL);
		display.drawString("U keert nu terug", LCD_LINKS_UITGELIJND, LCD_ZEVENDE_REGEL);
		display.drawString("naar het menu.", LCD_LINKS_UITGELIJND, LCD_ACHTSTE_REGEL);
		Delay.msDelay(TIJD_VOOR_SCHERM_LEZEN);
		sensor.closeSensors();
		display.clear();
	}
}
