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

public class MuziekLezer {
							
	private final int LEESSNELHEID = 300;				// Dit is hoe snel de robot rijdt.
	private final int MUZIEKVOLUME = 10;				//
	private final int NOOT_LENGTE_LEES = 400;
	private final int NOOT_LENGTE_SPEEL = 200;
	private final int [] NOOT_FREQ = { 440, 293, 329, 349, -1, -1, 391, -1 };
	private final String [] NOOT_NAAMKLEUR = { "A - Rood", "D - Groen", "E - Blauw", "F - Geel", "", "", "G - Wit", "Rst -Zwart" };	
	private final int KLEUR_ZWART = 7;						// 7 is zwart, nodig voor stopconditie
	
	private TextLCD display;
	private ArrayList<Integer> sampleLijst = new ArrayList<>();   // Arraylist, deze wordt gevuld met de kleurwaardes die gescand worden.
	int currentSample;										// De waarde van een lichtmeting (dit is een integer)
	
	
	// Constructor
	public MuziekLezer(Brick brick) {		
		this.display = brick.getTextLCD();
	}
	
	public void leesMuziek() {	
		
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);		// Het object scanner wordt aangemaakt, de scanner kan nu gebruikt worden.
		Sound.setVolume(MUZIEKVOLUME);											// Het geluid wordt op volume 10 gezet
		Motor.A.setSpeed(LEESSNELHEID);									// Snelheid wordt geactiveerd
		Motor.D.setSpeed(LEESSNELHEID);
<<<<<<< HEAD

		display.clear();
=======
>>>>>>> 1a4611fe0d732b5027c9d24e246ce6d220c4127b
		
		while (doorgaanMuziek()) {										// Zolang de methode doorgaan true is gaat het door.

			if (Button.ESCAPE.isDown()) {								// Bij escape indrukken: stoppen motoren
				break;
			}
			
<<<<<<< HEAD
			Motor.A.rotate(120, true);
			Motor.D.rotate(120, true);
			
=======
			Motor.A.rotate(120, true);									//Motor draait elke keer 120 graden van een hele omwenteling (360)
			Motor.D.rotate(120, true);									//en activeert de sensor (en evt neutron cannon)

>>>>>>> 1a4611fe0d732b5027c9d24e246ce6d220c4127b
				currentSample = sensor.getColorID(); 	// Haalt gescande kleur op.
				sampleLijst.add(0, currentSample); 		// Stopt gescande kleur sampleLijst Array
				
				display.scroll();						//speelt de goede toon en laat hem op scherm zien
				display.drawString(NOOT_NAAMKLEUR[currentSample], 0, 3);
				Sound.playTone(NOOT_FREQ[currentSample], NOOT_LENGTE_LEES);
			}
		
		sensor.close();							// na de methode stopt de scanner en stoppen de motoren.
<<<<<<< HEAD
				
=======
	
>>>>>>> 1a4611fe0d732b5027c9d24e246ce6d220c4127b
		return;
	}
	
	public void speelMuziek() {
		display.clear();
		display.drawString("Nog een keer!", 2, 0);
		display.drawString("druk op een toets", 0 , 1);
		Button.waitForAnyPress();
		display.drawString("Speelt af...", 1, 3);
		
		for (int index = 0 ; index < sampleLijst.size() ; index ++) {
			display.scroll();						//speelt de goede toon en laat hem op scherm zien
			display.drawString(NOOT_NAAMKLEUR[currentSample], 0, 3);
			Sound.playTone(NOOT_FREQ[currentSample], NOOT_LENGTE_SPEEL);	
		}
		
		display.drawString("Ta da!", 7, 5);
		display.drawString("druk op een toets", 0, 1);		
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


//switch (currentSample) {				// Kiest op basis van de gescande kleur een switch.
//case 0: // Rood	 						// Rood geeft altijd het cijfer 0.
//	Sound.playTone(NOOT_A, NOOT_LENGTE);// Frequentie & Duur, wij hebben hier dus zelf de toon A van gemaakt, namelijk frequentie 440 ingesteld.
//	break;								// Speelt 1x kleur af, daarna break.
//case 1: // Groen 
//	Sound.playTone(NOOT_D, NOOT_LENGTE);
//	break; 
//case 2: // Blauw 
//	Sound.playTone(NOOT_E, NOOT_LENGTE);
//	break;
//case 3: // Geel 
//	Sound.playTone(NOOT_F, NOOT_LENGTE);
//	break;
//case 6: // Wit (toon G)
//	Sound.playTone(NOOT_G, NOOT_LENGTE);
//	break;
//case 7: // Zwart (Geen toon)
//	Delay.msDelay(NOOT_LENGTE - ZWART_CORR);
//	break;
