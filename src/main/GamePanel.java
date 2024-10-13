package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

import entities.Enemy;
import entities.Player;
import entities.Projectile;
import screens.BackGround;

public class GamePanel extends JPanel implements Runnable {
    public boolean running;
    Thread gameThread;
    private static final int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    public BackGround backGround;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.backGround = new BackGround(0, 0, "/res/space.jpg");

        for(int i = 0; i < 10; i++){
            enemies.add(new Enemy((50 * i),5,this));
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.running = true;
    }

    public void handleFPS() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 1000000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);

            nextDrawTime += drawInterval;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            handleFPS();
        }
    }

    public void update() {
        if(this.running == false) return;
        if(enemies.isEmpty()) return;

        List<Enemy> deadEnemies = new ArrayList<>();
        List<Projectile> deadProjectiles = new ArrayList<>();

        player.update(projectiles);

        for(Enemy enemy: enemies){
            enemy.update();

            if(player.rect.intersects(enemy.rect)){
                this.running = false;
            }
        }

        for(Projectile projectile: projectiles){
            projectile.update();

            for(Enemy enemy: enemies){
                if(projectile.rect.intersects(enemy.rect)){
                    deadEnemies.add(enemy);
                    deadProjectiles.add(projectile) ;
                }
    
                if(projectile.outOfWindow()){
                    deadProjectiles.add(projectile);
                }
            }
        }
        
        enemies.removeAll(deadEnemies);
        projectiles.removeAll(deadProjectiles);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        backGround.draw(g2);

        player.draw(g2);

        for(Projectile projectile: projectiles){
            projectile.draw(g2);
        }

        for(Enemy enemy: enemies){
            enemy.draw(g2);
        }

        g2.dispose();
    }
}