package lijnvolgen;

import lejos.hardware.motor.Motor;


class Robot {

	
	// Een robot kan rijden, het verschil in snelheid tussen de twee motoren
	// bepaalt of hij links of rechts gaat.
	public static void drive(float l, float r) {
		// D-> to left A-> to right
		Motor.D.setSpeed(Math.abs(l));
		Motor.A.setSpeed(Math.abs(r));

		if (l > 0) {
			Motor.D.forward();
		} else if (l < 0) {
			Motor.D.backward();
		} else {
			Motor.D.stop(true);
		}

		if (r > 0) {
			Motor.A.forward();
		} else if (r < 0) {
			Motor.A.backward();
		} else {
			Motor.A.stop(true);
		}
	}

	
}