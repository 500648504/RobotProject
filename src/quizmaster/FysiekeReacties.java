package quizmaster;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
 * @author jvren
 *
 */

public class FysiekeReacties {

	private final static int SHAKEDISTANCE = 50;

	public void schudHoofd(int aantal, int shakeSpeed) {
		System.out.println("SchudHoofd");

		aantal = 1;			//test waarde
		shakeSpeed = 200;	//idem
		
		Motor.B.setSpeed(shakeSpeed);

		for (int draai = 0; draai < aantal; draai++) {
			System.out.println(aantal);
			Delay.msDelay(2000);
			Motor.B.resetTachoCount();
			System.out.println("Tacho voor begin: " + Motor.B.getTachoCount());

			Motor.B.forward();
			while (Motor.B.getTachoCount() < SHAKEDISTANCE) {
				System.out.println("Tacho heen: " + Motor.B.getTachoCount());
			}
			//Motor.B.stop();
			Motor.B.resetTachoCount();

			Motor.B.backward();
			while (-Motor.B.getTachoCount() < (SHAKEDISTANCE * 2)) {
				System.out.println("Tacho terug: " + -Motor.B.getTachoCount());
			}
			//Motor.B.stop();
			Motor.B.resetTachoCount();

			while (Motor.B.getTachoCount() < SHAKEDISTANCE) {
			Motor.B.forward();
			System.out.println("Tacho weer heen: " + Motor.B.getTachoCount());
			}
			//Motor.B.stop();
			
		}	
	Motor.B.stop(true);
	
	System.out.println("Druk op een toets");
	Button.waitForAnyPress();
	//}
	
	// nu even zelfde methode voor testen public void wiggleWiggle(int aantal, int wiggleSpeed) {
		
		aantal = 2;		//test waarden
		int wiggleSpeed = 250;
		
		Motor.B.setSpeed(wiggleSpeed);
	}
	
	

}
