package javanoid;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class Window extends JPanel implements ActionListener,MouseListener {
    private final int WINDOW_WIDTH = 1015;
    private final int WINDOW_HEIGHT = 650;
    private final int GAME_AREA_WIDTH = 825;
    private final int GAME_AREA_HEIGHT = 600;
    private Image imgBoard;
    private Image imgBackground;
    private Image imgBall;
    private Image imgTable;
    private int position =  GAME_AREA_WIDTH/2-50;
    private int x = GAME_AREA_WIDTH/2-8;
    private int y =  GAME_AREA_HEIGHT-65;
    private final int boardStepMove = 6;
    private int ballStepMoveX = 3;
    private int ballStepMoveY = 3;
    private final int DELAY = 7;
    private Timer timer;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean inGame = false;
    private boolean gameOver = false;
    private boolean levelCompleted = false;
    private int destroyedBricks;

    private final int numberOfBricks = 135;
    
    private boolean ballDirectionLeft = false;
    private boolean ballDirectionRight = true;
    private boolean ballDirectionUp = true;
    private boolean ballDirectionDown = false;
    
    private final Brick[] bricks = new Brick[numberOfBricks];
    
    // Загрузка графики и других ресурсов
    private void loadResources() {
        ImageIcon img_imgBoard = new ImageIcon(getClass().getResource("resources/paddle.png")); 
        imgBoard = img_imgBoard.getImage();
        
        ImageIcon img_imgBackground = new ImageIcon(getClass().getResource("resources/bg1.jpg")); 
        imgBackground = img_imgBackground.getImage();
        
        ImageIcon img_imgBall = new ImageIcon(getClass().getResource("resources/ball.png")); 
        imgBall = img_imgBall.getImage();
        
        ImageIcon img_imgTable = new ImageIcon(getClass().getResource("resources/ramka.png")); 
        imgTable = img_imgTable.getImage();
    }
    
    // Инициализация главного окошка
    public Window() {
        addKeyListener(new MyKeyListener());
        addMouseListener(new MyMouseListener());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        loadResources();
        LoadLevel(0);
    }
    
    public void LoadLevel(int numLevel) {
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
        
        Sound.play("flute.wav",true);
    }
    
    // Прослушка таймера
    @Override
    public void actionPerformed(ActionEvent e) {
        moveBoard();
        moveBall();
    }
    
    public void startGame() {
        inGame = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void stopGame() {
        Sound.play("fall.au",false);
        inGame = false;
        gameOver = true;
        timer.stop();
    }
    
    public void levelCompleted() {
        Sound.play("victory.au",false);
        inGame = false;
        levelCompleted = true;
        timer.stop();
    }
    
    private void moveBall() {
        for (int q=0; q<numberOfBricks; q++) {
            if (bricks[q].Destroyed != true) {

                if (x+8 > bricks[q].x && x+8 < bricks[q].x+50 && y+8 < bricks[q].y+20 && y+8 > bricks[q].y) {
                    
                    // Для дебага координат
                    //System.out.println("br x " + bricks[q].x);
                    //System.out.println("br y " + bricks[q].y);

                    //System.out.println("ball x " + x);
                    //System.out.println("ball y " + y);
                    
                    bricks[q].Destroyed = true;
                    if (ballDirectionDown) {
                        ballDirectionDown = false;
                        ballDirectionUp = true;
                    } else {
                        ballDirectionDown = true;
                        ballDirectionUp = false;
                    }
                    Sound.play("bum.au",false);
                    break;
                }
            }
        }
        
        if (ballDirectionRight) {
            if (x < GAME_AREA_WIDTH-16) {
                x = x + ballStepMoveX;
            } else {
                ballDirectionLeft = true;
                ballDirectionRight = false;
            }
        }
        if (ballDirectionLeft) {
            if (x > 15) {
                x = x - ballStepMoveX;
            } else {
                ballDirectionLeft = false;
                ballDirectionRight = true;
            }
        }
        if (ballDirectionUp) {
            if (y > 7) {
                y = y - ballStepMoveY;
            } else {
                ballDirectionUp = false;
                ballDirectionDown = true;
            }
        }
        if (ballDirectionDown) {
            if (y < GAME_AREA_HEIGHT-66) {
                y = y + ballStepMoveY;
            } else {
                if (x+16 < position) {
                    stopGame();
                } else if (x+8 > position+100){
                    stopGame();
                } else {
                    ballDirectionUp = true;
                    ballDirectionDown = false;
                    if (x+8 > position+75 && x+8 < position+100) {
                        ballStepMoveY = 2;
                        ballDirectionLeft = false;
                        ballDirectionRight = true;
                    }
                    if (x+8 > position+50 && x+8 < position+75) {
                        ballStepMoveY = 3;
                        ballDirectionLeft = false;
                        ballDirectionRight = true;
                    }
                    if (x+8 > position+1 && x+8 < position+25) {
                        ballStepMoveY = 2;
                        ballDirectionLeft = true;
                        ballDirectionRight = false;
                    }
                    if (x+8 > position+25 && x+8 < position+50) {
                        ballStepMoveY = 3;
                        ballDirectionLeft = true;
                        ballDirectionRight = false;
                    }
                    Sound.play("click.au",false);
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
    
    // Передвижение ракетки
    private void moveBoard() {
        if (leftDirection) {
            if (position > 15) {
                position = position - boardStepMove;
            }
        }

        if (rightDirection) {
            if (position < GAME_AREA_WIDTH-100) {
                position = position + boardStepMove;
            }
        }
    }

    
    // Разобраться зачем вся эта хрень тут нужна
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Для дебага координат
    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();
            System.out.println("Координаты точки: "+x+","+y);
        }
    }
    
    // Прослушка клавиатуры
    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                leftDirection = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                rightDirection = true;
            }
            if (key == KeyEvent.VK_SPACE) {
                if (inGame != true) {
                    startGame();
                }
            }
            if (key == KeyEvent.VK_PAUSE) {
                if (timer.isRunning()) {
                    timer.stop();
                } else {
                    timer.start();
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                leftDirection = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                rightDirection = false;
            }
        }
    }
    
    private void writeMessage(Graphics g, int x, int y, String msg) {
        try {
            InputStream fntStream = getClass().getResourceAsStream("resources/iomanoid.ttf");
            Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, fntStream);
            Font Iomanoid = fontRaw.deriveFont(45f);

            g.setColor(Color.black);
            g.setFont(Iomanoid);
            g.drawString(msg, x,y);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    // Отрисовка графических обьектов
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackground, 1, 1, this); 
        g.drawImage(imgTable, 1, 1, this);
        g.drawImage(imgBoard, position, GAME_AREA_HEIGHT-50, this);
        if (gameOver != true) {
            g.drawImage(imgBall, x, y, this);
        }
        
        for (int q=0; q<numberOfBricks; q++) {
            if (bricks[q].Destroyed != true) {
                g.drawImage(bricks[q].img, bricks[q].x, bricks[q].y, this);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        repaint();
        
        if (gameOver) {
            writeMessage(g, 340,GAME_AREA_HEIGHT/2,"GAME OVER");
        }
        if (levelCompleted) {
            writeMessage(g, 320,GAME_AREA_HEIGHT/2,"V I C T O R Y");
        }
        writeMessage(g, 845,50,"Level - 1");
    }
}