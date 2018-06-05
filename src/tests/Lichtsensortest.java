package tests;

//nog aan geklooid.... werkt nu niet meer Jorg

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.lcd.TextLCD;

public class Lichtsensortest {
	
		//deze methode heeft ook de brick nodig
		private Brick brick;
	
		//constructor voor de test
		public Lichtsensortest(Brick brick) {
			this.brick = brick;
		}


	    //methode zelf
		public void ambientLightTest () {
			
			//colorsensor naam geeft
	        EV3ColorSensor colorsensor = new EV3ColorSensor(SensorPort.S3);
	        SampleProvider colorsensorId = colorsensor.getColorIDMode();
	        float[] current_sample;

	        //dit stuk uit de code
	        System.out.println("Color Demo");
	        
	        
	        Button.LEDPattern(4);    // flash green led and
	        Sound.beepSequenceUp();    // make sound when ready.

	        Button.waitForAnyPress();
	        Button.LEDPattern(0);
	        
	        
	        // run until escape button pressed.	    	        
	        while (Button.ESCAPE.isUp())		//loop zolang escape niet ingedrukt is
	        {
	            colorsensorId.fetchSample(current_sample, 0);
	        	//LCD.clear(4);					//maak leeg, nog even precies uitzoeken?
	            System.out.println("test " + current_sample.getColorID());
	            Delay.msDelay(250);				//wacht 250 ms om loop niet te snel te maken
	        }

	        Delay.msDelay(1000);
		}
}
