//package tests;
//
//import lejos.hardware.Button;
//import lejos.hardware.Sound;
//import lejos.hardware.lcd.LCD;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3TouchSensor;
//import lejos.utility.Delay;
//
//
///**
// * A simple touch sensor test program.
// *
// */
//
//public class TouchSensorTest {
//	public static void main(String[] args) throws Exception {
//		
//		
//		
//		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);
//		float [] sample = new float[1];
//		System.out.println("wacht op druk");
//		do  {
//			   
//			 
//			   touch.fetchSample(sample, 0);
//				
//				
//				Delay.msDelay(100);
//			} while (sample[0] == 0);
//			System.out.println("Ingedrukt");
//		
//		  touch.close();
//		  System.out.println("Druk op een toets");
//		  Button.waitForAnyPress();
//		}
//		  
//		  
//		}
//		
//		
//}
//
