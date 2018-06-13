package lijnvolgen;

import lejos.hardware.motor.Motor;

/**
 * @author Robot groep E.
 * Dit is de klasse die gebruikt wordt om de robot te laten rijden. De robot functioneerd met twee motoren,
 * een links en een rechts. Gelijke snelheid is rechtdoor, afwijking naar een kant is draaien.
 */

class CorrectieRichting {
	
	//De methode om de robot te laten rijden en bijsturen
	public static void correctie(float links, float rechts) {
		// D-> to left A-> to right
		Motor.D.setSpeed(Math.abs(links));
		Motor.A.setSpeed(Math.abs(rechts));

		if (links > 0) {
			Motor.D.forward();
		} else if (links < 0) {
			Motor.D.backward();
		} else {
			Motor.D.stop(true);
		}

		if (rechts > 0) {
			Motor.A.forward();
		} else if (rechts < 0) {
			Motor.A.backward();
		} else {
			Motor.A.stop(true);
		}
	}
}