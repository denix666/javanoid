package javanoid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Window extends JPanel implements ActionListener,MouseMotionListener,MouseListener {
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
    private static final int numberOfBricks = 126;
    private final Brick[] bricks = new Brick[numberOfBricks];
    
    // Other vars
    private static boolean gameRunning;
    private static final int DELAY = 7;
    private static boolean showingIntro = true;
    private static boolean showingGameOver = false;
    
    // Objects
    Img background = new Img("backgrounds/bg"+Main.curLevel+".jpg");
    JFrame frame = new JFrame();
    Img paddle = new Img("paddle.png");
    Img ball = new Img("ball.png");
    Img intro = new Img("intro.jpg");
    Img gameOver = new Img("game_over.jpg");
    Img ramka = new Img("frame.png");
    Img lvlNumber = new Img("ciphers/"+Main.curLevel+".jpg");
    Img livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
    Img icon = new Img("icon.png");
    Timer timer = new Timer(DELAY, this);
    Level level = new Level(Main.curLevel);
    Sound sound = new Sound();
    Music music = new Music("intro.wav");
    Pause pause = new Pause();
    
    Img num0 = new Img("ciphers/0.jpg");
    Img num1 = new Img("ciphers/1.jpg");
    Img num2 = new Img("ciphers/2.jpg");
    Img num3 = new Img("ciphers/3.jpg");
    Img num4 = new Img("ciphers/4.jpg");
    Img num5 = new Img("ciphers/5.jpg");
    Img num6 = new Img("ciphers/6.jpg");
    Img num7 = new Img("ciphers/7.jpg");
    Img num8 = new Img("ciphers/8.jpg");
    Img num9 = new Img("ciphers/9.jpg");
    
    public Window(String winTitle, int winWidth, int winHeight) {
        // Загрузка первого уровня
        loadLevel(Main.curLevel);
        
        music.loop();
        
        frame.addKeyListener(new MyKeyListener());
        frame.addMouseMotionListener(this);
        frame.addMouseListener(this);
        
        // Make invisible mouse cursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        
        frame.add(this);
        frame.setIconImage(icon.img);
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
            if (paddlePosition > 10) {
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
        sound.play("level_completed.wav");
        pause.wait(5000);
        if (Main.curLevel < Main.numOfLevels) {
            Main.curLevel++;
            this.background = new Img("backgrounds/bg"+Main.curLevel+".jpg");
            this.lvlNumber = new Img("ciphers/"+Main.curLevel+".jpg");
            this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
            //frame.setTitle(Main.version+" | Level - "+Main.curLevel+" | Lives - "+Main.numOfLives+" | Score - "+Main.score);
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
            this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
            ballDirectionUp = true;
            ballDirectionDown = false;
            levelStart();
        } else {
            // Mission failure
            showingGameOver = true;
            sound.play("game_over.wav");
        }
    }
    
    public void levelStart() {
        if (showingGameOver) {
            Main.score = 0;
            Main.numOfLives = 2;
            Main.curLevel = 1;
            loadLevel(Main.curLevel);
            showingGameOver = false;
        }
        gameRunning = true;
        paddlePosition = Main.GAME_AREA_WIDTH/2-50;
        ballPosX = Main.GAME_AREA_WIDTH/2-8;
        ballPosY =  Main.GAME_AREA_HEIGHT-65;
        if (!showingIntro) {
            sound.play("level_start.wav");
        }
    }
    
    // Прослушка таймера
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
                if (showingIntro) {
                    showingIntro = false;
                    music.stop();
                    sound.play("level_start.wav");
                } else {
                    if (!gameRunning) {
                        levelStart();
                        timer.start();
                    } else {
                        timer.start();
                    }
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (showingIntro) {
            showingIntro = false;
            music.stop();
            sound.play("level_start.wav");
        } else {
            if (!gameRunning) {
                levelStart();
                timer.start();
            } else {
                timer.start();
            }
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        paddlePosition = e.getX();
        if (paddlePosition < 10) {
            paddlePosition = 10;
        }
        
        if (paddlePosition > Main.GAME_AREA_WIDTH-100) {
            paddlePosition =Main.GAME_AREA_WIDTH-100;
        }
        
        if (!timer.isRunning()) {
            ballPosX = paddlePosition+42;
        }
    }
    
    // Отрисовка компонентов
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(background.img, 0, 0, this);
        g.drawImage(ramka.img, 0, 0, this);
        g.drawImage(paddle.img, paddlePosition, Main.GAME_AREA_HEIGHT-50, this);
        g.drawImage(ball.img, ballPosX, ballPosY, this);
        g.drawImage(lvlNumber.img, 165, 625, this);
        g.drawImage(livesNumber.img, 390, 627, this);
        
        for (int q=0; q<numberOfBricks; q++) {
            if (bricks[q].Destroyed != true) {
                g.drawImage(bricks[q].img, bricks[q].x, bricks[q].y, this);
            }
        }
        
        if (showingIntro) {
            g.drawImage(intro.img, 0, 0, this);
        }
        
        if (showingGameOver) {
            g.drawImage(gameOver.img, 0, 0, this);
        }
        
        showScore(g);        
        repaint();
    }
    
    // Движение мячика
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
                    
                    // Bonus lives
                    if (Main.score == 2000) {
                        Main.numOfLives++;
                        sound.play("bonus.wav");
                        this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
                    }
                    if (Main.score == 7000) {
                        Main.numOfLives++;
                        sound.play("bonus.wav");
                        this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
                    }
                    if (Main.score == 10000) {
                        Main.numOfLives++;
                        sound.play("bonus.wav");
                        this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
                    }
                    if (Main.score == 15000) {
                        Main.numOfLives++;
                        sound.play("bonus.wav");
                        this.livesNumber = new Img("ciphers/"+Main.numOfLives+".jpg");
                    }
                    
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
            if (ballPosX > 10) {
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
    
    // Отображение цифр счета
    public void showScore(Graphics g) {
        String s = ""+Main.score;
        int x=630;
        for (int i=0; i<6; i++) {
            if (i<(6-s.length())) {
                g.drawImage(num0.img, x, 630, this);
            } else {
                int n = Character.getNumericValue(s.charAt(i-(6-s.length())));
                switch (n) {
                    case 0:
                        g.drawImage(num0.img, x, 630, this);
                    break;
                    case 1:
                        g.drawImage(num1.img, x, 630, this);
                    break;
                    case 2:
                        g.drawImage(num2.img, x, 630, this);
                    break;
                    case 3:
                        g.drawImage(num3.img, x, 630, this);
                    break;
                    case 4:
                        g.drawImage(num4.img, x, 630, this);
                    break;
                    case 5:
                        g.drawImage(num5.img, x, 630, this);
                    break;
                    case 6:
                        g.drawImage(num6.img, x, 630, this);
                    break;
                    case 7:
                        g.drawImage(num7.img, x, 630, this);
                    break;
                    case 8:
                        g.drawImage(num8.img, x, 630, this);
                    break;
                    case 9:
                        g.drawImage(num9.img, x, 630, this);
                    break;
                }
            }
            x=x+20;
        }
    }
    
    // Загрузка уровня
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
