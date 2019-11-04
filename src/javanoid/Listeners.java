package javanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Listeners extends JPanel implements KeyListener, MouseListener {

	private static boolean[] keyboardState = new boolean[525];
	private static boolean[] mouseState = new boolean[3];
	
	public Listeners() {
		this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.black);
        
        this.addKeyListener(this);
        this.addMouseListener(this);
	}
	
	public abstract void draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;        
        super.paintComponent(g2d);        
        draw(g2d);
    }
    
    // Keyboard
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }
    
    // Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) {
        keyboardState[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    public abstract void keyReleasedFramework(KeyEvent e);
    
    // Mouse
    public static boolean mouseButtonState(int button) {
        return mouseState[button - 1];
    }
    
    // Sets mouse key status.
    private void mouseKeyStatus(MouseEvent e, boolean status) {
        if(e.getButton() == MouseEvent.BUTTON1)
            mouseState[0] = status;
        else if(e.getButton() == MouseEvent.BUTTON2)
            mouseState[1] = status;
        else if(e.getButton() == MouseEvent.BUTTON3)
            mouseState[2] = status;
    }
    
    // Methods of the mouse listener.
    @Override
    public void mousePressed(MouseEvent e) {
        mouseKeyStatus(e, true);
        
        //remove after debug
        //System.out.println(e.getX() + "," + e.getY());
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseKeyStatus(e, false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }

	@Override
    public void mouseEntered(MouseEvent e) { }
    
    @Override
    public void mouseExited(MouseEvent e) { }
}
