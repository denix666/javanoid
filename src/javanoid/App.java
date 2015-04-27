package javanoid;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
    public App() {
        add(new Window());
        setTitle("Javanoid v0.1");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
         EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new App();
                ex.setVisible(true);                
            }
        });
    }
}