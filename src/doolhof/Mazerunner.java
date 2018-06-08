package doolhof;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Mazerunner {
	
	


	
	
	
	  
	  public void runMaze() {
		  EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3);
		  SensorMode distanceMode = irSensor.getDistanceMode();
		  
		  //int sampleSize = distanceMode.sampleSize();
		  float [] sampleIR = new float[1];
		  
		  while (Button.ESCAPE.isUp()) {
			   
			 
			   irSensor.fetchSample(sampleIR, 0);
				System.out.println("sample: " + sampleIR[0]);
				
				Delay.msDelay(100);
			}
		  irSensor.close();
		  System.out.println("Druk op een toets");
		  Button.waitForAnyPress();
		  
		  
		  
		  
		  EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);
			float [] sampleTouch = new float[1];
			System.out.println("wacht op druk");
			do  {
				   
				 
				   touch.fetchSample(sampleTouch, 0);
					
					
					Delay.msDelay(100);
				} while (sampleTouch[0] == 0);
				System.out.println("Ingedrukt");
			
			  touch.close();
			  System.out.println("Druk op een toets");
			  Button.waitForAnyPress();
			}
		}
		  
		  
		  
		 
		  
		  
		  
	
		  
		  
		  
//		  float rangeSampler;
//		  rangeSampler = irSensor.getDistanceMode();
		  //lastRange = new float[rangeSampler.sampleSize()];
		  


	  

