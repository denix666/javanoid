package javanoid;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Laser {
	private BufferedImage laserImg;
	public int laserImgWidth,laserImgHeight;
	public int x,y;
	public static long timeBetweenLasers = GameFrameWork.secInNanosec/4;
	public static long lastLaserTime = 0;
	
	public Laser(int x, int y) {
		loadContent();
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, laserImgWidth, laserImgHeight);                     
	}
	
	public void Update() {
		y -= 8;
	}
	
	private void loadContent() {
		try {
			URL laserImgUrl = this.getClass().getResource("/javanoid/resources/images/laser.png");
			laserImg = ImageIO.read(laserImgUrl);
			laserImgWidth = laserImg.getWidth();
			laserImgHeight = laserImg.getHeight();
		} catch (IOException ex) {
            Logger.getLogger(Laser.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(laserImg, x, y, null);
    }
}
