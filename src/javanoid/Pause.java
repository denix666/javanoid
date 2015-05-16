package javanoid;

public class Pause {
    public void wait(int mseconds) {
        try {
            Thread.sleep(mseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
