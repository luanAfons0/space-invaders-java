package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.GameWindow;

public class Enemy {
    int enemyX, enemyY;
    int speed = 5;
    GamePanel gamePanel;

    public Enemy(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.enemyX = GameWindow.WINDOW_WIDTH / 2;
        this.enemyY = 100;
    }

    public void update() {
        if(enemyX >= GameWindow.WINDOW_WIDTH || enemyX + 50 <= 0){
            enemyY += 60;
            speed = -speed;
        }

        enemyX += speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect(enemyX, enemyY, 50, 50);
    }
}
