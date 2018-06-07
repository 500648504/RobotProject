package lijnvolgen;

public class PIDController { 

	final float TARGET = Robot.COLOUR_VALUES[2];
	final float P_CONTROL = 265;	//P = Proportionele regelaar - Hoeveel heb je nodig om bij de gewenste waarde te komen
	final float I_CONTROL = 30;		//I = Integrator. Kijkt naar duur/tijd en groote van afwijking ten opzichte van de lijn.
	final float D_CONTROL = 567;	//D = Differantiator. Kijkt niet naar de afwijking zelf, maar naar hoe snel deze groeit.
	final float BASE_SPEED = 200; 	
	float leftSpeed, rightSpeed; 
	float sensorData;
	float integral = 0;
	float lastErr = 0; 
	float deriv = 0; 

	

	public void run() {
		sensorData = Robot.pollSensor(true);
		float err = TARGET - sensorData;
		integral *= 0.98; 
		integral += err;
		deriv = err - lastErr; 
		lastErr = err; 



		leftSpeed = BASE_SPEED + P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv; 
		rightSpeed = BASE_SPEED - (P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv);
		Robot.drive(leftSpeed, rightSpeed);
	}
}