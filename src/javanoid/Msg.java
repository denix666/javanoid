package javanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;

public class Msg {
    // Надписи внешним шрифтом
    public Msg() {
        
    }
    
    public void writeMessage(Graphics g, int x, int y, String msg) {
        try {
            InputStream fntStream = getClass().getResourceAsStream("resources/iomanoid.ttf");
            Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, fntStream);
            Font Iomanoid = fontRaw.deriveFont(32f);
            g.setColor(Color.black);
            g.setFont(Iomanoid);
            g.drawString(msg, x,y);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

/*
        Офигенные тормоза - разобраться почему

        Создать обьект
        //Msg msg = new Msg();

        Добавить к paintComponent
        //msg.writeMessage(g, 850,50,"Level - "+Main.curLevel);
        //msg.writeMessage(g, 850,100,"Lives - "+Main.numOfLives);
        //msg.writeMessage(g, 850,150,"Score - "+Main.score);
*/