package screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameWindow;

public class BackGround {
    public int x, y;
    public BufferedImage sprite;
    
    public BackGround(int x, int y, String image){
        try {
            this.x = x;
            this.y = y;
            this.sprite = ImageIO.read(getClass().getResourceAsStream(image));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite,this.x, this.y, GameWindow.WINDOW_HEIGHT, GameWindow.WINDOW_HEIGHT,null);
    }
}
