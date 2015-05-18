package javanoid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Window extends JPanel implements ActionListener,MouseMotionListener {
    // Paddle
    private static boolean paddleDirectionLeft, paddleDirectionRight;
    private static int paddlePosition = Main.GAME_AREA_WIDTH/2-50;
    private final int paddleStepMove = 5;
    
    // Ball
    private int ballStepMoveX = 3;
    private int ballStepMoveY = 3;
    private boolean ballDirectionLeft = false;
    private boolean ballDirectionRight = true;
    private boolean ballDirectionUp = true;
    private boolean ballDirectionDown = false;
    private int ballPosX = Main.GAME_AREA_WIDTH/2-8;
    private int ballPosY =  Main.GAME_AREA_HEIGHT-65;
    private int calcBallPosX, calcBallPosY;
    
    // Bricks
    private int destroyedBricks;
    private static final int numberOfBricks = 135;
    private final Brick[] bricks = new Brick[numberOfBricks];
    
    // Other vars
    private static boolean gameRunning;
    private static final int DELAY = 7;
    
    // Objects
    Img background = new Img("backgrounds/bg"+Main.curLevel+".jpg");
    JFrame frame = new JFrame();
    Img paddle = new Img("paddle.png");
    Img ball = new Img("ball.png");
    Img ramka = new Img("ramka.png");
    Timer timer = new Timer(DELAY, this);
    Level level = new Level(Main.curLevel);
    Sound sound = new Sound();
    Music music = new Music("intro.wav");
    Pause pause = new Pause();
    
    public Window(String winTitle, int winWidth, int winHeight) {
        // Загрузка первого уровня
        loadLevel(Main.curLevel);
        
        //music.loop();
        
        frame.addKeyListener(new MyKeyListener());
        frame.addMouseMotionListener(this);
        
        // Make invisible mouse cursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        
        frame.add(this);
        frame.setTitle(winTitle);
        frame.setSize(winWidth,winHeight);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        levelStart();
    }
    
    // Передвижение ракетки
    public void movePaddle() {
        if (paddleDirectionLeft) {
            if (paddlePosition > 15) {
                paddlePosition = paddlePosition - paddleStepMove;
            }
        }

        if (paddleDirectionRight) {
            if (paddlePosition < Main.GAME_AREA_WIDTH-100) {
                paddlePosition = paddlePosition + paddleStepMove;
            }
        }
    }
    
    public void levelCompleted() {
        gameRunning = false;
        timer.stop();
        music.stop();
        sound.play("level_completed.wav");
        pause.wait(5000);
        if (Main.curLevel < Main.numOfLevels) {
            Main.curLevel++;
            this.background = new Img("backgrounds/bg"+Main.curLevel+".jpg");
            //this.music = new Music("level"+Main.curLevel+".wav");
            //this.music = new Music("intro.wav");
            frame.setTitle("Level - "+Main.curLevel+" | Lives - "+Main.numOfLives+" | Score - "+Main.score);
            loadLevel(Main.curLevel);
            levelStart();
        } else {
            // Victory
            sound.play("game_completed.wav");
            pause.wait(5000);
            System.exit(0);
        }
    }
    
    public void levelFail() {
        timer.stop();
        music.stop();
        sound.play("ball_lost.wav");
        pause.wait(500);
        sound.play("level_fail.wav");
        pause.wait(3000);
        gameRunning = false;
        if (Main.numOfLives > 0) {
            Main.numOfLives--;
            levelStart();
        } else {
            // Mission failure
            sound.play("game_over.wav");
            pause.wait(3000);
            System.exit(0);
        }
    }
    
    public void levelStart() {
        frame.setTitle("Level - "+Main.curLevel+" | Lives - "+Main.numOfLives+" | Score - "+Main.score);
        gameRunning = true;
        paddlePosition = Main.GAME_AREA_WIDTH/2-50;
        ballPosX = Main.GAME_AREA_WIDTH/2-8;
        ballPosY =  Main.GAME_AREA_HEIGHT-65;
        //music.stop();
        sound.play("level_start.wav");
        //music.play();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        movePaddle();
        moveBall();
    }
    
    // Прослушка клавиатуры
    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                paddleDirectionLeft = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                paddleDirectionRight = true;
            }
            if (key == KeyEvent.VK_SPACE) {
                if (!gameRunning) {
                    levelStart();
                    timer.start();
                } else {
                    timer.start();
                }
            }
            if (key == KeyEvent.VK_PAUSE) {
                timer.stop();
            }
            
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                paddleDirectionLeft = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                paddleDirectionRight = false;
            }
        }
    }
    
    // Прослушка мышки
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {
        paddlePosition = e.getX();
        if (paddlePosition < 15) {
            paddlePosition = 15;
        }
        
        if (paddlePosition > Main.GAME_AREA_WIDTH-100) {
            paddlePosition =Main.GAME_AREA_WIDTH-100;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.img, 0, 0, this);
        g.drawImage(ramka.img, 0, 0, this);
        g.drawImage(paddle.img, paddlePosition, Main.GAME_AREA_HEIGHT-50, this);
        g.drawImage(ball.img, ballPosX, ballPosY, this);
        
        for (int q=0; q<numberOfBricks; q++) {
            if (bricks[q].Destroyed != true) {
                g.drawImage(bricks[q].img, bricks[q].x, bricks[q].y, this);
            }
        }
 
        repaint();
    }
    
    private void moveBall() {
        for (int q=0; q<numberOfBricks; q++) {
            if (bricks[q].Destroyed != true) {
                if (ballDirectionRight) {
                    calcBallPosX = ballPosX+14;
                } else {
                    calcBallPosX = ballPosX+2;
                }
                if (ballDirectionUp) {
                    calcBallPosY = ballPosY+2;
                } else {
                    calcBallPosY = ballPosY+14;
                }
                
                
                if (calcBallPosX > bricks[q].x && calcBallPosX < bricks[q].x+50 && calcBallPosY < bricks[q].y+20 && calcBallPosY > bricks[q].y) {
                    bricks[q].Destroyed = true;
                    sound.play("destroyed_block.au");
                    
                    Main.score = Main.score + 25;
                    frame.setTitle("Level - "+Main.curLevel+" | Lives - "+Main.numOfLives+" | Score - "+Main.score);
                    if (ballDirectionDown) {
                        ballDirectionDown = false;
                        ballDirectionUp = true;
                    } else {
                        ballDirectionDown = true;
                        ballDirectionUp = false;
                    }
                    break;
                }
            }
        }
        
        if (ballDirectionRight) {
            if (ballPosX < Main.GAME_AREA_WIDTH-16) {
                ballPosX = ballPosX + ballStepMoveX;
            } else {
                ballDirectionLeft = true;
                ballDirectionRight = false;
            }
        }
        if (ballDirectionLeft) {
            if (ballPosX > 15) {
                ballPosX = ballPosX - ballStepMoveX;
            } else {
                ballDirectionLeft = false;
                ballDirectionRight = true;
            }
        }
        if (ballDirectionUp) {
            if (ballPosY > 7) {
                ballPosY = ballPosY - ballStepMoveY;
            } else {
                ballDirectionUp = false;
                ballDirectionDown = true;
            }
        }
        if (ballDirectionDown) {
            if (ballPosY < Main.GAME_AREA_HEIGHT-66) {
                ballPosY = ballPosY + ballStepMoveY;
            } else {
                if (calcBallPosX < paddlePosition) {
                    levelFail();
                } else if (calcBallPosX > paddlePosition+100){
                    levelFail();
                } else {
                    ballDirectionUp = true;
                    ballDirectionDown = false;
                    if (calcBallPosX > paddlePosition+75 && calcBallPosX < paddlePosition+100) {
                        ballStepMoveY = 2;
                        ballDirectionLeft = false;
                        ballDirectionRight = true;
                    }
                    if (calcBallPosX > paddlePosition+50 && calcBallPosX < paddlePosition+75) {
                        ballStepMoveY = 3;
                        ballDirectionLeft = false;
                        ballDirectionRight = true;
                    }
                    if (calcBallPosX > paddlePosition && calcBallPosX < paddlePosition+25) {
                        ballStepMoveY = 2;
                        ballDirectionLeft = true;
                        ballDirectionRight = false;
                    }
                    if (calcBallPosX > paddlePosition+25 && calcBallPosX < paddlePosition+50) {
                        ballStepMoveY = 3;
                        ballDirectionLeft = true;
                        ballDirectionRight = false;
                    }
                    sound.play("paddle_click.au");
                }
            }
        }
        
        destroyedBricks=0;
        for (int j=0; j<numberOfBricks; j++) {
            if (bricks[j].Destroyed) {
                destroyedBricks++;
            }
            if (destroyedBricks==numberOfBricks) {
                levelCompleted();
            }
        }
    }
    
    public void loadLevel(int numLevel) {
        Level NewLevel = new Level(numLevel);

        int k = 0;
        for (int i=1; i<10; i++) { // Кол-во рядов
            int j = 1;
            switch (i) {
                case 1:
                    for (char ch: NewLevel.a[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 2:
                    for (char ch: NewLevel.b[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 3:
                    for (char ch: NewLevel.c[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 4:
                    for (char ch: NewLevel.d[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 5:
                    for (char ch: NewLevel.e[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 6:
                    for (char ch: NewLevel.f[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 7:
                    for (char ch: NewLevel.g[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 8:
                    for (char ch: NewLevel.h[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
                case 9:
                    for (char ch: NewLevel.i[numLevel].toCharArray()) {
                        if ("o".equals(Character.toString(ch))) {
                            bricks[k] = new Brick("b", j*50, i*20, true);
                            bricks[k].Destroyed = true;
                        } else {
                            bricks[k] = new Brick(Character.toString(ch), j*50, i*20, false);
                        }
                        k++;
                        j++;
                    }
                break;
            }
        }
    }
}
