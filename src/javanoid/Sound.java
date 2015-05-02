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

// Only for WAV sounds!!!
public class Sound {
    public static synchronized void play(final String soundFile, final boolean loop) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream audioSrc = getClass().getResourceAsStream("resources/sounds/"+soundFile);
                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(inputStream);
                    if (loop) {
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-15.0f);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } else {
                        clip.start();
                    }
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    System.out.println("play sound error: " + e.getMessage());
                }
            }
        }).start();
    }
    
    
}