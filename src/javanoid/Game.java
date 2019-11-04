package javanoid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Game {
	private Sound sound;
	private Random random;
	private String bonusType;
	private Player player;
	private ArrayList<Ball> ball;
	private ArrayList<Laser> laser;
	private ArrayList<BonusBrick> bonusBrick;
	private ArrayList<Cipher> cipher;
	private ArrayList<Bricks> brick;
	private BufferedImage frameImg, gameOverImg, levelFailImg, gameCompletedImg;
	private int levelNum = 0; //0
	private int ballAccel = 0; //0
	private GameLevel gameLevel;
	private String[] lvlBricks;
	
	private static final long BALL_SPEED_UP_TIME = 30; //Промежуток увеличение скорости мячика(ов) в секундах
	
	private static long speedUpTime = GameFrameWork.secInNanosec * BALL_SPEED_UP_TIME;
	private static long lastSpeedUpTime = 0;
	
	public Game() {
        GameFrameWork.gameState = GameFrameWork.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
            	initialize();
            	loadContent();
                GameFrameWork.gameState = GameFrameWork.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
	
	private void loadContent() {
		try {
			URL frameImgUrl = this.getClass().getResource("/javanoid/resources/images/frame.png");
			frameImg = ImageIO.read(frameImgUrl);
			
			URL gameOverImgUrl = this.getClass().getResource("/javanoid/resources/images/game_over.png");
            gameOverImg = ImageIO.read(gameOverImgUrl);
            
            URL levelFailImgUrl = this.getClass().getResource("/javanoid/resources/images/level_fail.png");
            levelFailImg = ImageIO.read(levelFailImgUrl);
            
            URL gameCompletedImgUrl = this.getClass().getResource("/javanoid/resources/images/game_completed.png");
            gameCompletedImg = ImageIO.read(gameCompletedImgUrl);
		} catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
		ball.add(new Ball(player.x+player.paddleImgWidth/2-8 ,player.y-15,5,false));
    }
	
	private void initialize() {
		sound = new Sound();
        player = new Player(false,false,false);
        ball = new ArrayList<Ball>();
        cipher = new ArrayList<Cipher>();
        brick = new ArrayList<Bricks>();
        laser = new ArrayList<Laser>();
        bonusBrick = new ArrayList<BonusBrick>();
        random = new Random();
        player.expanded = false;
        player.laser = false;
		player.catched = false;
        
        for(int i = 0; i < 10; i++) { 
        	cipher.add(new Cipher(i));
        }
        
        sound.play("level_start.wav");
        loadLevel();
        lastSpeedUpTime = System.nanoTime();
        ballAccel = 0;
    }
	
	public void restartGame() { 
		player.resetPlayer();
		player.expanded = false;
		player.laser = false;
		player.catched = false;
		player.lives = 3;
		player.score = 0;
        ball.clear();
        brick.clear();
        laser.clear();
        bonusBrick.clear();
        ball.add(new Ball(player.x+player.paddleImgWidth/2-8 ,player.y-15,5,false));
        sound.play("level_start.wav");
        levelNum = 0;
        loadLevel();
        lastSpeedUpTime = System.nanoTime();
        ballAccel = 0;
    }
	
	public void restartLevel() { 
		player.resetPlayer();
        ball.clear();
        laser.clear();
        bonusBrick.clear();
        ball.add(new Ball(player.x+player.paddleImgWidth/2-8 ,player.y-15,5,false));
        sound.play("level_start.wav");
        player.expanded = false;
        player.laser = false;
		player.catched = false;
        lastSpeedUpTime = System.nanoTime();
        ballAccel = 0;
    }
	
	public void loadLevel() {
		levelNum += 1;
		gameLevel = new GameLevel(levelNum);
		lvlBricks = new String[21];
		
		int j;
		int brickPower;
		boolean brickPowered;
		
		// Добавляем кубики на уровни
		switch (levelNum) {
			case 1:
	    		 lvlBricks[0] = "               ";
	    		 lvlBricks[1] = "               ";
	    		 lvlBricks[2] = "               ";
	    		 lvlBricks[3] = " cccccc cccccc ";
	    		 lvlBricks[4] = " ffffff ffffff ";
	    		 lvlBricks[5] = " mmmmmm mmmmmm ";
	    		 lvlBricks[6] = " bbbbbb bbbbbb ";
	    		 lvlBricks[7] = "               ";
	    		 lvlBricks[8] = "               ";
	    		 lvlBricks[9] = "               ";
	    		lvlBricks[10] = "               ";
	    		lvlBricks[11] = "               ";
	    		lvlBricks[12] = "               ";
	    		lvlBricks[13] = "               ";
	    		lvlBricks[14] = "               ";
	    		lvlBricks[15] = "               ";
	    		lvlBricks[16] = "               ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
			case 2:
       		 	 lvlBricks[0] = "               ";
	       		 lvlBricks[1] = " cc         cc ";
	       		 lvlBricks[2] = "   c  ccc  c   ";
	       		 lvlBricks[3] = "    cc   cc    ";
	       		 lvlBricks[4] = "   c       c   ";
	       		 lvlBricks[5] = "  c  c   c  c  ";
	       		 lvlBricks[6] = "  c         c  ";
	       		 lvlBricks[7] = "   c  c c  c   ";
	       		 lvlBricks[8] = "    c  c  c    ";
	       		 lvlBricks[9] = "    ccccccc    ";
	       		lvlBricks[10] = "   c   c   c   ";
	    		lvlBricks[11] = "  c         c  ";
	    		lvlBricks[12] = "               ";
	    		lvlBricks[13] = "               ";
	    		lvlBricks[14] = "               ";
	    		lvlBricks[15] = "               ";
	    		lvlBricks[16] = "               ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
			case 3:
	    		 lvlBricks[0] = "               ";
	    		 lvlBricks[1] = "               ";
	    		 lvlBricks[2] = "               ";
	    		 lvlBricks[3] = "ccccccccccccccc";
	    		 lvlBricks[4] = "fffffffffffffff";
	    		 lvlBricks[5] = "mmmmmmmmmmmmmmm";
	    		 lvlBricks[6] = "bbbbbbbbbbbbbbb";
	    		 lvlBricks[7] = "               ";
	    		 lvlBricks[8] = "wwwwwwwwwwwwwww";
	    		 lvlBricks[9] = "ggggggggggggggg";
	    		lvlBricks[10] = "               ";
	    		lvlBricks[11] = "               ";
	    		lvlBricks[12] = "               ";
	    		lvlBricks[13] = "               ";
	    		lvlBricks[14] = "ggggggggggggggg";
	    		lvlBricks[15] = "aaaaaaaaaaaaaaa";
	    		lvlBricks[16] = "               ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
			case 4:
	    		 lvlBricks[0] = "ggggggggggggggg";
	    		 lvlBricks[1] = "               ";
	    		 lvlBricks[2] = "               ";
	    		 lvlBricks[3] = " cccccc cccccc ";
	    		 lvlBricks[4] = " ffffff ffffff ";
	    		 lvlBricks[5] = " mmmmmm mmmmmm ";
	    		 lvlBricks[6] = " bbbbbb bbbbbb ";
	    		 lvlBricks[7] = "aaaaaaaaaaaaaaa";
	    		 lvlBricks[8] = "               ";
	    		 lvlBricks[9] = "               ";
	    		lvlBricks[10] = "               ";
	    		lvlBricks[11] = "yyyyyyyyyyyyyyy";
	    		lvlBricks[12] = "wwwwwwwwwwwwwww";
	    		lvlBricks[13] = "ppppppppppppppp";
	    		lvlBricks[14] = "aaaaaaaaaaaaaaa";
	    		lvlBricks[15] = "               ";
	    		lvlBricks[16] = "               ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
			case 5:
	    		 lvlBricks[0] = "               ";
	    		 lvlBricks[1] = "               ";
	    		 lvlBricks[2] = "               ";
	    		 lvlBricks[3] = "wwwwwwwwwwwwaa ";
	    		 lvlBricks[4] = "ccccccccccccaa ";
	    		 lvlBricks[5] = "rrrrrrrrrrrraa ";
	    		 lvlBricks[6] = "bbbbbbbbbbbbaa ";
	    		 lvlBricks[7] = "rrrrrrrrrrrraa ";
	    		 lvlBricks[8] = "ggggggggggggaa ";
	    		 lvlBricks[9] = "ffffffffffffaa ";
	    		lvlBricks[10] = "yyyyyyyyyyyyaa ";
	    		lvlBricks[11] = "ffffffffffffaa ";
	    		lvlBricks[12] = "ccccccccccccaa ";
	    		lvlBricks[13] = "ppppppppppppaa ";
	    		lvlBricks[14] = "rrrrrrrrrrrraa ";
	    		lvlBricks[15] = "aaaaaaaaaaaaaa ";
	    		lvlBricks[16] = "aaaaaaaaaaaaaa ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
			case 6:
	    		 lvlBricks[0] = "               ";
	    		 lvlBricks[1] = "               ";
	    		 lvlBricks[2] = "               ";
	    		 lvlBricks[3] = " a a a a a a a ";
	    		 lvlBricks[4] = " a a a a a a a ";
	    		 lvlBricks[5] = "gagagagagagagag";
	    		 lvlBricks[6] = " a a a a a a a ";
	    		 lvlBricks[7] = " a a a a a a a ";
	    		 lvlBricks[8] = " a a a a a a a ";
	    		 lvlBricks[9] = "wawawawawawawaw";
	    		lvlBricks[10] = " a a a a a a a ";
	    		lvlBricks[11] = " a a a a a a a ";
	    		lvlBricks[12] = " a a a a a a a ";
	    		lvlBricks[13] = "yayayayayayayay";
	    		lvlBricks[14] = " a a a a a a a ";
	    		lvlBricks[15] = " a a a a a a a ";
	    		lvlBricks[16] = " a a a a a a a ";
	    		lvlBricks[17] = "               ";
	    		lvlBricks[18] = "               ";
	    		lvlBricks[19] = "               ";
	    		lvlBricks[20] = "               ";
		    break;
		}
		
		for (int i=0; i<21; i++) { // Кол-во рядов
			j = 0;
			for (char ch: lvlBricks[i].toCharArray()) {
				if (!" ".equals(Character.toString(ch))) {
					switch(Character.toString(ch)) {
						case "a":
							brickPower = 2;
							brickPowered = true;
						break;
						default:
							brickPower = 0;
							brickPowered = false;
						break;
					}
					brick.add(new Bricks(Character.toString(ch),j*50+GameFrameWork.frameIndent+15,i*20+GameFrameWork.frameIndent+15,brickPower,brickPowered));
				}
				j += 1;
			}
		}
		
		// Рандомально определяем бонусные кирпичики
		for (int i=0; i<6; i++) { // Кол-во бонусов
			brick.get(random.nextInt(brick.size())).bonusBrick = true;
		}
	}
	
	private void levelFail() {
		sound.play("ball_lost.wav");
		
		player.lives -= 1;
		if (player.lives < 1) {
			GameFrameWork.gameState = GameFrameWork.GameState.GAMEOVER;
			sound.play("game_over.wav");
		} else {
			GameFrameWork.gameState = GameFrameWork.GameState.LEVEL_FAIL;
		}
	}
	
	private void levelComplete() {
		player.resetPlayer();
		ball.clear();
		laser.clear();
		ball.add(new Ball(player.x+player.paddleImgWidth/2-8 ,player.y-15,5,false));
		sound.play("level_completed.wav");
		ballAccel = 0;
		if (levelNum == 6) {
			GameFrameWork.gameState = GameFrameWork.GameState.GAME_COMPLETE;
		} else {
			GameFrameWork.gameState = GameFrameWork.GameState.LEVEL_COMPLETE;
		}
	}
	
	public void updateGame(long gameTime, Point mousePosition) {
		
		// Увеличение скорости мячика через промежуток времени
		if (System.nanoTime() - lastSpeedUpTime >= speedUpTime) {
			lastSpeedUpTime = System.nanoTime();
			ballAccel += 1;
		}
		
		int paddleWidth;
		
		if (player.expanded) {
			paddleWidth = player.paddleImgExpandedWidth;
		} else {
			paddleWidth = player.paddleImgWidth;
		}
		
		// Движение ракетки
		if(GameFrameWork.keyboardKeyState(KeyEvent.VK_LEFT)) {
			player.x -= player.paddleStepMove;
			if (player.x < GameFrameWork.frameIndent) {
				player.x  = GameFrameWork.frameIndent;
			}
		}
		if(GameFrameWork.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			player.x += player.paddleStepMove;
			if (player.expanded) {
				if (player.x > GameFrameWork.frameWidth-1-paddleWidth-GameFrameWork.frameIndent) {
					player.x  = GameFrameWork.frameWidth-1-paddleWidth-GameFrameWork.frameIndent;
				}
			} else {
				if (player.x > GameFrameWork.frameWidth-1-paddleWidth-GameFrameWork.frameIndent) {
					player.x  = GameFrameWork.frameWidth-1-paddleWidth-GameFrameWork.frameIndent;
				}
			}
		}
		// Если имеем бонус лазера - стреляем
		if(GameFrameWork.keyboardKeyState(KeyEvent.VK_SPACE)) {
			if (player.laser) {
				if (System.nanoTime() - Laser.lastLaserTime >= Laser.timeBetweenLasers) {
					sound.play("laser.wav");
					laser.add(new Laser(player.x+15, player.y));
					laser.add(new Laser(player.x+53, player.y));
					Laser.lastLaserTime = System.nanoTime();
				}
			}
		}
		
		// Мячик(и)
		if(System.nanoTime() - Ball.lastBallTime >= Ball.idleTime || GameFrameWork.keyboardKeyState(KeyEvent.VK_SPACE)) {
			for(int i = 0; i < ball.size(); i++) {
				ball.get(i).released = true;
			}
		} else {
			for(int i = 0; i < ball.size(); i++) {
				if (!ball.get(i).released) {
					if (ball.get(i).posOnPaddle == 0) { 
						ball.get(i).x = player.x+paddleWidth/2-8;
					} else {
						ball.get(i).x = player.x+ball.get(i).posOnPaddle;
					}
				}
			}
		}
		
		for(int i = 0; i < ball.size(); i++) {
			if (ball.get(i).released) {
				if (ball.get(i).ballDirectionLeft) {
					ball.get(i).x -= ball.get(i).ballStepMoveX + ballAccel;
					if (ball.get(i).x < GameFrameWork.frameIndent) {
						ball.get(i).ballDirectionLeft = false;
						ball.get(i).ballDirectionRight = true;
						sound.play("ball_hit.wav");
					}
				} else if (ball.get(i).ballDirectionRight) {
					ball.get(i).x += ball.get(i).ballStepMoveX + ballAccel;
					if (ball.get(i).x > GameFrameWork.frameWidth-ball.get(i).ballImgWidth-GameFrameWork.frameIndent) {
						ball.get(i).ballDirectionRight = false;
						ball.get(i).ballDirectionLeft = true;
						sound.play("ball_hit.wav");
					}
				}
				if (ball.get(i).ballDirectionUp) {
					ball.get(i).y -= ball.get(i).ballStepMoveY + ballAccel;
					if (ball.get(i).y < GameFrameWork.frameIndent) {
						ball.get(i).ballDirectionUp = false;
						ball.get(i).ballDirectionDown = true;
						sound.play("ball_hit.wav");
					}
				} else if (ball.get(i).ballDirectionDown) {
					if (ball.get(i).y < player.y - ball.get(i).ballImgHeight) {
						ball.get(i).y += ball.get(i).ballStepMoveY + ballAccel;
					} else {
						if (ball.get(i).x + ball.get(i).ballImgWidth < player.x || ball.get(i).x > player.x + paddleWidth) {
							ball.get(i).y += ball.get(i).ballStepMoveY + 5;
							// На тот случай, если шариков летает больше одного
							if (ball.size() > 0) {
								ball.remove(i);
								if (ball.size() > 0) {
									break;
								} else {
									levelFail();
									break;
								}
							}
						} else {
							ball.get(i).ballDirectionDown = false;
							ball.get(i).ballDirectionUp = true;
							sound.play("paddle_click.au");
							if (player.catched) {
								ball.get(i).released = false;
								ball.get(i);
								Ball.lastBallTime = System.nanoTime();
								ball.get(i).posOnPaddle = ball.get(i).x - player.x;
							}
							if (ball.get(i).x + ball.get(i).ballImgWidth/2 >= player.x - ball.get(i).ballImgWidth/2 && ball.get(i).x + ball.get(i).ballImgWidth/2 <= player.x + paddleWidth/4) {
								ball.get(i).ballStepMoveX = ballAccel + 7;
								ball.get(i).ballDirectionRight = false;
								ball.get(i).ballDirectionLeft = true;
							} else if (ball.get(i).x + ball.get(i).ballImgWidth/2 >= player.x + paddleWidth/4 && ball.get(i).x + ball.get(i).ballImgWidth/2 <= player.x + paddleWidth/2 + paddleWidth/4) {
								ball.get(i).ballStepMoveX = ballAccel + 5;
							} else if (ball.get(i).x + ball.get(i).ballImgWidth/2 >= player.x + paddleWidth/2 + paddleWidth/4 && ball.get(i).x + ball.get(i).ballImgWidth/2 <= player.x + paddleWidth + ball.get(i).ballImgWidth/2) {
								ball.get(i).ballStepMoveX = ballAccel + 7;
								ball.get(i).ballDirectionRight = true;
								ball.get(i).ballDirectionLeft = false;
							}
						}
					}
				}
			}
			
			for(int y = 0; y < brick.size(); y++) {				
				Rectangle rBallLeftSide = ball.get(i).getLeftSide();
				Rectangle rBallUpSide = ball.get(i).getUpSide();
				Rectangle rBallRightSide = ball.get(i).getRightSide();
				Rectangle rBallDownSide = ball.get(i).getDownSide();
				
				Rectangle rBrickLeftSide = brick.get(y).getLeftSide();
				Rectangle rBrickRightSide = brick.get(y).getRightSide();
				Rectangle rBrickUpSide = brick.get(y).getUpSide();
				Rectangle rBrickDownSide = brick.get(y).getDownSide();
				
				if (rBallUpSide.intersects(rBrickDownSide)) {
					ball.get(i).ballDirectionDown = true;
					ball.get(i).ballDirectionUp = false;
					checkBrick(y);
				} else 
					
				if (rBallDownSide.intersects(rBrickUpSide)) {
					ball.get(i).ballDirectionDown = false;
					ball.get(i).ballDirectionUp = true;
					checkBrick(y);
				} else
				
				if (rBallRightSide.intersects(rBrickLeftSide)) {
					ball.get(i).ballDirectionRight = false;
					ball.get(i).ballDirectionLeft = true;
					checkBrick(y);
				} else
				
				if (rBallLeftSide.intersects(rBrickRightSide)) {
					ball.get(i).ballDirectionRight = true;
					ball.get(i).ballDirectionLeft = false;
					checkBrick(y);
				}
			}
			
			if (brick.size() < 1) {
				bonusBrick.clear();
				levelComplete();
			}
		}
		
		// Проверка пулек
		for(int l = 0; l < laser.size(); l++) {
			laser.get(l).Update();
			if (laser.get(l).x < 0) {
				laser.remove(l);
			}
			
			Rectangle rLaser = laser.get(l).getBounds();
			for(int b = 0; b < brick.size(); b++) {
				Rectangle rBrick = brick.get(b).getBounds();
				if (rLaser.intersects(rBrick)) {
					checkBrick(b);
					laser.remove(l);
					break;
				}
			}
		}
		
		// Проверяем пойман ли бонус
		for(int z = 0; z < bonusBrick.size(); z++) {
			bonusBrick.get(z).update();
			Rectangle rBonusBrick = bonusBrick.get(z).getBounds();
			Rectangle rPaddle = player.getBounds();
			if (rPaddle.intersects(rBonusBrick)) {
				switch(bonusBrick.get(z).brickType) {
					case "live":
						player.lives += 1;
						player.expanded = false;
						player.laser = false;
						player.catched = false;
						sound.play("bonus.wav");
					break;
					case "laser":
						player.laser = true;
						player.expanded = false;
						player.catched = false;
						sound.play("bonus.wav");
					break;
					case "expand":
						player.expanded = true;
						player.laser = false;
						player.catched = false;
						sound.play("paddle_ext.wav");
					break;
					case "catch":
						player.catched = true;
						player.expanded = false;
						player.laser = false;
						sound.play("bonus.wav");
					break;
					case "balls":
						ball.add(new Ball(ball.get(0).x ,ball.get(0).y,4,true));
						ball.add(new Ball(ball.get(0).x ,ball.get(0).y,6,true));
						player.expanded = false;
						player.laser = false;
						player.catched = false;
						sound.play("bonus.wav");
					break;
					case "slow":
						player.expanded = false;
						player.laser = false;
						player.catched = false;
						for(int r = 0; r < ball.size(); r++) {
							if (ballAccel > -3) {
								ballAccel -= 1;
							}
						}
						sound.play("bonus.wav");
					break;
				}
				player.score += 100;
				bonusBrick.remove(z);
			} else {
				if (bonusBrick.get(z).y > player.y + player.paddleImgHeight) {
					bonusBrick.remove(z);
				}
			}
		}
	}
	
	private void checkBrick(int brickIndex) {
		// Check if brick with bonus
		if (brick.get(brickIndex).bonusBrick) {
			switch (random.nextInt(6)) {
				case 0:
					bonusType = "live";
				break;
				case 1:
					bonusType = "slow";
				break;
				case 2:
					bonusType = "laser";
				break;
				case 3:
					bonusType = "expand";
				break;
				case 4:
					bonusType = "catch";
				break;
				case 5:
					bonusType = "balls";
				break;
			}
			bonusBrick.add(new BonusBrick(bonusType,brick.get(brickIndex).x, brick.get(brickIndex).y));
		}
		
		if (brick.get(brickIndex).brickPower == 0) {
			brick.remove(brickIndex);
			player.score += 10;
		} else {
			brick.get(brickIndex).brickPower -= 1;
			player.score += 5;
		}
		sound.play("destroyed_block.au");
	}
	
	public void drawGameOver(Graphics2D g2d, Point mousePosition) {
		draw(g2d, mousePosition);
		g2d.drawImage(gameOverImg, 0, 0, GameFrameWork.frameWidth, GameFrameWork.frameHeight, null);
	}
	
	public void drawLevelFail(Graphics2D g2d, Point mousePosition) {
		draw(g2d, mousePosition);
		g2d.drawImage(levelFailImg, 0, 0, GameFrameWork.frameWidth, GameFrameWork.frameHeight, null);
	}
	
	public void drawLevelComplete(Graphics2D g2d, Point mousePosition) {
		draw(g2d, mousePosition);
		g2d.setColor(Color.white);
        g2d.drawString("Good work!.", GameFrameWork.frameWidth / 2 - 100, GameFrameWork.frameHeight / 3 + 70);
	}
	
	public void drawGameComplete(Graphics2D g2d, Point mousePosition) {
		draw(g2d, mousePosition);
		g2d.drawImage(gameCompletedImg, 0, 0, GameFrameWork.frameWidth, GameFrameWork.frameHeight, null);
	}
	
	public void draw(Graphics2D g2d, Point mousePosition) {
		gameLevel.Draw(g2d);
		g2d.drawImage(frameImg, 0, 0, null);
		player.draw(g2d);
		
		for(int i = 0; i < ball.size(); i++) {
			ball.get(i).draw(g2d);
        }
		
		for(int i = 0; i < laser.size(); i++) {
			laser.get(i).Draw(g2d);
        }
		
		for(int i = 0; i < bonusBrick.size(); i++) {
			bonusBrick.get(i).draw(g2d);
        }
		
		for(int i = 0; i < brick.size(); i++) {
			brick.get(i).draw(g2d);
        }
        
        // Level
        cipher.get(levelNum).draw(g2d,165,628);
        
        // Lives
        cipher.get(player.lives).draw(g2d,395,628);
        
        // Score
        String s = ""+player.score;
        int x = 610;
        for (int i = 0; i < s.length(); i++) {
        	int n = Character.getNumericValue(s.charAt(i));
        	cipher.get(n).draw(g2d,x+=20,628);
        }
    }
}
