package muziek;

import java.util.ArrayList;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class MuziekLezer {

	private Brick brick;
	float[] sample;

	public MuziekLezer() {
	
	}
	
	public MuziekLezer(Brick brick) {
		this.brick = brick;
	}
	
	public void testRun() {
		return;
	}

	public ArrayList<Integer> leesMuziek() {
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);

		int currentSample;
		int sampleteller = 0;
		
		ArrayList<Integer> sampleLijst = new ArrayList<>();

		while (Button.ESCAPE.isUp()) {
			currentSample = sensor.getColorID();
			System.out.println("test " + sampleteller + " " + sensor.getColorID());
			sampleLijst.add(currentSample);
			
		

		
		sampleteller++;
		Delay.msDelay(1000);
		}
		// When ready close the sensor */
		sensor.close();
		return sampleLijst;

	}
}
