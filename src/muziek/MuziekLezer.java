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

public class MuziekLezer {
							
	private final int LEESSNELHEID = 300;				// Dit is hoe snel de robot rijdt.
	private final int SCAN_SNELHEID = 6;
	private final int MUZIEKVOLUME = 10;				//
	private final int NOOT_LENGTE = 400;
	private final int ZWART_CORR = 0;					// Als hij zwart leest speelt hij geen toon en scant snel teveel waarden. Een delay NOOT_LENGTE bleek teveel, vandaar een correctie
	private final int NOOT_D = 293;
	private final int NOOT_E = 329;
	private final int NOOT_F = 349;
	private final int NOOT_G = 391;
	private final int NOOT_A = 440;
	private final int KLEUR_ZWART = 7;						// 7 is zwart, nodig voor stopconditie
	
	private ArrayList<Integer> sampleLijst = new ArrayList<>();   // Arraylist, deze wordt gevuld met de kleurwaardes die gescand worden.
	int currentSample;										// De waarde van een lichtmeting (dit is een integer)
	
	
	// Constructor
	public MuziekLezer() {
	}
	
	public void leesMuziek() {	
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);		// Het object scanner wordt aangemaakt, de scanner kan nu gebruikt worden.
		Sound.setVolume(MUZIEKVOLUME);											// Het geluid wordt op volume 10 gezet
		Motor.A.setSpeed(LEESSNELHEID);									// Snelheid wordt geactiveerd
		Motor.D.setSpeed(LEESSNELHEID);
		//Motor.A.forward();												// De motoren gaan voorwaarts 
		//Motor.D.forward();
		Motor.A.resetTachoCount();										// Het aantal omwentelingen wordt gereset, start op 0.
		Motor.D.resetTachoCount();


		while (doorgaanMuziek()) {										// Zolang de methode doorgaan true is gaat het door.
			if (Button.ESCAPE.isDown()) {								// Bij escape indrukken: stoppen motoren
//				Motor.A.stop();											
//				Motor.D.stop();
				break;
			}
			Motor.A.rotate(120, true);
			Motor.D.rotate(120, true);
			//if (Motor.A.getTachoCount() % SCAN_SNELHEID == 0) { 	// Al de modulo van het aantal omwentelingen uitkomt op 0, dan: nieuwe scan. (modulo 6 = 0)
				// De kleuren Scanner gaat aan.
				currentSample = sensor.getColorID(); 	// Haalt gescande kleur op.
				sampleLijst.add(0, currentSample); 		// Stopt gescande kleur sampleLijst Array
				

				switch (currentSample) {				// Kiest op basis van de gescande kleur een switch.
				case 0: // Rood	 						// Rood geeft altijd het cijfer 0.
					Sound.playTone(NOOT_A, NOOT_LENGTE);// Frequentie & Duur, wij hebben hier dus zelf de toon A van gemaakt, namelijk frequentie 440 ingesteld.
					break;								// Speelt 1x kleur af, daarna break.
				case 1: // Groen 
					Sound.playTone(NOOT_D, NOOT_LENGTE);
					break; 
				case 2: // Blauw 
					Sound.playTone(NOOT_E, NOOT_LENGTE);
					break;
				case 3: // Geel 
					Sound.playTone(NOOT_F, NOOT_LENGTE);
					break;
				case 6: // Wit (toon G)
					Sound.playTone(NOOT_G, NOOT_LENGTE);
					break;
				case 7: // Zwart (Geen toon)
					Delay.msDelay(NOOT_LENGTE - ZWART_CORR);
					break;
				}
			Delay.msDelay(0);
			}
		//}
		sensor.close();							// na de methode stopt de scanner en stoppen de motoren.
		Motor.A.stop(true);
		Motor.D.stop(true);
	
		return;
	}

	public boolean doorgaanMuziek() { // drie keer zwart is stop
		if (sampleLijst.size() < 3) { // eerst controleren of er wel 3 zijn
			return true;
		}
		if (sampleLijst.get(0) == KLEUR_ZWART && sampleLijst.get(1) == KLEUR_ZWART && sampleLijst.get(2) == KLEUR_ZWART) {		// als arraynummer 0, 1 en 2 zwart zijn
			return false;																			// dan is doorgaan = false
		}
		return true;																				// niet 3x zwart, dan doorgaan = true
	}

}




