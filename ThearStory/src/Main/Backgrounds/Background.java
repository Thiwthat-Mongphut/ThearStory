package Main.Backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Background {
    
    // Create All Background
    public static ArrayList<Background> background = new ArrayList<Background>();
    public static Background cloud = new Cloud(0,0);
    public static Background sky = new Sky(0,0);
    public static Background mountain = new Mountain(0,0);
    public static Background sunset = new Sunset(0,0);
    
    protected BufferedImage img;
    protected float x, y;
    
    public Background(BufferedImage img, float x, float y){
        this.img = img;
        this.x = x;
        this.y = y;
        background.add(cloud);
        background.add(sky);
        background.add(mountain);
        background.add(sunset);
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
