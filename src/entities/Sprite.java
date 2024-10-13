package entities;

import java.awt.image.BufferedImage;

public class Sprite {
    public int x, y, speed,size;
    public BufferedImage sprite; 

    public Sprite(int x, int y, int speed, int size){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
    }
}