// RobotLauncher.showMenu();
//
// int sampleteller = 0;
//
// while ( sensor.getColorID() != 7 ) { // Stopt bij zwart (7)
//
// if (vorigeKleur != huidigeKleur) {
// vorigeKleur = huidigeKleur;
//
// File groenGeluidObject = new File("test.wav");
// Sound.playSample(groenGeluidObject, 200); // Bestandsnaam & Volume
// Button.waitForAnyPress();
//
// System.out.println(sensor.getColorID());
// int huidigeKleur = sensor.getColorID();
//
// while (Motor.B.getTachoCount() <= 4000) { // Aantal omwentelingen
//
// boolean doorgaan = true;
// int vorigeKleur = -1;
//
// sensor.getRedMode();
// int sampleSize = sensor.sampleSize();
// int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB
// - samplesize opvragen?
// float[] sample = new float[sampleSize];
//
// Motor.B.resetTachoCount();
// Motor.C.resetTachoCount();
//
// sensor.getRedMode();
// int sampleSize = sensor.sampleSize();
// int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB
// - samplesize opvragen?
// float[] sample = new float[sampleSize];
//
// int currentSample;
// int sampleteller = 0;
//
// while (Motor.B.getTachoCount() <= 1000) { // Aantal omwentelingen
// if (Motor.B.getTachoCount() % 150 == 0) { // Als omwentelingen (modulo 150 =
// 0) / Kleuren Scannen:
//
// De kleuren Scanner gaat aan.
// Deze scant continue kleuren.
// currentSample = sensor.getColorID(); // Haalt gescande kleur op.
// System.out.println("sample " + sampleteller + " " + sensor.getColorID());
// System.out.println(Motor.B.getTachoCount());
// sampleLijst.add(currentSample); // Stopt gescande kleur sampleLijst Array
// (gebeurt niets mee)
// sampleteller++;
//
// int selection = 0;
// if (sensor.getColorID() == 1) selection = 1; // Groen
// if (sensor.getColorID() == 2) selection = 2; // Blauw
// if (sensor.getColorID() == 3) selection = 3; // Donker Geel
// if (sensor.getColorID() == 6) selection = 6; // Geel
// if (sensor.getColorID() == 7) selection = 7; // Zwart - Hierdoor ziet de
// robot een nieuwe kleur
//
// File testwav0 = new File("geluid0.wav"); // rood
// File testwav1 = new File("geluid1.wav"); // groen
// File testwav2 = new File("geluid2.wav"); // licht blauw
// File testwav3 = new File("geluid3.wav"); // donker geel
// File testwav4 = new File("geluid4.wav"); // onbekend, misschien bruin?
// File testwav5 = new File("geluid5.wav"); // onbekend, misschien wit?
// File testwav6 = new File("geluid6.wav"); // licht geel
// File testwav7 = new File("geluid7.wav"); // zwart
//
// if (sensor.getColorID() == 0) { // Kijkt naar de kleuren ID, als deze 0 is
// dan:
// Sound.playSample(testwav0, 200); // Speel af: bestand: testwav0, volume: 200
// }
// if (sensor.getColorID() == 1) {
// Sound.playSample(testwav1, 200);
// }
// if (sensor.getColorID() == 2) {
// Sound.playSample(testwav2, 200);
// }
// if (sensor.getColorID() == 3) {
// Sound.playSample(testwav3, 200);
// }
// if (sensor.getColorID() == 4) {
// Sound.playSample(testwav4, 200);
// }
// if (sensor.getColorID() == 5) {
// Sound.playSample(testwav5, 200);
// }
// if (sensor.getColorID() == 6) {
// Sound.playSample(testwav6, 200);
// }
// if (sensor.getColorID() == 7) {
// Sound.playSample(testwav7, 200);
// }
//
// }
// sensor.getRGBMode().fetchSample(sample, 0);
// currentSample = (int)sample[0];
// System.out.println("test " + sampleteller + " " + currentSample);
// sampleLijst.add(currentSample);
//
// When ready close the sensor */
