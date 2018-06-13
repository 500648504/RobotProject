package muziek;

import java.io.File;
import java.util.ArrayList;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;


/**
 * @author robotgroup
 * De robot neemt een bepaalde kleur waar via de kleurensensor. 
 * Iedere kleur komt overeen met een bepaalde waarde, zo geeft zwart bijvoorbeeld altijd 7.
 * Wij hebben deze waarde gekoppeld aan een bepaalde toon.
 * Tijdens de kleurwaarneming speelt de robot de bijbehorende toon af en slaat de toon op in een array.
 * Zo kan de robot ook na het waarnemen van de kleuren het liedje nog een keer afspelen.
 *
 */

public class MuziekLezer {
							
	private final int LEESSNELHEID = 300;								// Dit is hoe snel de robot rijdt.
	private final int MUZIEKVOLUME = 10;								// Volume van de muziek
	private final int NOOT_LENGTE_LEES = 400;							// Dit is hoelang de toon wordt afgespeeld, direct (tijdens het rijden)
	private final int NOOT_LENGTE_SPEEL = 250;							// Dit is hoelang de toon wordt afgespeeld, achteraf (via array)
	private final int [] NOOT_FREQ = { 440, 293, 329, 349, -1, -1, 391, 20000 };	// Alle frequenties staan in een Array. 
	private final String [] NOOT_NAAMKLEUR = { "A - Rood", "D - Groen", "E - Blauw", "F - Geel", "", "", "G - Wit", ". - Zwart" };  // De kleuren staan ook in een Array, samengevoegd met de noot. 	
	private final int KLEUR_ZWART = 7;									// 7 is zwart, nodig voor stopconditie
	
	private TextLCD display;											// Het object display wordt opgehaald.
	private ArrayList<Integer> sampleLijst = new ArrayList<>(); 	  	// Arraylist, deze wordt gevuld met de kleurwaardes die gescand worden.
	int currentSample;													// De waarde van een lichtmeting (dit is een integer)
	
	// Constructor
	public MuziekLezer(Brick brick) {									// Constructor met de brick (De EV3, het lego object)		
		this.display = brick.getTextLCD();								// Het LCD scherm wordt opgeroepen.
	}
	
	public void leesMuziek() {											// Methode leesMuziek
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);		// Het object scanner wordt aangemaakt, de scanner kan nu gebruikt worden.
		Sound.setVolume(MUZIEKVOLUME);									// Het geluid wordt op volume 10 gezet
		Motor.A.setSpeed(LEESSNELHEID);									// Snelheid wordt geactiveerd
		Motor.D.setSpeed(LEESSNELHEID);

		display.clear();												// Het display wordt leeggemaakt, voordat de robot muziek gaat scannen.
	
		while (doorgaanMuziek()) {										// Zolang de methode doorgaan true is gaat het door.
		
			
			if (Button.ESCAPE.isDown()) {								// Bij escape indrukken: stoppen motoren
				break;
			}
			
			Motor.A.rotate(120, true);									//Motor draait elke keer 120 graden van een hele omwenteling (360)
			Motor.D.rotate(120, true);									//en activeert de sensor (en evt neutron cannon)
			
			currentSample = sensor.getColorID(); 						// Haalt gescande kleur op.
				
				if (currentSample == -1) {								//onze zwart was op het eind van de week niet zwart genoeg meer,
					currentSample = KLEUR_ZWART ;						//en werd vaak gemeten als -1, wat het programma stopte omdat hij dan
				}														//index -1 leest. Daarom zetten we -1 terug naar zwart.
							
				sampleLijst.add(0, currentSample); 						// Stopt gescande kleur sampleLijst Array
				
							
				display.scroll();												//speelt de goede toon en laat hem op scherm zien
				
				display.drawString(NOOT_NAAMKLEUR[currentSample], 0, 7);		// Laat de toon op het scherm zien (op basis van gescande nummer, zoekt plek in de array op) en verteld de positie  waarop deze op het scherm moet komen 0.7
				Sound.playTone(NOOT_FREQ[currentSample], NOOT_LENGTE_LEES);		// Speelt de gescande kleur af (kleur wordt omgezet in een toon)
			}
		

		sensor.close();															// na de methode stopt de scanner en stoppen de motoren.

	
		return;
	}
	
	public void speelMuziek() {
		display.clear();														// Display wordt leeggemaakt.
		display.drawString("Nog een keer!", 2, 0);								// De tekst verschijnt op het scherm (positie 2,0)
		display.drawString("druk op een toets", 0 , 1);							// De tekst verschijnt op het scherm (positie 0,1)
		Button.waitForAnyPress();												// Wacht op een toets
		display.clear();														// Scherm wordt weer leeggemaakt.
		display.drawString("Speelt af...", 1, 3);								// Tekst speelt af komt in beeld.
		int currentTone;														// Een int wordt aangemaakt, voor de for-loop.
		String currentString;													// Een String wordt aangemaakt, voor de for-loop.
		for (int index = (sampleLijst.size()-1) ; index >= 0 ; index --) {		//omgekeerd want de array wordt omgekeerd gevuld
			display.clear(0);													//speelt de goede toon en laat hem op scherm zien
			
			currentTone = NOOT_FREQ[sampleLijst.get(index)];					// Toon wordt opgehaald
			currentString = NOOT_NAAMKLEUR[sampleLijst.get(index)];				// Kleur wordt aangemaakt
			display.drawString(currentString, 3, 0);							// Kleur + Toon komt op t scherm
			
			Sound.playTone(currentTone, NOOT_LENGTE_SPEEL);						// opgehaalde kleuren en tonen worden gespeeld.				
		}
		display.clear();														// Na het afspelen van de kleuren komt de tekst:
		display.drawString("Ta da!", 7, 5);										// Ta Da! (op plek 7,5)
		display.drawString("druk op een toets", 0, 1);
		Delay.msDelay(1000);
		Button.waitForAnyPress();
		Delay.msDelay(2000);
	}

	public boolean doorgaanMuziek() { // drie keer zwart is stop
		if (sampleLijst.size() < 3) { // eerst controleren of er wel 3 zijn
			return true;
		}
		if (sampleLijst.get(0) == KLEUR_ZWART && 		// als arraynummer 0, 1 en 2 zwart zijn
			sampleLijst.get(1) == KLEUR_ZWART && 		// dan is doorgaan = false
			sampleLijst.get(2) == KLEUR_ZWART) {		// niet 3x zwart, dan doorgaan = true
			return false;																		
		}
		return true;																				
	}
}