package javanoid;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Player {
	private BufferedImage paddleImg, paddleImgExpanded, paddleImgLaser, paddleImgCatched;
    public int paddleImgWidth,paddleImgHeight,paddleImgExpandedWidth,paddleImgExpandedHeight;
    public int x,y;
    public int lives,score;
    public final int paddleStepMove = 8;
    public boolean expanded = false;
    public boolean laser = false;
    public boolean catched = false;
	
	public Player(boolean expanded, boolean laser, boolean catched) {
		this.expanded = expanded;
		this.laser = laser;
		this.catched = catched;
		
		initialize();
		loadContent();
	}
	
	private void initialize() {
		resetPlayer();
        lives = 3;
        score = 0;
	}
	
	private void loadContent() {
		try {
			URL paddleImgUrl = this.getClass().getResource("/javanoid/resources/images/paddle.png");
			paddleImg = ImageIO.read(paddleImgUrl);
			paddleImgWidth = paddleImg.getWidth();
			paddleImgHeight = paddleImg.getHeight();
			
			URL paddleImgExpandedUrl = this.getClass().getResource("/javanoid/resources/images/paddle_expanded.png");
			paddleImgExpanded = ImageIO.read(paddleImgExpandedUrl);
			paddleImgExpandedWidth = paddleImgExpanded.getWidth();
			paddleImgExpandedHeight = paddleImgExpanded.getHeight();
			
			URL paddleImgLaserUrl = this.getClass().getResource("/javanoid/resources/images/paddle_laser.png");
			paddleImgLaser = ImageIO.read(paddleImgLaserUrl);
			
			URL paddleImgCatchedUrl = this.getClass().getResource("/javanoid/resources/images/paddle_catch.png");
			paddleImgCatched = ImageIO.read(paddleImgCatchedUrl);
		} catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void resetPlayer() {
		if (expanded) {
			x = (GameFrameWork.frameWidth/2 - paddleImgExpandedWidth);
		} else {
			x = (GameFrameWork.frameWidth/2 - paddleImgWidth);
		}
        y = GameFrameWork.frameHeight - 135;
	}
	
	public Rectangle getBounds() {
		if (expanded) {
			return new Rectangle(x, y, paddleImgExpandedWidth, paddleImgExpandedHeight);
		} else {
			return new Rectangle(x, y, paddleImgWidth, paddleImgHeight);
		}
	}
	
	public void draw(Graphics2D g2d) {
        if (expanded) {
        	g2d.drawImage(paddleImgExpanded, x, y-15, null);
        } else if (laser) {
        	g2d.drawImage(paddleImgLaser, x, y-15, null);
        } else if (catched) {
        	g2d.drawImage(paddleImgCatched, x, y-15, null);
        } else {
        	g2d.drawImage(paddleImg, x, y-15, null);
        }
	}
}
