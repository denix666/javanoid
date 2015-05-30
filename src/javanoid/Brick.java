package javanoid;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Brick {
    protected Image img;
    protected int x,y;
    protected boolean Destroyed;
    protected String bonus;
    
    public Brick(String brickColor, int x, int y, boolean Destroyed) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("resources/bricks/brick_" + brickColor + ".png"));
        img = ii.getImage();
        
        this.x = x;
        this.y = y;
    }
}
