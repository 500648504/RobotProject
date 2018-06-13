package theremin;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

/**
 * @author jvren 
 * 		   Een theremin muziekinstrument dat gebruikt maakt van de IR sensor (ogen).
 *         Hoe dichter bij de ogen je je hand houdt, hoe lager de toon. 
 *         De linker druksensor regelt het volume: ingedrukt houden maakt de toon luider, 
 *         loslaten laat het volume langzaam weer zakken, zodat er wat expressie in de tonen zit. 
 *         De lengte van de toon kan tijdens het spelen aangepast worden met de knoppen op de EV3 
 *         (instructie op display). Langere tonen (boven de 200ms) zorgen voor wat muzikalere melodie, 
 *         terwijl kortere tonen (onder de 100ms) meer aan het continu geluid van een Theremin doen denken, 
 *         ondanks de stotter die er dan in komt.
 */
public class ThereminSpeler {

	// *Majeur toonladder
	private final int[] NOTE_FREQ = new int[] 								// array met frequenties,
				{ 20000, 523, 494, 440, 391, 349, 329, 293, 261, 20000 };
	private final String[] NOTE_NAME = new String[] 						// array met bijpassende notennamen
				{ "too close", "C", "B", "A", "G", "F", "E", "D", "C", "too far" };
	private final int[] NOTE_DIST_TARGET = new int[] 						// array met afstanden tot sensor per noot
				{ 5, 10, 15, 20, 25, 30, 35, 40, 45, 100 };

	private final int NOTE_LENGTH_HIGHLOW_LIMIT = 100;						// boven/onder deze grens wordt nootlengte anders
																			// aangepast, voor meer controle
	private final int NOTE_LENGTH_CHANGE_HIGH = 50;							// verandering van nootlengte boven grens 
	private final int NOTE_LENGTH_CHANGE_LOW = 10;							// verandering van nootlengte onder grens
	private final int NOTE_LENGTH_MAX = 1000;								// maximum nootlengte in ms
	private final int NOTE_LENGTH_MIN = 10;									// minimum nootlengte in ms
	private final int NOTE_VOLUME_CHANGE_UP = 10;							// waarde waarmee het volume wordt verhoogd (user input)
	private final int NOTE_VOLUME_CHANGE_DOWN = 5;							// waarde waarmee het volume wordt verlaagd (automatisch)
	private final int NOTE_VOLUME_MAX = 100;								// maximum volume
	private final int NOTE_VOLUME_MIN = 0;									// minimum volume
	private final int RETURN_DELAY = 3000;									// delay voor teruggaan naar hoofdmenu na stop programma

	private int noteLength = 250; 											// initiële lengte noten
	private float[] sampleIR = new float[1]; 								// resultaat variabele voor IR sensor
	private float[] sampleTouch = new float[1]; 							// resultaat variabele voor touch sensor
	private int noteVolume = 0; 											// initiëel volume
	private TextLCD display; 												// aansturen van het LCD display

	public ThereminSpeler(Brick brick) { 									// Constructor, met de 'brick' waaruit het LCD
		this.display = brick.getTextLCD(); 									// wordt gehaald
	}

	// hoofdmethode voor het bespelen van de Theremin
	public void runMaze() { 												
		EV3TouchSensor touchL = new EV3TouchSensor(SensorPort.S1); 			// sensors toewijzen: linker touchsensor
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3); 				// en IR sensor
		irSensor.getDistanceMode(); 										// IR sensor in Distancemode zetten

		display.clear(); 													// display schoonvegen en instructies op scherm
		display.drawString("Escape stopt", 0, 0);
		display.drawString("<korter - langer>", 0, 1);

		while (Button.ESCAPE.isUp()) { 										// loop voor continu afspelen
																			// gaat door tot escape toets wordt ingedrukt.
			touchL.fetchSample(sampleTouch, 0); 							// neem sample van linker touchsensor
			irSensor.fetchSample(sampleIR, 0); 								// neem sample van IR sensor

			if (sampleTouch[0] == 1) { 										// als de touchsensor wordt ingedrukt
				noteVolume = noteVolumeUp(noteVolume); 						// verhoog volume met methode
			}

			if (Button.LEFT.isDown()) { 									// als de left toets wordt ingedrukt
				noteLength = noteLengthDown(noteLength); 					// maak de nootlengte korter met methode
			}
			if (Button.RIGHT.isDown()) { 									// als de right toets wordt ingedrukt
				noteLength = noteLengthUp(noteLength); 						// maak de nootlengte langer met methode
			}
																			// strings bouwen voor feedback op LCD
			String displayDistance = "Distance:" + (double) sampleIR[0];
			String displayVolume = "Volume  :" + noteVolume;
			String displayLength = "Lengte  :" + noteLength;
			display.clear(3); 												// display regels schoonmaken
			display.clear(4);
			display.clear(5);
			display.drawString(displayDistance, 0, 3); 						// gemaakte strings weergeven, elk
			display.drawString(displayVolume, 0, 4); 						// op een eigen regel
			display.drawString(displayLength, 0, 5);

			// hier wordt de daadwerkelijke toon afgespeeld. De frequentie van de toon wordt bepaald
			// volgens de methode getNoteFromDistance, de lengte en het volume zijn hierboven bepaald
			Sound.playTone(getNoteFromDistance(sampleIR[0]), noteLength, noteVolume);

			noteVolume = noteVolumeDown(noteVolume); 						// na elke toon wordt het volume ook weer
																			// langzaam verlaagd met een methode
		}																	// einde while-loop

