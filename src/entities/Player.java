package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.GameWindow;
import main.KeyHandler;

public class Player {
    int playerX, playerY;
    int speed = 5;
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.playerX = GameWindow.WINDOW_WIDTH / 2;
        this.playerY = 100;
    }

    public void update() {
        if (keyHandler.upPressed == true) playerY -= speed;
        if (keyHandler.leftPressed == true) playerX -= speed;
        if (keyHandler.rightPressed == true) playerX += speed;
        if (keyHandler.downPressed == true) playerY += speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, 50, 50);
    }
}