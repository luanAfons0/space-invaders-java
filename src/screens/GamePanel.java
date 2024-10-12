package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entities.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    private static final int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();

    int px = 100;
    int py = 100;
    int speed = 5;

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
    }

    public void handleFPS() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 1000000;

            if(remainingTime < 0){
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
        if(keyHandler.upPressed == true) py -= speed;
        if(keyHandler.leftPressed == true) px -= speed;
        if(keyHandler.rightPressed == true) px += speed;
        if(keyHandler.downPressed == true) py += speed;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(px, py, 50, 50);
        g2.dispose();
    }
}