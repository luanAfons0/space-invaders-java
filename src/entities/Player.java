package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.GameWindow;
import main.KeyHandler;

public class Player {
    int playerX, playerY, size;
    int speed = 5;
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.playerX = GameWindow.WINDOW_WIDTH / 2;
        this.playerY = GameWindow.WINDOW_HEIGHT - 100;
        this.size = 50;
    }

    public void update() {
        if (keyHandler.upPressed == true) {
            if(playerY + speed <= 0) return;
            playerY -= speed;
        };
        if (keyHandler.leftPressed == true) {
            if(playerX + speed <= 0) return;
            playerX -= speed;
        };
        if (keyHandler.rightPressed == true) {
            if((playerX + speed) + size >= GameWindow.WINDOW_WIDTH) {
                playerX = GameWindow.WINDOW_WIDTH - size;
            };
            playerX += speed;
        };
        if (keyHandler.downPressed == true) {
            if((playerY + speed) + size >= GameWindow.WINDOW_HEIGHT){
                playerY = GameWindow.WINDOW_HEIGHT - size;
            };
            playerY += speed;
        };
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, size, size);
    }
}