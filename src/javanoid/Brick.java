package javanoid;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Brick {
    public Image imgBrick;
    public int brickX, brickY;
    
    public Brick(int brickX, int brickY) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("resources/brick.png"));
        imgBrick = ii.getImage();
        this.brickX = brickX;
        this.brickY = brickY;
    }
}
