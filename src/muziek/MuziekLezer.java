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
	
	private final int LEESSNELHEID = 100;
	private ArrayList<Integer> sampleLijst = new ArrayList<>();

	public MuziekLezer() {
	
	}
	
//	public MuziekLezer(Brick brick) {
//		this.brick = brick;
//	}
	
	public void testRun() {
	File testwav1 = new File("test.wav");
	File testwav2 = new File("test8u2.wav");
		System.out.println("Speel sample 1");
		Sound.playSample(testwav1, 200);
		System.out.println("Druk op een toets");
		System.out.println("Speel sample 2");
		Sound.playSample(testwav2, 200);
		System.out.println("Druk op een toets");
		Button.waitForAnyPress();
		
		
		//leesMuziek();
		//System.out.println(sampleLijst.toString());
	}

	public void leesMuziek() {
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
					//sensor.getRedMode();
					//int sampleSize = sensor.sampleSize();
				
			//int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB - samplesize opvragen?
			//float[] sample = new float[sampleSize];

		int currentSample;
		int sampleteller = 0;

//		Motor.B.setSpeed(LEESSNELHEID);
//		Motor.C.setSpeed(LEESSNELHEID);
//		Motor.B.forward();
//		Motor.C.forward();
		
		

		while (Button.ESCAPE.isUp()) {
			currentSample = sensor.getColorID();
			System.out.println("test " + sampleteller + " " + sensor.getColorID());
			sampleLijst.add(currentSample);
			
			//sensor.getRGBMode().fetchSample(sample, 0);
			//currentSample = (int)sample[0];
			//			System.out.println("test " + sampleteller + " " + currentSample);
			//			sampleLijst.add(currentSample);
			
		sampleteller++;
		Delay.msDelay(1000);
		
		}
		
		
		
		// When ready close the sensor */
		sensor.close();
//		Motor.B.stop();
//		Motor.C.stop();
		return;

	}
}
