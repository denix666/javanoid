package javanoid;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bonus {
    protected Image img;
    protected int x,y;
    protected boolean inuse;
    
    public Bonus(String bonusType, int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("resources/bonuses/bonus_" + bonusType + ".png"));
        img = ii.getImage();
        
        this.x = x;
        this.y = y;
    }
}
