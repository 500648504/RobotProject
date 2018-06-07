package muziek;

import java.io.File;
import java.util.ArrayList;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class MuziekLezer {

	private Brick brick;
	
	private final int LEESSNELHEID = 800;
	private ArrayList<Integer> sampleLijst = new ArrayList<>();

	public MuziekLezer() {
	
	}
	
	public MuziekLezer(Brick brick) {
		this.brick = brick;
	}
	
	public void testRun() {
		
		
		
		
		
		
		leesMuziek();
		Button.waitForAnyPress();
//	System.out.println(sampleLijst.toString());
	}
	
	

	public void leesMuziek() {
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
		
					//sensor.getRedMode();
					//int sampleSize = sensor.sampleSize();
				
			//int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB - samplesize opvragen?
			//float[] sample = new float[sampleSize];

		int currentSample;
		int sampleteller = 0;

		Motor.B.setSpeed(LEESSNELHEID);
		Motor.C.setSpeed(LEESSNELHEID);
		Motor.B.forward();
		Motor.C.forward();
		
		while (Motor.B.getTachoCount() <= 1000) {
			
			
	

			if  (Motor.B.getTachoCount() % 150 == 0) {
				
			
			currentSample = sensor.getColorID();
			System.out.println("sample " + sampleteller + " " + sensor.getColorID());
			System.out.println(Motor.B.getTachoCount());
			sampleLijst.add(currentSample);
			sampleteller++;
			}
			//sensor.getRGBMode().fetchSample(sample, 0);
			//currentSample = (int)sample[0];
			//			System.out.println("test " + sampleteller + " " + currentSample);
			//			sampleLijst.add(currentSample);
			
			
		}
		
		
		
		// When ready close the sensor */
		
		sensor.close();
		
		Motor.B.stop(true);
		Motor.C.stop(true);
		return;

	}
}
