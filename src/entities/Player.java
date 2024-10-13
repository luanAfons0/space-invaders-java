package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import main.GameWindow;
import main.KeyHandler;

public class Player extends Sprite {
    int speed = 5;
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(GameWindow.WINDOW_WIDTH / 2, GameWindow.WINDOW_HEIGHT - 100, 5, 50, "/res/ship.png");
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.rect = new Rectangle(this.x, this.y, this.size, this.size);
    }

    public void update() {
        if (keyHandler.upPressed == true) {
            if(this.y + speed <= 0) return;
            this.y -= speed;
        };

        if (keyHandler.leftPressed == true) {
            if(this.x + speed <= 0) return;
            this.x -= speed;
        };

        if (keyHandler.rightPressed == true) {
            if((this.x + speed) + this.size >= GameWindow.WINDOW_WIDTH) {
                this.x = GameWindow.WINDOW_WIDTH - this.size;
            };
            this.x += speed;
        };
        
        if (keyHandler.downPressed == true) {
            if((this.y + speed) + this.size >= GameWindow.WINDOW_HEIGHT){
                this.y = GameWindow.WINDOW_HEIGHT - this.size;
            };
            this.y += speed;
        };

        this.updateRectangle();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite,this.x, this.y, this.size, this.size,null);
    }
}