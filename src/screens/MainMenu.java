package screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameWindow;

public class MainMenu {
    public int x, y;
    private BufferedImage mainMenuImage, pressSpaceToStart;
    
    public MainMenu(int x, int y){
        try {
            this.x = x;
            this.y = y;
            this.mainMenuImage = ImageIO.read(getClass().getResourceAsStream("/res/messages/space-invaders.png"));
            this.pressSpaceToStart = ImageIO.read(getClass().getResourceAsStream("/res/messages/press-space-to-start.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(mainMenuImage,this.x, this.y, GameWindow.WINDOW_HEIGHT, GameWindow.WINDOW_WIDTH / 2,null);
        g2.drawImage(pressSpaceToStart,this.x, 450, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT / 3,null);
    }
}
