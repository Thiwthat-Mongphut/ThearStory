package Main.Backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Background {

    protected BufferedImage img;
    protected float x, y;
    
    public Background(BufferedImage img, float x, float y){
        this.img = img;
        this.x = x;
        this.y = y;
    }
    
    public abstract void tick();

    public abstract void render(Graphics g);
    
    public abstract void move(float x, float y);
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public int getWidth(){
        return img.getWidth();
    }

}
