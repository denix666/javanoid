package javanoid;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class BonusBrick {

	private BufferedImage brickImg;
	
	public int x,y;
	public String brickType;
	public int brickImgWidth,brickImgHeight;
	
	public BonusBrick(String brickType, int x, int y) {
		this.x = x;
		this.y = y;
		this.brickType = brickType;
		
		loadContent();
	}
	
	private void loadContent() {
		try {
			URL brickImgUrl = this.getClass().getResource("/javanoid/resources/bonuses/"+brickType+".png");
			brickImg = ImageIO.read(brickImgUrl);
			brickImgWidth = brickImg.getWidth();
			brickImgHeight = brickImg.getHeight();
		} catch (IOException ex) {
            Logger.getLogger(BonusBrick.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void update() {
		y += 2;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, brickImgWidth, brickImgHeight);                     
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(brickImg, x, y, null);
	}
}
