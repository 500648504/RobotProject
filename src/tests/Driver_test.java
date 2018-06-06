package tests;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Driver_test {
	
	// Lijst van alle variable in deze klasse
	public static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);		// Kleurenscanner, aangemaakt & geimporeerd
	public static float[] COLOUR_VALUES = { 0.102f, 0.160f, 0.312f, 0.507f, 0.582f };	// Array met kleuren, midden is target
	
	final float TARGET = Robot.COLOUR_VALUES[2];			// Targetwaarde
	final float P_CONTROL = 350;
	final float I_CONTROL = 30;
	final float D_CONTROL = 500;
	final float BASE_SPEED = 250; 
	float leftSpeed, rightSpeed; 			// 
	float sensorData;
	float integral = 0;
	float lastErr = 0; 
	float deriv = 0; 
	// most black to most white 

	public static void drive(float l, float r) {
		// B-> to left C-> to right
		Motor.B.setSpeed(Math.abs(l));
		Motor.C.setSpeed(Math.abs(r));

		if (l > 0) {
			Motor.B.forward();
		} else if (l < 0) {
			Motor.B.backward();
		} else {
			Motor.B.stop(true);
		}

		if (r > 0) {
			Motor.C.forward();
		} else if (r < 0) {
			Motor.C.backward();
		} else {
			Motor.C.stop(true);
		}
	}

	public static float pollSensor(boolean log) {
		int sampleSize = sensor.sampleSize();
		float[] redsample = new float[sampleSize];
		sensor.getRedMode().fetchSample(redsample, 0);

		if (log) {
			System.out.print("sensor: ");
			System.out.println(redsample[0]);
		}
		return redsample[0];
	}

	public void run() {
		sensorData = Robot.pollSensor(true);
		float err = TARGET - sensorData;	//  float err = Aantal waarnemingen (ingesteld op 2) - Waarneming sensor (=sensordata)
		integral *= 0.98; 
		integral += err;
		deriv = err - lastErr; 
		lastErr = err; 
		leftSpeed = BASE_SPEED + P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv; 
		rightSpeed = BASE_SPEED - (P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv);
		Robot.drive(leftSpeed, rightSpeed);		// Actie: rijden (linkerband, rechterband)
	}
}