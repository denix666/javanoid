package javanoid;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Only for WAV sounds!!!
public class Sound {
    public void play(String soundFile) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream audioSrc = getClass().getResourceAsStream("resources/sounds/"+soundFile);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.out.println("play sound error: " + e.getMessage());
        }
    }
}