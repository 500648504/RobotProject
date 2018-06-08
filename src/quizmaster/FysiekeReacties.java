package quizmaster;

import lejos.hardware.motor.Motor;

/**
 * @author jvren
 *
 */

public class FysiekeReacties {
	
	private final static int SHAKESPEED = 100;
	private final static int SHAKEDISTANCE = 20;


	public void schudHoofd(int aantal) {
		Motor.B.setSpeed(SHAKESPEED);
		
		for (int draai = 0 ; draai < aantal ; aantal++) {
		
		Motor.B.resetTachoCount();
		
		while (Motor.B.getTachoCount() < SHAKEDISTANCE ) {
			Motor.B.forward();
		}
		Motor.B.resetTachoCount();
		
		while (Motor.B.getTachoCount() < SHAKEDISTANCE )
			Motor.B.backward();
		}
		
	}
	
	
	
	
}
