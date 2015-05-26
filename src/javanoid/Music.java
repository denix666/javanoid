package javanoid;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    
    private Clip clip;
    
    public Music(String soundFile) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("resources/sounds/"+soundFile);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream sound = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.out.println("play sound error: " + e.getMessage());
        }
    }
    
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void loop(){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-5.0f);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
}
