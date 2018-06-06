package lijnvolgen;

public class PIDController { 

	final float TARGET = Robot.COLOUR_VALUES[2];
	final float P_CONTROL = 265;	//was 350
	final float I_CONTROL = 30;		//was 30
	final float D_CONTROL = 567;	//was 500
	final float BASE_SPEED = 200; 	//was 250
	float leftSpeed, rightSpeed; 
	float sensorData;
	float integral = 0;
	// float[] lastErrs = {0, 0, 0};
	float lastErr = 0; 
	float deriv = 0; 

	

	public void run() {
		sensorData = Robot.pollSensor(true);
		float err = TARGET - sensorData;
		integral *= 0.98; 
		integral += err;
		deriv = err - lastErr; 
		lastErr = err; 

//		lastErrs[2] = lastErrs[1]; 
//		lastErrs[1] = lastErrs[0]; 
//		lastErrs[0] = err; 
//		deriv = (float) (11.0 / 6 * err - 3 * lastErrs[0] + 3.0 / 2 * lastErrs[1] - 1.0 / 3 * lastErrs[2]);

		leftSpeed = BASE_SPEED + P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv; 
		rightSpeed = BASE_SPEED - (P_CONTROL * err + I_CONTROL * integral + D_CONTROL * deriv);
		Robot.drive(leftSpeed, rightSpeed);
	}
}