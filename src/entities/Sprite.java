package entities;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Sprite {
    public int x, y, speed,size;
    public BufferedImage sprite; 
    public Rectangle rect;

    public Sprite(int x, int y, int speed, int size, String image){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
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
}
