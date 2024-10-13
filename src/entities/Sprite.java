package entities;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class Sprite {
    public int x, y, speed, size;
    public BufferedImage sprite; 
    public Rectangle rect;

    public Sprite(int x, int y, int speed, int size, String image){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.rect = new Rectangle(this.x, this.y, this.size, this.size);
        setUpImage(image);
    }

    public void setUpImage(String image){
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRectangle(){
        this.rect.x = this.x;
        this.rect.y = this.y;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite,this.x, this.y, this.size, this.size,null);
    }
}
