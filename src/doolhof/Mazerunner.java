package doolhof;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

/**
 * @author jvren
 * expirementje voor een theremin muziek instrument wat gebruikt maakt van de IR sensor (ogen).
 * Hoe verder weg hoe lager de toon. De linker druk sensor regelt het volume: ingedrukt houden
 * maakt de toon luider, loslaten laat hem langzaam weer in volume zakken.
 */
public class Mazerunner {

	private final int[] NOTE_FREQ = new int[] { 20000, 523, 494, 440, 391, 349, 329, 293, 261, 20 };
	private final String[] NOTE_NAME = new String[] { "too close", "C", "B", "A", "G", "F", "E", "D", "C", "too far" };
	private final int[] NOTE_DIST_TARGET = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 100 };
	private final int NOTE_LENGTH = 100;

	public void runMaze() {
		//EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3);
		// SensorMode distanceMode = irSensor.getDistanceMode();

		float[] sampleIR = new float[1];
		float[] sampleTouch = new float[1];
		int currentVolume = 0;

		while (Button.ESCAPE.isUp()) {

			//touch.fetchSample(sampleTouch, 0);
			irSensor.fetchSample(sampleIR, 0);

			if (sampleTouch[0] == 1) {
				currentVolume += 2;
			}

			System.out.println("Distance: " + sampleIR[0]);
			System.out.println("Volume: " + currentVolume);

			Sound.playTone(getNoteFromDistance(sampleIR[0]), NOTE_LENGTH, currentVolume);

			Delay.msDelay(NOTE_LENGTH);

			currentVolume -= 1;

			if (currentVolume < 0) {
				currentVolume = 0;
			}
		}

		//touch.close();
		irSensor.close();
		System.out.println("STOP");
		Button.waitForAnyPress();
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
		System.out.println("Noot: " + currentTone);
		return currentFrequency;
	}

}

// float rangeSampler;
// rangeSampler = irSensor.getDistanceMode();
// lastRange = new float[rangeSampler.sampleSize()];
