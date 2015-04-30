package javanoid;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Only for WAV sounds!!!
public class Sound {
    public static synchronized void play(final String soundType) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream audioSrc = getClass().getResourceAsStream("resources/"+soundType+".au");
                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(inputStream);
                    clip.start();
                    //System.out.println("resources/"+soundType+".au");
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage());
                }
            }
        }).start();
    }
}