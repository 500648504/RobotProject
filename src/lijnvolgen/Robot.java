package lijnvolgen;

import lejos.hardware.motor.Motor;


class Robot {

	
	// Een robot kan rijden, het verschil in snelheid tussen de twee motoren
	// bepaalt of hij links of rechts gaat.
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

	
}