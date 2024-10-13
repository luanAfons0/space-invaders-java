package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import main.GameWindow;

public class Enemy extends Sprite{
    GamePanel gamePanel;

    public Enemy(GamePanel gamePanel) {
        super(GameWindow.WINDOW_WIDTH / 2, 100, 5, 50, "/res/alien.png");
        this.gamePanel = gamePanel;
        this.rect = new Rectangle(this.x, this.y, this.size, this.size);
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
