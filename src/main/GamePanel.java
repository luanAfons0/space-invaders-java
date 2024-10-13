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
import screens.GameOver;
import screens.MainMenu;
import screens.MissionSuccess;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

    public boolean running, gameOver, mainMenu, missionSuccess;
    public BackGround backGround;
    public GameOver gameOverScreen;
    public MainMenu mainMenuScreen;
    public MissionSuccess missionSuccessScreen;

    private static final int FPS = 60;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();

    public GamePanel() {
        // Initialize necessary informations
        this.setPreferredSize(new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.gameOver = false;
        this.mainMenu = true;
        this.missionSuccess = false;
        this.gameOverScreen = new GameOver(0, 0);
        this.mainMenuScreen = new MainMenu(0, 0);
        this.missionSuccessScreen = new MissionSuccess(0, 0);
        this.backGround = new BackGround(0, 0, "/res/sprites/space.jpg");

        // Setup all entities
        resetGame();
    }

    public void startGameThread() {
        // Start the game thead
        gameThread = new Thread(this);
        gameThread.start();
        this.running = true;
    }

    public void handleFPS() {
        // Limit the game to 60FPS
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
        // Gameloop
        while (gameThread != null) {
            update();
            repaint();
            handleFPS();
        }
    }

    public void resetGame(){
        // Reset gameover variable
        gameOver = false;
        missionSuccess = false;

        // Reset enemies
        enemies.removeAll(enemies);
        for(int i = 0; i < 10; i++){
            enemies.add(new Enemy((50 * i),5,this));
        }

        // Reset all projectiles
        projectiles.removeAll(projectiles);

        // Reset player
        player = new Player(this, keyHandler);
    }

    public void update() {
        // Main menu related
        if(mainMenu == true) {
            if(keyHandler.spacePressed) {
                mainMenu = false;
            }
            return;
        }

        // Game over and success related
        if(gameOver == true){
            if(keyHandler.spacePressed){
                this.resetGame();
            }
            return;
        }

        // Mission success related
        if(missionSuccess == true){
            if(keyHandler.spacePressed){
                this.resetGame();
            }
            return;
        }

        // Create list to dispose elementes
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Projectile> deadProjectiles = new ArrayList<>();

        // Update player infos
        player.update(projectiles);

        // Check if there is enemies alive
        if(enemies.isEmpty()) missionSuccess = true;

        // Handle enemy update
        for(Enemy enemy: enemies){
            enemy.update();

            if(player.rect.intersects(enemy.rect)){
                this.gameOver = true;
            }
        }

        // Handle projectiles update
        for(Projectile projectile: projectiles){
            projectile.update();

            // Handle kill
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
        
        // Handle dispatch of unnecessary objects
        enemies.removeAll(deadEnemies);
        projectiles.removeAll(deadProjectiles);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        backGround.draw(g2);

        // Game over related
        if(gameOver == true){
            gameOverScreen.draw(g2);
            return;
        }

        // Main menu related
        if(mainMenu == true){
            mainMenuScreen.draw(g2);
            return;
        }

        // Mission success related
        if(missionSuccess == true){
            missionSuccessScreen.draw(g2);
            return;
        }

        // Draw player
        player.draw(g2);

        // Draw projectiles
        for(Projectile projectile: projectiles){
            projectile.draw(g2);
        }

        // Draw Enemies
        for(Enemy enemy: enemies){
            enemy.draw(g2);
        }

        g2.dispose();
    }
}