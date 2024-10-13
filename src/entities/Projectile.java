package entities;

public class Projectile extends Sprite {
    public Projectile(int x, int y, int speed, int size, String image){
        super(x, y, speed, size, image);
    }

    public void update() {
        y -= this.speed;
        this.updateRectangle();
    }

    public boolean outOfWindow(){
        return y <= 0 ? true : false;
    }
}