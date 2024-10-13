package screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.KeyHandler;
import main.GameWindow;

public class GameOver {
    public int x, y;
    public KeyHandler keyHandler;
    private BufferedImage gameOverImage, pressSpaceToContinueImage;
    
    public GameOver(int x, int y, KeyHandler keyHandler, String image){
        try {
            this.x = x;
            this.y = y;
            this.keyHandler = keyHandler;
            this.gameOverImage = ImageIO.read(getClass().getResourceAsStream("/res/messages/game-over.png"));
            this.pressSpaceToContinueImage = ImageIO.read(getClass().getResourceAsStream("/res/messages/press-space-to-continue.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(gameOverImage,this.x, this.y, GameWindow.WINDOW_HEIGHT, GameWindow.WINDOW_WIDTH / 2,null);
        g2.drawImage(pressSpaceToContinueImage,this.x, 450, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT / 3,null);
    }
}