package javanoid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class GameFrameWork extends Listeners {
	
    public static int frameWidth;
    public static int frameHeight;
    public static int frameIndent = 15;
	
    public static final long secInNanosec = 1000000000L;
    public static final long milisecInNanosec = 1000000L;
    private static final int GAME_FPS = 50; //50
    private static final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    public static enum GameState {
        STARTING, VISUALIZING, GAME_CONTENT_LOADING, INTRO, OPTIONS, PLAYING, GAMEOVER, LEVEL_FAIL, LEVEL_COMPLETE, GAME_COMPLETE
    }
    
    public static GameState gameState;
    
    private Game game;
    private BufferedImage introImg;
    private long gameTime, lastTime;
    private Music music;
	
    public GameFrameWork () {
        super();
        music = new Music("intro.wav");
        gameState = GameState.VISUALIZING;

        Thread gameThread = new Thread() {
            @Override
            public void run(){
                gameLoop();
            }
        };
        gameThread.start();
    }
	
    private void gameLoop() {
        long beginTime, timeTaken, timeLeft;
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();

        while(true) {
            beginTime = System.nanoTime();
			
            switch (gameState) {
                case PLAYING:
                    gameTime += System.nanoTime() - lastTime;
                    game.updateGame(gameTime, mousePosition());
                    lastTime = System.nanoTime();
                break;
	            case GAMEOVER:
	                //...
	            break;
	            case INTRO:
	                //...
	            break;
	            case OPTIONS:
	                //...
	            break;
	            case LEVEL_FAIL:
	                //...
	            break;
	            case GAME_CONTENT_LOADING:
	                //...
	            break;
	            case STARTING:
	            	loadIntro();
	                gameState = GameState.INTRO;
                break;
				case VISUALIZING:
					if(this.getWidth() > 1 && visualizingTime > secInNanosec) {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    } else {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
				break;
				case LEVEL_COMPLETE:
					//..
				break;
				default:
					//..
				break;
			}
			
			repaint();
			timeTaken = System.nanoTime() - beginTime;
	        timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec;
	        if (timeLeft < 10) 
	            timeLeft = 10; //set a minimum
	        try {
	             //Provides the necessary delay and also yields control so that other thread can do work.
	             Thread.sleep(timeLeft);
	        } catch (InterruptedException ex) { 
	        	//..
	        }
		}
	}
	
	private void loadIntro() {
        try {
            URL introImgUrl = this.getClass().getResource("/javanoid/resources/images/intro.jpg");
            introImg = ImageIO.read(introImgUrl);
        } catch (IOException ex) {
            Logger.getLogger(GameFrameWork.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        music.play();
    }
	
	@Override
    public void draw(Graphics2D g2d) {
		switch (gameState) {
			case GAMEOVER:
	            game.drawGameOver(g2d, mousePosition());
	        break;
			case LEVEL_FAIL:
	            game.drawLevelFail(g2d, mousePosition());
	        break;
			case PLAYING:
                game.draw(g2d, mousePosition());
            break;
            case INTRO:
                g2d.drawImage(introImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.white);
                g2d.drawString("-=DeN=- 2016", 7, frameHeight - 5);
            break;
            case OPTIONS:
                //...
            break;
            case GAME_CONTENT_LOADING:
                g2d.setColor(Color.white);
                g2d.drawString("LOADING...", frameWidth / 2 - 50, frameHeight / 2);
            break;
			case STARTING:
				//..
			break;
			case VISUALIZING:
				//..
			break;
			case LEVEL_COMPLETE:
				game.drawLevelComplete(g2d, mousePosition());
			break;
			case GAME_COMPLETE:
				game.drawGameComplete(g2d, mousePosition());
			break;
			default:
				//..
			break;
        }
    }
	
	private void newGame() {
		game = new Game();
		music.stop();
    }
	
	private void restartGame() {
		game.restartGame();
		gameState = GameState.PLAYING;
	}
	
	private void restartLevel() {
		game.restartLevel();
		gameState = GameState.PLAYING;
	}
	
	private void loadLevel() {
		game.loadLevel();
		gameState = GameState.PLAYING;
	}
	
	@Override
    public void keyReleasedFramework(KeyEvent e) {
		switch (gameState) {
            case INTRO:
            	newGame();
            break;
            case LEVEL_FAIL:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
                	restartLevel();
            break;
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
                	restartGame();
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                	gameState = GameState.INTRO;
            break;
			case GAME_CONTENT_LOADING:
				//..
			break;
			case OPTIONS:
				//..
			break;
			case PLAYING:
				//..
			break;
			case STARTING:
				//..
			break;
			case VISUALIZING:
				//..
			break;
			case LEVEL_COMPLETE:
				loadLevel();
			break;
			case GAME_COMPLETE:
				if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
					newGame();
			break;
			default:
				//..
			break;
        }
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        switch (gameState) {
            case INTRO:
                if(e.getButton() == MouseEvent.BUTTON1)
                    newGame();
            break;
		case GAMEOVER:
			break;
		case GAME_CONTENT_LOADING:
			break;
		case LEVEL_FAIL:
			//..
		break;
		case OPTIONS:
			//..
		break;
		case PLAYING:
			//..
		break;
		case STARTING:
			//..
		break;
		case VISUALIZING:
			//..
		break;
		case LEVEL_COMPLETE:
			//..
		break;
		default:
			//..
		break;
        }
    }
	
	private Point mousePosition() {
        try {
            Point mp = this.getMousePosition();
            
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        } catch (Exception e) {
            return new Point(0, 0);
        }
    }
}
