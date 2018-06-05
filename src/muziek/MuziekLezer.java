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
	EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S3);
	
	SampleProvider kleurLezer = sensor.getColorIDMode();
	//SampleProvider reflectedLight = new autoAdjustFilter(kleurlezer);
	
	int sampleteller = 0;
	ArrayList <Integer> sampleLijst = new ArrayList<>(); 
	    while (Button.ESCAPE.isUp()) {
	      sampleteller ++;
	    	/*
	       * Get a fresh sample from the filter. (The filter gets it from its
	       * source, the kleurLezer)
	       */
	      kleurLezer.fetchSample(sample, 0);
	      int currentSample = (int)sample[0];
	      sampleLijst.add(currentSample);
	      System.out.println(currentSample);
	      
	      
	      
	      
	      /* Display individual values in the sample. */	      
//	      for (int i = 0; i < sample.length; i++) {
//	        System.out.println(sample[i] + " ");
//	        
//	        
//	      }
	      
	      Delay.msDelay(1000);
	    }
	    /* When ready close the sensor */
	    sensor.close();
	    return sampleLijst;

	}
}


