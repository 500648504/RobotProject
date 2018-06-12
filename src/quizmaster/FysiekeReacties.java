package quizmaster;

import lejos.hardware.motor.Motor;


/**
 * @author jvren
 * Class met methodes om verschillende fysieke reacties van de robot te kunnen doen als
 * reactie op de gebruikers input
 *
 */

public class FysiekeReacties {

	private final static String FORWARD = "forward";
	private final static String BACKWARD = "backward";
	private final static int STOP_SPEED = 0;

	private final static int SHAKE_DISTANCE = 45;
	private final static int SHAKE_SPEED = 200;

	private final static int WIGGLE_DISTANCE = 20;
	private final static int WIGGLE_SPEED = 1000;

	private final static int PIROUETTE_DISTANCE = 1250;
	private final static int PIROUETTE_SPEED = 400;

	private final static int SCARED_DISTANCE = 720;
	private final static int SCARED_SPEED = 1000;
	private final static int SCARED_SLOW_FACTOR = 4;

	// robot schudt zijn hoofd
	public void schudHoofd() {
		hoofdBeweging(SHAKE_DISTANCE, SHAKE_SPEED);
		hoofdBeweging(-(SHAKE_DISTANCE * 2), SHAKE_SPEED);
		hoofdBeweging(SHAKE_DISTANCE, SHAKE_SPEED);
		Motor.B.stop(true);
	}

	// robot draait heen en terug met zijn hele lijf
	public void wiggle() {
		rupsDraaiBeweging(WIGGLE_DISTANCE, WIGGLE_SPEED, FORWARD);
		rupsDraaiBeweging((WIGGLE_DISTANCE), WIGGLE_SPEED, BACKWARD);
		rupsDraaiBeweging((WIGGLE_DISTANCE), WIGGLE_SPEED, BACKWARD);
		rupsDraaiBeweging(WIGGLE_DISTANCE, WIGGLE_SPEED, FORWARD);
	}

	// robot draait om zijn as
	public void pirouette() {
		rupsDraaiBeweging(PIROUETTE_DISTANCE, PIROUETTE_SPEED, BACKWARD);
	}

	// robot 'schrikt' naar achteren en rijdt langzaam terug
	public void scared() {
		rupsRijBeweging(SCARED_DISTANCE, SCARED_SPEED, BACKWARD);
		rupsRijBeweging(SCARED_DISTANCE, (SCARED_SPEED / SCARED_SLOW_FACTOR), FORWARD);
	}

	// algemene methode voor hoofdbeweging (motor B)
	public void hoofdBeweging(int distance, int speed) {
		Motor.B.setSpeed(speed);
		Motor.B.rotate(distance);
		return;
	}

	// algemene methode voor draaibeweging met rupsbanden (A en D).
	public void rupsDraaiBeweging(int distance, int speed, String direction) {
		resetCountAD();
		setSpeedAD(speed);
		if (direction.equals(FORWARD)) { // bepalen linksom of rechtsom draaien
			while (Motor.A.getTachoCount() < distance) {
				Motor.A.forward();
				Motor.D.backward();
			}
		} else
			while (Motor.D.getTachoCount() < distance) {
				Motor.A.backward();
				Motor.D.forward();
			}
		setSpeedAD(STOP_SPEED);
	}

	// algemene methode voor rijbeweging met rupsbanden (A en D)
	public void rupsRijBeweging(int distance, int speed, String direction) {
		resetCountAD();
		setSpeedAD(speed);
		if (direction.equals(FORWARD)) {
			while (Motor.A.getTachoCount() < distance) {
				Motor.A.forward();
				Motor.D.forward();
			}
		} else
			while (-(Motor.A.getTachoCount()) < distance) {
				Motor.A.backward();
				Motor.D.backward();
			}
		setSpeedAD(STOP_SPEED);
	}

	// algemene methode om snelheid beide motors (A en D) in te stellen
	// en te stoppen als snelheid is 0
	public void setSpeedAD(int speed) {
		if (speed == STOP_SPEED) {
			Motor.A.stop(true);
			Motor.D.stop(true);
			return;
		}
		Motor.A.setSpeed(speed);
		Motor.D.setSpeed(speed);
	}

	// algemene methode om tellers beide motors (A en D) te resetten
	public void resetCountAD() {
		Motor.A.resetTachoCount();
		Motor.D.resetTachoCount();
	}

}
