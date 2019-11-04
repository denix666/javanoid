package javanoid;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class GameLevel {

	private BufferedImage bgImg;
	private int levelNum;
	
	public GameLevel(int levelNum) {
		this.levelNum = levelNum;
		loadContent();
	}
	
	private void loadContent() {
		try {
			URL bgImgUrl = this.getClass().getResource("/javanoid/resources/backgrounds/bg_"+levelNum+".png");
			bgImg = ImageIO.read(bgImgUrl);
		} catch (IOException ex) {
            Logger.getLogger(GameLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void Draw(Graphics2D g2d) {
        g2d.drawImage(bgImg, 0, 0, null);
	}
}
