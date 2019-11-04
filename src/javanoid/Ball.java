package javanoid;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Ball {
	private BufferedImage ballImg;
	public int ballImgWidth,ballImgHeight;
	public int x,y;
	public int posOnPaddle;
	public static long idleTime = GameFrameWork.secInNanosec * 3;
	public static long lastBallTime = 0;
	public int ballStepMoveX = 5;
	public int ballStepMoveY = 5;
	public boolean ballDirectionLeft = false;
	public boolean ballDirectionRight = true;
	public boolean ballDirectionUp = true;
	public boolean ballDirectionDown = false;
	public boolean released = false;
	
	public Ball(int x, int y, int ballStepMoveX, boolean released) {
		loadContent();
		this.x = x;
		this.y = y;
		this.released = released;
		this.ballStepMoveX = ballStepMoveX;
	}
	
	private void loadContent() {
		try {
			URL ballImgUrl = this.getClass().getResource("/javanoid/resources/images/ball.png");
			ballImg = ImageIO.read(ballImgUrl);
			ballImgWidth = ballImg.getWidth();
			ballImgHeight = ballImg.getHeight();
		} catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
		lastBallTime = System.nanoTime();
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, ballImgWidth, ballImgHeight);                     
	}
	
	public Rectangle getUpSide() {
		return new Rectangle(x+7, y+3, 4, 2);
	}
	
	public Rectangle getLeftSide() {
		return new Rectangle(x+3, y+7, 2, 4);
	}
	
	public Rectangle getRightSide() {
		return new Rectangle(x+13, y+7, 2, 4);
	}
	
	public Rectangle getDownSide() {
		return new Rectangle(x+7, y+13, 4, 2);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ballImg, x, y, null);
    }
}
