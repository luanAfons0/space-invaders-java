package entities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import main.GameWindow;
import main.KeyHandler;
import java.util.List;
import main.GamePanel;

public class Player extends Sprite {
    Timer timer;
    int speed = 5;
    GamePanel gamePanel;
    KeyHandler keyHandler;
    boolean delayShoot = false;

    ActionListener releaseShootButton = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            delayShoot = false;
        }
    };

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(GameWindow.WINDOW_WIDTH / 2, GameWindow.WINDOW_HEIGHT - 100, 5, 50, "/res/sprites/ship.png");
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        timer = new Timer(300, releaseShootButton);
    }

    public void update(List<Projectile> projecties) {
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

        if(keyHandler.spacePressed && !delayShoot){
            projecties.add(new Projectile(x, y, 5, 15, "/res/sprites/projectile.png"));
            delayShoot = true;
        } else {
            timer.start();
        }

        this.updateRectangle();
    }
}