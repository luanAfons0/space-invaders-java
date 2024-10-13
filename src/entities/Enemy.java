package entities;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.GameWindow;

public class Enemy extends Sprite{
    GamePanel gamePanel;

    public Enemy(GamePanel gamePanel) {
        super(GameWindow.WINDOW_WIDTH / 2, 100, 5, 50);
        this.gamePanel = gamePanel;
        getEnemyImage();
    }

    public void getEnemyImage(){
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/res/alien.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(this.x >= GameWindow.WINDOW_WIDTH || this.x + 50 <= 0){
            this.y += 60;
            speed = -speed;
        }

        this.x += speed;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite,this.x, this.y, this.size, this.size,null);
    }
}
