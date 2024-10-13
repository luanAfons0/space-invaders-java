package screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GameWindow;

public class MissionSuccess {
      public int x, y;
    private BufferedImage missionSuccessImage, pressSpaceToRestart;
    
    public MissionSuccess(int x, int y){
        try {
            this.x = x;
            this.y = y;
            this.missionSuccessImage = ImageIO.read(getClass().getResourceAsStream("/res/messages/mission-sucess.png"));
            this.pressSpaceToRestart = ImageIO.read(getClass().getResourceAsStream("/res/messages/press-space-to-restart.png"));
        } catch (Exception e) {
            e.setStackTrace(null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(missionSuccessImage,this.x, this.y, GameWindow.WINDOW_HEIGHT, GameWindow.WINDOW_WIDTH / 2,null);
        g2.drawImage(pressSpaceToRestart,this.x, 450, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT / 3,null);
    }
}
