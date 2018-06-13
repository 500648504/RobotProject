package quizmaster;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

/**
 * Zowel Francois als DierenQuiz gebruiken de touch sensoren.
 * Functionaliteit om deze aan te sturen is in deze Class gezet.
 * Op onze LeJos EV3 robot zitten de sensoren op port S1 en S4.
 */

public class TouchSensor {
	
	// Variabelen
	private final static int ANTWOORD_LINKS = 1;
	private final static int ANTWOORD_RECHTS = 2;
	private EV3TouchSensor linksTouch;
	private EV3TouchSensor rechtsTouch;
	private final int SENSOR_DELAY = 3000;
	
	
	
	//Constructor
	public TouchSensor() {
		//System.out.println("sensoren aanmaken");
		linksTouch = new EV3TouchSensor(SensorPort.S1);
		rechtsTouch = new EV3TouchSensor(SensorPort.S4);		
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
			Delay.msDelay(SENSOR_DELAY);
			return ANTWOORD_LINKS;
		} else {
			Delay.msDelay(SENSOR_DELAY);
			return ANTWOORD_RECHTS;			
		}
	}
	
	
	// Afsluiten sensoren
	public void closeSensors() {
		linksTouch.close();
		rechtsTouch.close();
	}
	
	
	
	
	
	
}
