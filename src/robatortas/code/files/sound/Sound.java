package robatortas.code.files.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	public static Sound shoot = new Sound("/shoot.wav");
	
	
	private String path;
	private Clip clip;
	
	private Sound(String path) {
		this.path = path;
		
		InputStream src = Sound.class.getResourceAsStream(path);
		
		try {
			AudioInputStream out = AudioSystem.getAudioInputStream(src);
			this.clip = AudioSystem.getClip();
			clip.open(out);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.err.println("\n\nLineUnavailableException on the Sound class");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			System.err.println("\n\nUnsoported audio file at: " + path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nUnable to load sound located at: " + path);
		}
	}
	
	public void play() {
		if(clip == null) return;
		
		stop();
		clip.setFramePosition(0);
		
		while(!clip.isRunning()) {
			clip.start();
		}
	}
	
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
			clip.flush();
		}
	}
	
	public void setVol(float value) {
//		if(clip.isRunning()) {
			FloatControl vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			vol.setValue(value);
//		}
	}
	
	public void loop() {
		while(clip.isRunning()) {
			clip.loop(0);
		}
	}
}