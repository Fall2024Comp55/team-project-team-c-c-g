//import java.io.File; 
//import java.io.IOException; 
//import java.util.Scanner; 
//import javax.sound.sampled.AudioInputStream; 
//import javax.sound.sampled.AudioSystem; 
//import javax.sound.sampled.Clip; 
//import javax.sound.sampled.LineUnavailableException; 
//import javax.sound.sampled.UnsupportedAudioFileException; 
//
//public class Sound  { 
//
//	// to store current position 
//	Long currentFrame; 
//	Clip clip; 
//	
//	// current status of clip 
//	String status; 
//	
//	AudioInputStream audioInputStream; 
//	static String filePath; 
//
//	// constructor to initialize streams and clip 
//	public Sound() 
//		throws UnsupportedAudioFileException, 
//		IOException, LineUnavailableException 
//	{ 
//		// create AudioInputStream object 
//		audioInputStream = 
//				AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
//		
//		// create clip reference 
//		clip = AudioSystem.getClip(); 
//		
//		// open audioInputStream to the clip 
//		clip.open(audioInputStream); 
//		
//		clip.loop(Clip.LOOP_CONTINUOUSLY); 
//	} 
//
//	public static void main(String[] args) 
//	{ 
//		try
//		{ 
//			filePath = "/COMP55TimerLab/Audio/Kanye West - Violent Crimes (Lyric Video).wav"; 
//			Sound audioPlayer = 
//							new Sound(); 
//			
//			audioPlayer.play(); 
//			Scanner sc = new Scanner(System.in); 
//			
//			while (true) 
//			{ 
//				System.out.println("1. pause"); 
//				System.out.println("2. resume"); 
//				System.out.println("3. restart"); 
//				System.out.println("4. stop"); 
//				System.out.println("5. Jump to specific time"); 
//				int c = sc.nextInt(); 
//				audioPlayer.gotoChoice(c); 
//				if (c == 4) 
//				break; 
//			} 
//			sc.close(); 
//		} 
//		
//		catch (Exception ex) 
//		{ 
//			System.out.println("Error with playing sound."); 
//			ex.printStackTrace(); 
//		
//		} 
//	} 
//	
//	// Work as the user enters his choice 
//	
//	private void gotoChoice(int c) 
//			throws IOException, LineUnavailableException, UnsupportedAudioFileException 
//	{ 
//		switch (c) 
//		{ 
//			case 1: 
//				pause(); 
//				break; 
//			case 2: 
//				resumeAudio(); 
//				break; 
//			case 3: 
//				restart(); 
//				break; 
//			case 4: 
//				stop(); 
//				break; 
//			case 5: 
//				System.out.println("Enter time (" + 0 + 
//				", " + clip.getMicrosecondLength() + ")"); 
//				Scanner sc = new Scanner(System.in); 
//				long c1 = sc.nextLong(); 
//				jump(c1); 
//				break; 
//	
//		} 
//	
//	} 
//	
//	// Method to play the audio 
//	public void play() 
//	{ 
//		//start the clip 
//		clip.start(); 
//		
//		status = "play"; 
//	} 
//	
//	// Method to pause the audio 
//	public void pause() 
//	{ 
//		if (status.equals("paused")) 
//		{ 
//			System.out.println("audio is already paused"); 
//			return; 
//		} 
//		this.currentFrame = 
//		this.clip.getMicrosecondPosition(); 
//		clip.stop(); 
//		status = "paused"; 
//	} 
//	
//	// Method to resume the audio 
//	public void resumeAudio() throws UnsupportedAudioFileException, 
//								IOException, LineUnavailableException 
//	{ 
//		if (status.equals("play")) 
//		{ 
//			System.out.println("Audio is already "+ 
//			"being played"); 
//			return; 
//		} 
//		clip.close(); 
//		resetAudioStream(); 
//		clip.setMicrosecondPosition(currentFrame); 
//		this.play(); 
//	} 
//	
//	// Method to restart the audio 
//	public void restart() throws IOException, LineUnavailableException, 
//											UnsupportedAudioFileException 
//	{ 
//		clip.stop(); 
//		clip.close(); 
//		resetAudioStream(); 
//		currentFrame = 0L; 
//		clip.setMicrosecondPosition(0); 
//		this.play(); 
//	} 
//	
//	// Method to stop the audio 
//	public void stop() throws UnsupportedAudioFileException, 
//	IOException, LineUnavailableException 
//	{ 
//		currentFrame = 0L; 
//		clip.stop(); 
//		clip.close(); 
//	} 
//	
//	// Method to jump over a specific part 
//	public void jump(long c) throws UnsupportedAudioFileException, IOException, 
//														LineUnavailableException 
//	{ 
//		if (c > 0 && c < clip.getMicrosecondLength()) 
//		{ 
//			clip.stop(); 
//			clip.close(); 
//			resetAudioStream(); 
//			currentFrame = c; 
//			clip.setMicrosecondPosition(c); 
//			this.play(); 
//		} 
//	} 
//	
//	// Method to reset audio stream 
//	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
//											LineUnavailableException 
//	{ 
//		audioInputStream = AudioSystem.getAudioInputStream( 
//		new File(filePath).getAbsoluteFile()); 
//		clip.open(audioInputStream); 
//		clip.loop(Clip.LOOP_CONTINUOUSLY); 
//	} 
//} 
//import javax.sound.sampled.*;
//import java.io.File;
//
//public class Sound {
//    private Clip clip;
//
//    public Sound(String filename) {
//        try {
//            // Load the sound file
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filename));
//            clip = AudioSystem.getClip();
//            clip.open(audioStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to set the volume of the sound
//    public void setVolume(float volume) {
//        try {
//            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
//                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                float range = gainControl.getMaximum() - gainControl.getMinimum();
//                float gain = range * volume + gainControl.getMinimum();
//                gainControl.setValue(gain);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to loop the sound
//    public void loop() {
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.start();
//    }
//
//    // Method to play the sound once
//    public void play() {
//        clip.start();
//    }
//
//    // Method to stop the sound
//    public void stop() {
//        clip.stop();
//    }
//}
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip clip;

    public Sound(String filename) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filename))) {
            // Load the sound file
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            System.out.println("Sound file loaded successfully: " + filename);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound file: " + e.getMessage());
        }
    }

    // Method to set the volume of the sound
    public void setVolume(float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
            System.out.println("Volume set to: " + volume);
        } else {
            System.err.println("Volume control not supported for this clip.");
        }
    }

    // Method to loop the sound
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Sound loop started.");
        } else {
            System.err.println("Clip is not initialized.");
        }
    }

    // Method to play the sound once
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
            System.out.println("Sound play started.");
        } else {
            System.err.println("Clip is not initialized.");
        }
    }

    // Method to stop the sound
    public void stop() {
        if (clip != null) {
            clip.stop();
            System.out.println("Sound stopped.");
        } else {
            System.err.println("Clip is not initialized.");
        }
    }

    // Method to close the clip and release resources
    public void close() {
        if (clip != null) {
            clip.close();
            System.out.println("Clip closed.");
        }
    }
}


