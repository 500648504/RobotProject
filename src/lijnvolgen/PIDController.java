package lijnvolgen;

import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

/**
 * @author Robot Groep E
 * Deze klasse laat onze robot een zwarte lijn volgen met een kleurensensor. Deze kleurensensor, ingesteld op rood licht,
 * is ingesteld om aan de rechter kant van een zwarte lijn te blijven. Zodra er te veel wit of zwart naar de sensor wordt gestuurd - oftewel hij staat niet meer goed 
 * in het midden van de lijn, stuurt de robot bij om weer op de juiste zwart/wit waarde uit te komen. Een zogenoemde PID controller helpt hiermee. 
 * De waarde van de 'redsample' die genomen wordt, wordt in beeld getoond zodat er bij afwijkingen makkelijker mee kan worden gewerkt.
 */

public class PIDController {

	// Variabelen
	public EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	final float TARGET = 0.32f; // originele waarde was 0.32f, 0.35f was beter een grijst-tint, geen zwart (om
								// over gat te komen)
	final float P_CONTROL = 265; // P = Proportionele regelaar - Hoeveel heb je nodig om bij de gewenste waarde
									// te komen
	final float I_CONTROL = 30; // I = Integrator. Kijkt naar duur/tijd en groote van afwijking ten opzichte van
								// de lijn.
	final float D_CONTROL = 567; // D = Differantiator. Kijkt niet naar de afwijking zelf, maar naar hoe snel
									// deze groeit.
	final float BASE_SPEED = 240;  //De snelheid van onze robot
	float leftSpeed, rightSpeed;
	float sensorData;
	float integral = 0;
	float lastErr = 0;
	float deriv = 0;
//	final float MAX_AFWIJKING_NAAR_BOVEN = 1.025f;
//	final float MAX_AFWIJKING_NAAR_BENEDEN = 0.955f;

	
	public void run() {
		// De sensor meet een waarde
		sensorData = pollSensor(true);

		// Corrigeren koers om op de lijn te blijven, met een maximum afwijking van 2 %
		float err = TARGET - sensorData;
		integral *= 0.98;
		integral += err;
		deriv = err - lastErr;
		lastErr = err;

		leftSpeed = BASE_SPEED + P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv;
		rightSpeed = BASE_SPEED - (P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv);
		Robot.drive(leftSpeed, rightSpeed);

	}
	
	// Zet de Brick klaar, zodat we de display kunnen gebruiken
	private Brick brick;
	private TextLCD display;
	
	// Lees met de sensor en krijg een kleurwaarde terug
	// Sensor staat in red modus
	public float pollSensor(boolean log) {
		int sampleSize = sensor.sampleSize();
		float[] redsample = new float[sampleSize];
		sensor.getRedMode().fetchSample(redsample, 0);

	if (log) {
			display.clear();
			display.drawString("Sensor: ", 0, 0);
			display.drawInt((int)redsample[0], 0, 1);
			
//			System.out.print("sensor: ");
//			System.out.println(redsample[0]);
		}
		return redsample[0];
	}
}