		touchL.close(); 													// als de while loop gestopt wordt door de
		irSensor.close(); 													// escapetoets worden de sensors afgesloten,
		display.clear(7);													// het scherm schoongemaakt
		display.drawString("STOP...menu->", 0, 7);							// en aangegeven dat er gestopt is en na
		Delay.msDelay(RETURN_DELAY);										// korte pauze gaat het programma terug naar
	}																		// het hoofdmenu

	// methode voor het korter maken van de lengte van de toon
	public int noteLengthDown(int noteLength) {
		if (noteLength > NOTE_LENGTH_HIGHLOW_LIMIT) {						// boven een bepaalde grens wordt de toon
			return noteLength -= NOTE_LENGTH_CHANGE_HIGH;					// met een grotere waarde verkort dan daaronder.
		} else {															// die waarde wordt meteen terug gegeven
			noteLength -= NOTE_LENGTH_CHANGE_LOW;
			if (noteLength < NOTE_LENGTH_MIN) {								// onder bovenstaande grens ook controleren
				noteLength = NOTE_LENGTH_MIN;								// of de waarde niet onder minimum komt
			}
			return noteLength;												// na verlagen en check teruggeven nieuwe waarde
		}
	}

	// methode voor het langer maken van de lengte van de toon
	public int noteLengthUp(int noteLength) {								// boven een bepaalde grens wordt de toon
		if (noteLength > NOTE_LENGTH_HIGHLOW_LIMIT) {						// met een grotere waarde verlengd dan daaronder.
			noteLength += NOTE_LENGTH_CHANGE_HIGH;							
			if (noteLength > NOTE_LENGTH_MAX) {								// boven bovenstaande grens ook controleren
				noteLength = NOTE_LENGTH_MAX;								// of de waarde niet boven maximum komt
			}
			return noteLength;												// na verhogen en check teruggeven nieuwe waarde
		} else {
			return noteLength += NOTE_LENGTH_CHANGE_LOW;					// indien onder de grens gewoon verhogen
		}																	// en meteen teruggeven
	}

	// methode voor het verhogen van het volume
	public int noteVolumeUp(int noteVolume) {
		noteVolume += NOTE_VOLUME_CHANGE_UP;								// verhogen van het volume
		if (noteVolume > NOTE_VOLUME_MAX) {									// controleren of volume niet boven maximum komt
			noteVolume = NOTE_VOLUME_MAX;
		}
		return noteVolume;													// teruggeven nieuwe volume waarde
	}

	// methode voor het (automatisch) verlagen van het volume
	public int noteVolumeDown(int noteVolume) {
		noteVolume -= NOTE_VOLUME_CHANGE_DOWN;								// verlagen van het volume
		if (noteVolume < NOTE_VOLUME_MIN) {									// controleren of volume niet onder minimum komt
			noteVolume = NOTE_VOLUME_MIN;
		}
		return noteVolume;													// teruggeven nieuwe volume waarde
	}

	// methode om te bepalen welke noot wordt gespeeld aan de hand van de meting van de IR sensor
	// krijgt de gemeten afstand als variabele, geeft een frequentie in Hz terug.
	public int getNoteFromDistance(float currentSample) {					 
		int currentFrequency = 0;											// waarde voor frequentie
		String currentTone = "";											// string voor weergeven noot op LCD

		for (int index = 0; index < NOTE_DIST_TARGET.length; index++) {		// in deze loop wordt gekeken tussen welke
																			// target-distances in de array de meting valt
			if (currentSample <= NOTE_DIST_TARGET[index]) {					// en haalt dan de bijpassende frequentie en
				currentFrequency = NOTE_FREQ[index];						// noot naam op uit de frequentie array en naam
				currentTone = NOTE_NAME[index];								// arrays.
				index = NOTE_FREQ.length;									// als deze waarde bepaald is wordt de index-waarde
			}																// voor de for-loop op max gezet zodat de loop
		}																	// afgebroken wordt.
		display.clear(6);													// De schermregel voor de nootnaam wordt 
		String displayString = "Noot:   " + currentTone;					// leeggemaakt, en er wordt een string opgebouwd
		display.drawString(displayString, 0, 6);							// met de nootnaam en meteen weergegeven.
		return currentFrequency;											// hier wordt de gevonden frequentie teruggestuurd
	}

}


////*Bluestoonladder - deze wordt niet meer gebruikt, maar kan als alternatief gebruikt worden voor de majeur toonladder
//private final int[] NOTE_FREQ = new int[]
//			{ 20000, 523, 466, 392, 370, 349, 311, 261, 20000 };
//private final String[] NOTE_NAME = new String[]
//			{ "too close", "C", "Bb", "G", "F#", "F", "Eb", "C", "too far" };
//private final int[] NOTE_DIST_TARGET = new int[]
//	 		{ 10, 15, 20, 25, 30, 35, 40, 45, 100 };


