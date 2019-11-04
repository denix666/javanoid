package javanoid;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Cipher {

	private BufferedImage cipherImg;
	
	public int x,y,cipher;
	public int cipherImgWidth,cipherImgHeight;
	
	public Cipher(int cipher) {
		this.cipher = cipher;
		loadContent();
	}
	
	private void loadContent() {
		try {
			URL cipherImgUrl = this.getClass().getResource("/javanoid/resources/ciphers/"+cipher+".jpg");
			cipherImg = ImageIO.read(cipherImgUrl);
			cipherImgWidth = cipherImg.getWidth();
			cipherImgHeight = cipherImg.getHeight();
		} catch (IOException ex) {
            Logger.getLogger(Cipher.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(cipherImg, x, y, null);
	}
}
