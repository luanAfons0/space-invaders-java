package entities;

import java.awt.Graphics2D;
import main.GamePanel;
import main.GameWindow;

public class Enemy extends Sprite{
    GamePanel gamePanel;

    public Enemy(int initialX, int initialY, GamePanel gamePanel) {
        super(initialX, initialY, 5, 50, "/res/alien.png");
        this.gamePanel = gamePanel;
    }

    public void update() {
        if(this.x >= GameWindow.WINDOW_WIDTH || this.x + 50 <= 0){
            this.y += 60;
            speed = -speed;
        }

        this.x += speed;
        this.updateRectangle();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite,this.x, this.y, this.size, this.size,null);
    }
}
