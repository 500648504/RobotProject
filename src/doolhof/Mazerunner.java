package doolhof;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Mazerunner {
	
	


	
	
	
	  
	  public void runMaze() {
		  EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3);
		  SensorMode distanceMode = irSensor.getDistanceMode();
		  
		  //int sampleSize = distanceMode.sampleSize();
		  float [] sample = new float[1];
		  
		  while (Button.ESCAPE.isUp()) {
			   
			 
			   irSensor.fetchSample(sample, 0);
				System.out.println("sample: " + sample[0]);
				
				Delay.msDelay(100);
			}
		  irSensor.close();
		  System.out.println("Druk op een toets");
		  Button.waitForAnyPress();
		}
		  
		  
		  
		 
		  
		  
		  
	
		  
		  
		  
//		  float rangeSampler;
//		  rangeSampler = irSensor.getDistanceMode();
		  //lastRange = new float[rangeSampler.sampleSize()];
		  


	  
}
