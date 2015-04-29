package javanoid;

import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {
    public Sound(String soundType) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("resources/"+soundType+".au");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            System.out.println("Проблема со звуком");
        }
    }
}
