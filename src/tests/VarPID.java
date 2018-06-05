package tests;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import tests.*;

public class VarPID {

		final float TARGET = Robot.COLOUR_VALUES[2];
		float[] P_CONTROL = {265,265};
		float[] I_CONTROL = {30,30};
		float[] D_CONTROL = {567,567};
		float[] BASE_SPEED = {200,320}; 
		float leftSpeed, rightSpeed; 
		float sensorData;
		float integral = 0;
		float[] lastDerivs = {0, 0, 0};
		float lastErr = 0; 
		float deriv = 0;
		int mode = 1;
		int incrementor = 0;
		

		public void set() {
			Robot.drive(0, 0);
			mode = 1;
			integral = 0;
			lastErr = 0;
			deriv = 0;
			lastDerivs[0]=0;
			lastDerivs[1]=0;
			lastDerivs[2]=0;
			int sel = 0;


			while(Button.ENTER.isUp()){
				if(Button.RIGHT.isDown()) sel++;
				if(Button.LEFT.isDown())  sel--;


				if (Button.UP.isDown() && !Button.DOWN.isDown()) 
					incrementor = 1 + incrementor; 
				else if (!Button.UP.isDown() && Button.DOWN.isDown()) 
					incrementor = -1+incrementor;
				else 
					incrementor = 0; 
				sel %= 4; 

				switch (sel) {
					case 0:
						P_CONTROL[mode] += incrementor; 
//						if(Button.UP.isDown()) P_CONTROL[1]+=1;
//						if(Button.DOWN.isDown()) P_CONTROL[1]-=1;
						System.out.print("P: ");
						System.out.println(P_CONTROL[mode]);
						break; 

					case 1:
						I_CONTROL[mode] += incrementor; 
//						if(Button.UP.isDown()) I_CONTROL[1]+=1;
//						if(Button.DOWN.isDown()) I_CONTROL[1]-=1;
						System.out.print("I: ");
						System.out.println(I_CONTROL[mode]);
						break; 

					case(2):
						D_CONTROL[mode] += incrementor; 
//						if(Button.UP.isDown()) D_CONTROL[1]+=1;
//						if(Button.DOWN.isDown()) D_CONTROL[1]-=1;
						System.out.print("D: ");
						System.out.println(D_CONTROL[mode]);
						break; 

					case 3:
						BASE_SPEED[mode] += incrementor; 
//						if(Button.UP.isDown()) BASE_SPEED[1]+=1;
//						if(Button.DOWN.isDown()) BASE_SPEED[1]-=1;
						System.out.print("SPEED: ");
						System.out.println(BASE_SPEED[mode]);

						break; 

				}
			}
		}

		public void run() {
			sensorData = Robot.pollSensor(true);
			float err = TARGET - sensorData;


			integral *= 0.98; 
			integral += err;
			deriv = err - lastErr; 
			lastErr = err; 

			// 3 modes: straight			slight curve			heavy curve
			// 			0<=   				<=T1					<=T2						(collect thresholds emperically, project linear path on curve, find avgDeriv)
			//			Fast				medium speed			slow
			//			more I, low PD		more PD, decrease I		Normal PD, normal I
			//			want little wobble	
			// Use the single mode implementation + set() to get the 3 sets of control coefficients
			// Un comment the mode = 1 line to force the mode...

//			lastDerivs[2] = lastDerivs[1];
//			lastDerivs[1] = lastDerivs[0];
//			lastDerivs[0] = deriv;
//			float avgDeriv = (lastDerivs[0]+lastDerivs[1]+lastDerivs[2])/3;			// play with this line to change the sensitivity of mode switches
//			
//			System.out.println(avgDeriv + " " + mode);
//			// Controller switcher
//			if (Math.abs(avgDeriv)<=0.1)
//				mode = 1;
//			else
//				mode = 0;


			// Explanation: (1+err/Math.abs(err)) term
			//		Switches control on or off depending on sign.
			// 		This gaurantees that we do not slow down our selves.
			//		DFX: Speeeeeeddd. I am spppppeeeed.

			/* 
			leftSpeed = BASE_SPEED[mode] + P_CONTROL[mode] * err *(1+err/Math.abs(err)) + I_CONTROL[mode] * integral*(1+integral/Math.abs(integral)) + D_CONTROL[mode] * deriv*(1+deriv/Math.abs(deriv)); 
			rightSpeed = BASE_SPEED[mode] - (P_CONTROL[mode] * err *(1-err/Math.abs(err)) + I_CONTROL[mode] * integral*(1-integral/Math.abs(integral)) + D_CONTROL[mode] * deriv*(1-deriv/Math.abs(deriv)));
			*/ 
			leftSpeed = BASE_SPEED[mode] + P_CONTROL[mode] * err + I_CONTROL[mode] * integral + D_CONTROL[mode] * deriv; 
			rightSpeed = BASE_SPEED[mode] - (P_CONTROL[mode] * err + I_CONTROL[mode] * integral + D_CONTROL[mode] * deriv);
			Robot.drive(leftSpeed, rightSpeed);
		}
}
