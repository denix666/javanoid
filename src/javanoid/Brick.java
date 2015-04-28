package javanoid;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Brick {
    protected Image img;
    protected int x,y;
    protected boolean Destroyed;
    
    public Brick(String brickColor, int x, int y, boolean Destroyed) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("resources/brick_" + brickColor + ".png"));
        img = ii.getImage();
        
        this.x = x;
        this.y = y;
    }
}
