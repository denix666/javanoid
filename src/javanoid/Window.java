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

public class Window extends JPanel implements ActionListener,MouseListener {
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 600;
    private Image imgBoard;
    private Image imgBackground;
    private Image imgBall;
    private int position = WINDOW_WIDTH/2-50;
    private int x = WINDOW_WIDTH/2-8;
    private int y = WINDOW_HEIGHT-65;
    private final int boardStepMove = 2;
    private final int ballStepMove = 1;
    private final int DELAY = 5;
    private Timer timer;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean inGame = false;
    private boolean gameOver = false;
    private boolean levelCompleted = false;
    private int destroyedBricks;
    
    
    private final int numberOfBricks = 108;
    
    private boolean ballDirectionLeft = false;
    private boolean ballDirectionRight = true;
    private boolean ballDirectionUp = true;
    private boolean ballDirectionDown = false;
    
    private Brick[] bricks = new Brick[numberOfBricks];
    
    
    // Загрузка графики и других ресурсов
    private void loadResources() {
        ImageIcon img_imgBoard = new ImageIcon(getClass().getResource("resources/paddle.png")); 
        imgBoard = img_imgBoard.getImage();
        
        ImageIcon img_imgBackground = new ImageIcon(getClass().getResource("resources/bg1.jpg")); 
        imgBackground = img_imgBackground.getImage();
        
        ImageIcon img_imgBall = new ImageIcon(getClass().getResource("resources/ball.png")); 
        imgBall = img_imgBall.getImage();
    }
    
    // Инициализация главного окошка
    public Window() {
        addKeyListener(new MyKeyListener());
        addMouseListener(new MyMouseListener());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        loadResources();
        Level_1();
    }
    
    public void Level_1() {
        Level Level1 = new Level(1);
        
        int k = 0;
        for (int i=1; i<10; i++) { // Кол-во рядов
            int j = 1;
            switch (i) {
                case 1:
                    for (char ch: Level1.a[0].toCharArray()) {
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
                    for (char ch: Level1.b[0].toCharArray()) {
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
                    for (char ch: Level1.c[0].toCharArray()) {
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
                    for (char ch: Level1.d[0].toCharArray()) {
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
                    for (char ch: Level1.e[0].toCharArray()) {
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
                    for (char ch: Level1.f[0].toCharArray()) {
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
                    for (char ch: Level1.g[0].toCharArray()) {
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
                    for (char ch: Level1.h[0].toCharArray()) {
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
                    for (char ch: Level1.i[0].toCharArray()) {
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
        
        //Sound.play("flute.wav",true);
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
    
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (WINDOW_WIDTH - metr.stringWidth(msg)) / 2, WINDOW_HEIGHT / 2);
    }
    
    private void victory(Graphics g) {
        String msg = "Victory";
        Font small = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(msg, (WINDOW_WIDTH - metr.stringWidth(msg)) / 2, WINDOW_HEIGHT / 2);
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

                if (x+8 > bricks[q].x && x+8 < bricks[q].x+46 && y+8 < bricks[q].y+22 && y+8 > bricks[q].y) {
                    
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
            if (x < WINDOW_WIDTH-16) {
                x = x + ballStepMove;
            } else {
                ballDirectionLeft = true;
                ballDirectionRight = false;
            }
        }
        if (ballDirectionLeft) {
            if (x > 0) {
                x = x - ballStepMove;
            } else {
                ballDirectionLeft = false;
                ballDirectionRight = true;
            }
        }
        if (ballDirectionUp) {
            if (y > 0) {
                y = y - ballStepMove;
            } else {
                ballDirectionUp = false;
                ballDirectionDown = true;
            }
        }
        if (ballDirectionDown) {
            if (y < WINDOW_HEIGHT-66) {
                y = y + ballStepMove;
            } else {
                if (x+8 < position) {
                    stopGame();
                } else if (x+8 > position+100){
                    stopGame();
                } else {
                    ballDirectionUp = true;
                    ballDirectionDown = false;
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
            if (position > 0) {
                position = position - boardStepMove;
            }
        }

        if (rightDirection) {
            if (position < WINDOW_WIDTH-100) {
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
    
    // Отрисовка графических обьектов
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackground, 1, 1, this);          
        g.drawImage(imgBoard, position, WINDOW_HEIGHT-50, this);
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
            gameOver(g);
        }
        if (levelCompleted) {
            victory(g);
        }
    }
}