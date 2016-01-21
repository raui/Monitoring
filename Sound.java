package lockard;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
public class Sound {
    Thread tr = null;
	public static Clip clip;
    public static synchronized void play(final String fileName) 
    {            
        new Thread(new Runnable() { 
            public void run() {
                try {
                     clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.start(); 
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();		
    }
	public  void aret(){
	clip.stop();	   
  } 
}
