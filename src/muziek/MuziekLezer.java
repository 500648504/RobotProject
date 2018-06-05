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
	float []sample;
	
	
	public MuziekLezer (Brick brick) {
		this.brick = brick;
	}
	
	public ArrayList<Integer> leesMuziek() {
	EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	
	SampleProvider kleurLezer = sensor.getColorIDMode();
	SampleProvider kleurLezer = new autoAdjustFilter(kleurlezer);
	
	int sampleSize = reflectedLight.sampleSize();
    float[] sample = new float[sampleSize];
    
	int sampleteller = 0;
	ArrayList <Integer> sampleLijst = new ArrayList<>(); 
	    while (Button.ESCAPE.isUp()) {
	      
	    	/*
	       * Get a fresh sample from the filter. (The filter gets it from its
	       * source, the kleurLezer)
	       */
	      kleurLezer.fetchSample(sample, 0);
	      int currentSample = (Math.round(sample[sampleteller]));
	      sampleLijst.add(currentSample);
	      System.out.println(currentSample);
	      
	      
	      
	      
	      /* Display individual values in the sample. */	      
//	      for (int i = 0; i < sample.length; i++) {
//	        System.out.println(sample[i] + " ");
//	        
//	        
//	      }
	      sampleteller ++;
	      Delay.msDelay(1000);
	    }
	    /* When ready close the sensor */
	    sensor.close();
	    return sampleLijst;

	}
}


