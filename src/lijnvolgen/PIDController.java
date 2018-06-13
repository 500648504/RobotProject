package lijnvolgen;

import lejos.hardware.Brick;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

/**
 * @author Robot Groep E Deze klasse laat onze robot een zwarte lijn volgen met
 *         een kleurensensor. Deze kleurensensor, ingesteld op rood licht, is
 *         ingesteld om aan de rechter kant van een zwarte lijn te blijven.
 *         Zodra er te veel wit of zwart naar de sensor wordt gestuurd - oftewel
 *         hij staat niet meer goed in het midden van de lijn, stuurt de robot
 *         bij om weer op de juiste zwart/wit waarde uit te komen. Een
 *         zogenoemde PID controller helpt hiermee. De waarde van de 'redsample'
 *         die genomen wordt, wordt in beeld getoond zodat er bij afwijkingen
 *         makkelijker mee kan worden gewerkt.
 */
public class PIDController {

	// Variabelen
	public EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	private final float TARGET = 0.32f; 	// Dit is het doel van de sensor: 0.32 is wat de sensor meet op de 'Sweet spot' op de lijn
	private final float P_CONTROL = 300; 	// P = Proportionele regelaar - Hoeveel heb je nodig om bij de gewenste waarde
											// te komen. In lekentaal: heden 
	private final float I_CONTROL = 30; 	// I = Integrator. Kijkt naar duur/tijd en groote van afwijking ten opzichte van
											// de lijn. Ook wel: verleden
	private final float D_CONTROL = 500; 	// D = Differantiator. Kijkt niet naar de afwijking zelf, maar naar hoe snel
											// deze groeit. Ofwel: de toekomst
	private final float BASE_SPEED = 250; 	// De snelheid van onze robot
	private float leftSpeed, rightSpeed; 	// De snelheid van de linker en de rechter motor
	private float sensorData; 				// De output van de sensor
	private float integral = 0; 			// Voor het berekenen van totalen, in dit geval de totale verandering van de
											// gegeven grootheid
	private float lastErr = 0; 				// De gemeten afwijking, de laatste gemeten en opgeslagen error
	private float deriv = 0; 				// De variable voor het opslaan van het verschil tussen de afwijking en de
											// laatst gemeten afwijking	
	
	public void run() {
		// De sensor meet een waarde
		sensorData = pollSensor(true);

		// Corrigeren koers om op de lijn te blijven. De error wordt gemeten en via
		// onderstaande berekening bijgestuurd naar de motoren. Links gaat minder snel rijden, rechts sneller.
		float err = TARGET - sensorData;
		integral += err;
		integral *= 0.98;		
		deriv = err - lastErr;
		lastErr = err;

		leftSpeed = BASE_SPEED + P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv;
		rightSpeed = BASE_SPEED - (P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv);
		Drive.drive(leftSpeed, rightSpeed);
	}

	// Lees met de sensor en krijg een kleurwaarde terug
	// Sensor staat in red modus
	public float pollSensor(boolean log) {
		
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