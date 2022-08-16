package robatortas.code.files.sound;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	public static Sound shoot = new Sound("/shoot.wav");
	public static Sound impact = new Sound("/impact.wav");
	public static Sound playerDeath = new Sound("/playerDeath.wav");
	
	
	public Clip clip = null;
	private FloatControl gainControl;
	
	private Sound(String path) {
		try {
			// Decoding Audio process
			InputStream audioSrc = Sound.class.getResourceAsStream(path);
			// Buffers encoded audio source from path to the system memory
			InputStream bufferedin = new BufferedInputStream(audioSrc);
			// Makes the inputStream the buffered audio
			AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(bufferedin);
			// Gets the format of the audioStream1
			AudioFormat baseFormat = audioStream1.getFormat();
			// Decodes the baseFormat to a PCM_SIGNED encoding.
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
														baseFormat.getSampleRate(),
														16,
														baseFormat.getChannels(),
														baseFormat.getChannels() * 2,
														baseFormat.getSampleRate(),
														false);
			
			//Decoded Audio (output)
			AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodeFormat, audioStream1);
			
			clip = AudioSystem.getClip();
			//Opens Decoded Audio
			clip.open(decodedAudioStream);
			
			//Gain Control for Audio
			gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(clip == null)
			return;
			
			stop();
			clip.setFramePosition(0);
			
			/*Fixes not playing bug.
			 * Forces it to play if not running when called
			 * Keeps trying to play it until played.
			 */
			while(!clip.isRunning()) {
				clip.start();
			}
	}
	
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	public void close() {
		stop();
		clip.drain();
		clip.close();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		play();
	}
	
	public void setVolume(float value) {
		gainControl.setValue(value);
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
}
