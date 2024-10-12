package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entities.Enemy;
import entities.Player;

public class GamePanel extends JPanel implements Runnable {
    public boolean running;
    Thread gameThread;
    private static final int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    Enemy simpleEnemy = new Enemy(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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
        if(this.running){
            player.update();
            simpleEnemy.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        simpleEnemy.draw(g2);

        g2.dispose();
    }
}