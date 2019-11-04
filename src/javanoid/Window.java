package javanoid;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private BufferedImage iconImg;
		
    private Window() {
    	try {
            URL iconImgUrl = this.getClass().getResource("/javanoid/resources/images/icon.png");
            iconImg = ImageIO.read(iconImgUrl);
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.getImage(this.getClass().getResource("/javanoid/resources/images/cursor.png"));
        Point cursorHotSpot = new Point(0,0);
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");
    	
        this.setCursor(customCursor);
        this.setTitle("Javanoid v1.0");
        this.setIconImage(iconImg);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(810, 725);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new GameFrameWork());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
