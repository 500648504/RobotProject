package lijnvolgen;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

class Robot {

	public static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	public static float[] COLOUR_VALUES = { 0.102f, 0.160f, 0.32f, 0.507f, 0.582f }; 
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
}