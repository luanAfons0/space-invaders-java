package main;

import javax.swing.JFrame;

public class GameWindow {
    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 650;

    public GameWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Invaders");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}