package javanoid;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Bricks {

	private BufferedImage brickImg, brickImgA, brickImgB;
	
	public int x,y;
	public String brickType;
	public int brickImgWidth,brickImgHeight;
	public int brickPower = 0;
	public boolean brickPowered = false;
	public boolean bonusBrick = false;
	
	public Bricks(String brickType, int x, int y, int brickPower, boolean brickPowered) {
		this.x = x;
		this.y = y;
		this.brickType = brickType;
		this.brickPower = brickPower;
		this.brickPowered = brickPowered;
		
		loadContent();
	}
	
	private void loadContent() {
		try {
			URL brickImgUrl = this.getClass().getResource("/javanoid/resources/bricks/brick_"+brickType+".png");
			brickImg = ImageIO.read(brickImgUrl);
			brickImgWidth = brickImg.getWidth();
			brickImgHeight = brickImg.getHeight();
			
			if (brickPowered) {
				URL brickImgUrlA = this.getClass().getResource("/javanoid/resources/bricks/brick_"+brickType+"_a.png");
				brickImgA = ImageIO.read(brickImgUrlA);
				URL brickImgUrlB = this.getClass().getResource("/javanoid/resources/bricks/brick_"+brickType+"_b.png");
				brickImgB = ImageIO.read(brickImgUrlB);
			}
			
		} catch (IOException ex) {
            Logger.getLogger(Bricks.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, brickImgWidth, brickImgHeight);                     
	}
	
	public Rectangle getLeftSide() {
		return new Rectangle(x, y, brickImgWidth/2, brickImgHeight);
	}
	
	public Rectangle getRightSide() {
		return new Rectangle(x+brickImgWidth/2, y, brickImgWidth/2, brickImgHeight);
	}
	
	public Rectangle getUpSide() {
		return new Rectangle(x, y, brickImgWidth, brickImgHeight/2);
	}
	
	public Rectangle getDownSide() {
		return new Rectangle(x, y+brickImgHeight/2, brickImgWidth, brickImgHeight/2);
	}
	
	public void draw(Graphics2D g2d) {
		if (brickPowered) {
			if (brickPower == 2) {
				g2d.drawImage(brickImg, x, y, null);
			} else if (brickPower == 1) {
				g2d.drawImage(brickImgA, x, y, null);
			} else if (brickPower == 0) {
				g2d.drawImage(brickImgB, x, y, null);
			}
		} else {
			g2d.drawImage(brickImg, x, y, null);
		}
	}
}
