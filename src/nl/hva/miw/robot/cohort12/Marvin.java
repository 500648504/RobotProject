package nl.hva.miw.robot.cohort12;

import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.utility.Delay;
import muziek.MuziekLezer;
import tests.*;

public class Marvin {
	
	//geeft lego ding een naam
	Brick brick;
	
	//nieuwe robot constructorlego ding
	public Marvin() {
		super();
		brick = LocalEV3.get();
	}
	
	//main method, nieuwe Marvin gedefinieerd en roept test aan
	public static void main(String[] args) {
		Marvin marvin = new Marvin();		
		
		marvin.lichttest();
		
		//Dit is een comment van niks
		
		
		// Code: Bobby (Object PIDController)
		PIDController pid = new PIDController(); 
		System.out.println("STARTING MAIN");
		Button.waitForAnyPress();
		while (true) {
			if (Button.ESCAPE.isDown())
				break;
			pid.run(); 
		}
		
		// Code: Bobby (Object VarPID)
		VarPID vpid = new VarPID(); 
		Button.waitForAnyPress();
		while (true) {
			if (Button.ESCAPE.isDown() && Button.ENTER.isDown())
				break;
			if (Button.ESCAPE.isDown()) 
				vpid.set();
			// bb.run();
			// p.run();
			// pi.run();
			// pid.run();
			vpid.run();
		}
	}
	
	
	public void muziek () {
		MuziekLezer muziekLezer = new MuziekLezer (brick) ;
		muziekLezer.leesMuziek();
		}



	
	
	//testmethode voor lichttest
	public void lichttest() {
//		Lichtsensortest lichtsensortest = new Lichtsensortest(brick);
//		lichtsensortest.ambientLightTest();
	}
	
	
	
	
	
// UIT DE EERSTE MARVIN FILE
	
//	private void run() {
//		TextLCD display = brick.getTextLCD();
//		display.drawString("Welkom!", 0, 3);
//		display.drawString("Team Zero", 0, 4);
//		waitForKey(Button.ENTER);
//	}
//	
//	public void waitForKey(Key key) {
//		while(key.isUp()) {
//			Delay.msDelay(100);
//		}
//		while(key.isDown()) {
//			Delay.msDelay(100);
//		}
//	}
	
	
}
