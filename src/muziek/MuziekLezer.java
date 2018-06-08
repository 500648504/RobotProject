package muziek;

import java.io.File;
import java.util.ArrayList;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class MuziekLezer {

	private Brick brick;
	private final int LEESSNELHEID = 150;
	private ArrayList<Integer> sampleLijst = new ArrayList<>();

	public MuziekLezer() {
	}

	public MuziekLezer(Brick brick) {
		this.brick = brick;
	}

	public void testRun() {
//		File groenGeluidObject = new File("test.wav");
//		Sound.playSample(groenGeluidObject, 200); // Bestandsnaam & Volume
//		Button.waitForAnyPress();
		leesMuziek();
	}

	public void leesMuziek() {
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
		File groenGeluidObject = new File("test.wav"); // groen
		Sound.setVolume(10);

		Motor.A.setSpeed(LEESSNELHEID);
		Motor.D.setSpeed(LEESSNELHEID);
		Motor.A.forward();
		Motor.D.forward();

		boolean doorgaan = true;
		int vorigeKleur	= -1;
		 Motor.A.resetTachoCount();
		 Motor.D.resetTachoCount();
		
		while ( sensor.getColorID() != 7 ) { // Stopt bij zwart (7)
			
//			System.out.println(sensor.getColorID());
//			int huidigeKleur = sensor.getColorID();
			
			if (Button.ESCAPE.isDown()) {
				Motor.A.stop();
				Motor.D.stop();
//				RobotLauncher.showMenu();
				break; 
			}
			
			
			


			 //sensor.getRedMode();
			// int sampleSize = sensor.sampleSize();
			// int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB
			// - samplesize opvragen?
			// float[] sample = new float[sampleSize];

			 int currentSample = -1;
			 int sampleteller = 0;

//			 while (Motor.B.getTachoCount() <= 4000) { // Aantal omwentelingen
			 if (Motor.A.getTachoCount() % 117 == 0) { // Als omwentelingen (modulo 150 =  0) / Kleuren Scannen:
//
			// De kleuren Scanner gaat aan.
			// Deze scant continue kleuren.
			 currentSample = sensor.getColorID(); // Haalt gescande kleur op.
//			
//			
//			if (vorigeKleur != huidigeKleur) {
//				vorigeKleur = huidigeKleur;
				
				switch (currentSample) {
				case 0: // Rood (Toon A)
						Sound.playTone(440, 500); // Frequentie & Duur
						System.out.println("Rood");
						System.out.println(sensor.getColorID());
						break;
				case 1: // Groen (Toon D)
						 Sound.playTone(293, 500);
//						Sound.playSample(groenGeluidObject, 400); // Bestandsnaam & Volume  (staat nu uit, als die aanstaat hoor je piano)
						System.out.println("Groen");
						System.out.println(sensor.getColorID());
						break; // Speelt 1x kleur af, daarna break.
				case 2: // Blauw (Toon E)
						 Sound.playTone(329, 500); // Frequentie & Duur
						System.out.println("Blauw");
						System.out.println(sensor.getColorID());
						break;
				case 3: // Geel (Toon F)
						 Sound.playTone(349, 500);
						System.out.println("Donker");
						System.out.println(sensor.getColorID());
						break;
				case 6: // Wit (toon G)
						Sound.playTone(391, 500);
						System.out.println("wit");
						System.out.println(sensor.getColorID());
						break;
				}
			}
		}

		
		 sensor.close();
		 Motor.A.stop(true);
		 Motor.D.stop(true);
		 return;
		 
	}
}



// Motor.B.resetTachoCount();
// Motor.C.resetTachoCount();

// sensor.getRedMode();
// int sampleSize = sensor.sampleSize();
// int sampleSize = 3; //niet netjes, misschien later nog veranderen? 3 voor RGB
// - samplesize opvragen?
// float[] sample = new float[sampleSize];

// int currentSample;
// int sampleteller = 0;

// while (Motor.B.getTachoCount() <= 1000) { // Aantal omwentelingen
// if (Motor.B.getTachoCount() % 150 == 0) { // Als omwentelingen (modulo 150 =  0) / Kleuren Scannen:

// De kleuren Scanner gaat aan.
// Deze scant continue kleuren.
// currentSample = sensor.getColorID(); // Haalt gescande kleur op.
// System.out.println("sample " + sampleteller + " " + sensor.getColorID());
// System.out.println(Motor.B.getTachoCount());
// sampleLijst.add(currentSample); // Stopt gescande kleur sampleLijst Array
// (gebeurt niets mee)
// sampleteller++;

// int selection = 0;
// if (sensor.getColorID() == 1) selection = 1; // Groen
// if (sensor.getColorID() == 2) selection = 2; // Blauw
// if (sensor.getColorID() == 3) selection = 3; // Donker Geel
// if (sensor.getColorID() == 6) selection = 6; // Geel
// if (sensor.getColorID() == 7) selection = 7; // Zwart - Hierdoor ziet de
// robot een nieuwe kleur

// File testwav0 = new File("geluid0.wav"); // rood
// File testwav1 = new File("geluid1.wav"); // groen
// File testwav2 = new File("geluid2.wav"); // licht blauw
// File testwav3 = new File("geluid3.wav"); // donker geel
// File testwav4 = new File("geluid4.wav"); // onbekend, misschien bruin?
// File testwav5 = new File("geluid5.wav"); // onbekend, misschien wit?
// File testwav6 = new File("geluid6.wav"); // licht geel
// File testwav7 = new File("geluid7.wav"); // zwart
//
// if (sensor.getColorID() == 0) { // Kijkt naar de kleuren ID, als deze 0 is
// dan:
// Sound.playSample(testwav0, 200); // Speel af: bestand: testwav0, volume: 200
// }
// if (sensor.getColorID() == 1) {
// Sound.playSample(testwav1, 200);
// }
// if (sensor.getColorID() == 2) {
// Sound.playSample(testwav2, 200);
// }
// if (sensor.getColorID() == 3) {
// Sound.playSample(testwav3, 200);
// }
// if (sensor.getColorID() == 4) {
// Sound.playSample(testwav4, 200);
// }
// if (sensor.getColorID() == 5) {
// Sound.playSample(testwav5, 200);
// }
// if (sensor.getColorID() == 6) {
// Sound.playSample(testwav6, 200);
// }
// if (sensor.getColorID() == 7) {
// Sound.playSample(testwav7, 200);
// }

// }
// sensor.getRGBMode().fetchSample(sample, 0);
// currentSample = (int)sample[0];
// System.out.println("test " + sampleteller + " " + currentSample);
// sampleLijst.add(currentSample);

// When ready close the sensor */
