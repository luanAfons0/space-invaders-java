package entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    private long timeNow;
    private static final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void handleRepaint() {
        timeNow = System.currentTimeMillis();

        timeNow = (1000 / FPS) - (System.currentTimeMillis() - timeNow);

        if (timeNow > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(timeNow);
            } catch (InterruptedException e) {
                repaint();
            }
        }
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            handleRepaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, 16, 16);
        g2.dispose();
    }
}