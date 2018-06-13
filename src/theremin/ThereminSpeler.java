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
 * @author jvren expirementje voor een theremin muziek instrument wat gebruikt
 *         maakt van de IR sensor (ogen). Hoe verder weg hoe lager de toon. De
 *         linker druk sensor regelt het volume: ingedrukt houden maakt de toon
 *         luider, loslaten laat hem langzaam weer in volume zakken.
 */
public class ThereminSpeler {

	// *Majeur toonladder
	private final int[] NOTE_FREQ = new int[] { 20000, 523, 494, 440, 391, 349, 329, 293, 261, 20000 };
	private final String[] NOTE_NAME = new String[] { "too close", "C", "B", "A", "G", "F", "E", "D", "C", "too far" };
	private final int[] NOTE_DIST_TARGET = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 100 };

	// //*Bluesladder
	// private final int[] NOTE_FREQ = new int[]
	// { 20000, 523, 466, 392, 370, 349, 311, 261, 20000 };
	// private final String[] NOTE_NAME = new String[]
	// { "too close", "C", "Bb", "G", "F#", "F", "Eb", "C", "too far" };
	// private final int[] NOTE_DIST_TARGET = new int[]
	// { 10, 15, 20, 25, 30, 35, 40, 45, 100 };

	private int noteLength = 250;
	private float[] sampleIR = new float[1];
	private float[] sampleTouch = new float[1];
	private int noteVolume = 0;
	private TextLCD display;

	public ThereminSpeler(Brick brick) {
		this.display = brick.getTextLCD();
	}

	public void runMaze() {
		EV3TouchSensor touchL = new EV3TouchSensor(SensorPort.S1);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3);
		irSensor.getDistanceMode();

		display.clear();
		display.drawString("Escape stopt", 0, 0);
		display.drawString("<korter - langer>", 0, 1);

		while (Button.ESCAPE.isUp()) {

			touchL.fetchSample(sampleTouch, 0);
			irSensor.fetchSample(sampleIR, 0);

			if (sampleTouch[0] == 1) {
				noteVolume = noteVolumeUp(noteVolume);
			}

			if (Button.LEFT.isDown()) {
				noteLength = noteLengthDown(noteLength);
			}
			if (Button.RIGHT.isDown()) {
				noteLength = noteLengthUp(noteLength);
			}

			String displayDistance = "Distance:" + (double) sampleIR[0];
			String displayVolume = "Volume  :" + noteVolume;
			String displayLength = "Lengte  :" + noteLength;
			display.clear(3);
			display.clear(4);
			display.clear(5);
			display.drawString(displayDistance, 0, 3);
			display.drawString(displayVolume, 0, 4);
			display.drawString(displayLength, 0, 5);

			Sound.playTone(getNoteFromDistance(sampleIR[0]), noteLength, noteVolume);

			noteVolume = noteVolumeDown(noteVolume);
		}

		touchL.close();
		irSensor.close();
		display.clear(7);
		display.drawString("STOP...menu->", 0, 7);
		Delay.msDelay(3000);
	}

	public int noteLengthDown(int noteLength) {
		if (noteLength > 100) {
			return noteLength -= 50;
		} else {
			noteLength -= 10;
			if (noteLength < 10) {
				noteLength = 10;
			}
			return noteLength;
		}
	}

	public int noteLengthUp(int noteLength) {
		if (noteLength > 100) {
			noteLength += 50;
			if (noteLength > 1000) {
				noteLength = 1000;
			}
			return noteLength;
		} else {
			return noteLength += 10;
		}
	}

	public int noteVolumeUp(int noteVolume) {
		noteVolume += 10;
		if (noteVolume > 100) {
			noteVolume = 100;
		}
		return noteVolume;
	}
	
	public int noteVolumeDown(int noteVolume) {
		noteVolume -= 5;
		if (noteVolume < 0) {
			noteVolume = 0;
		}
		return noteVolume;
	}

	public int getNoteFromDistance(float currentSample) {
		int currentFrequency = 0;
		String currentTone = "";

		for (int index = 0; index < NOTE_DIST_TARGET.length; index++) {

			if (currentSample <= NOTE_DIST_TARGET[index]) {
				currentFrequency = NOTE_FREQ[index];
				currentTone = NOTE_NAME[index];
				index = NOTE_FREQ.length;
			}
		}
		display.clear(6);
		String displayString = "Noot:   " + currentTone;
		display.drawString(displayString, 0, 6);
		return currentFrequency;
	}

}
