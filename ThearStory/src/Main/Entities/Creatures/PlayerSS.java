package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlayerSS extends Creature
{
    protected GamePanel game;
    protected BufferedImage[] img;
    protected int walkFrame = 0;
    protected boolean enterDoor, rightRoom = false;
    private long lastTime, timeUnit = 300000000;

    public PlayerSS(GamePanel game, float x, float y, boolean enterDoor) {
        super(x, y);
        this.game = game;
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.TearImg.get(1);
        this.enterDoor = enterDoor;
    }

    @Override
    public void tick() 
    {
       if(x <= 0)
       {
           x = 0;
       }
       else if(x >= 720)
       {
           x = 720;
       }
       
       if(x >= 270 && y != 395 && !rightRoom)
       {
           x = 270;
       }
       else if(x <= 410 && y != 395 && rightRoom)
       {
           x = 410;
       }
       
       if(game.getKeyManager().left){
            x -= speed;
            if(System.nanoTime() / timeUnit - lastTime >= 1)
            {
                if(walkFrame >= 2){
                    walkFrame = 0;
                    lastTime = System.nanoTime() / timeUnit;
                }
                else{
                    walkFrame++;
                    lastTime = System.nanoTime() / timeUnit;
                }
            }
            
            img = Assets.TearImg.get(0);   
        }
            
        if(game.getKeyManager().right){
            x += speed;
            if(System.nanoTime() / timeUnit - lastTime >= 1)
            {
                if(walkFrame >= 2){
                    walkFrame = 0;
                    lastTime = System.nanoTime() / timeUnit;
                    }
                else{
                    walkFrame++;
                    lastTime = System.nanoTime() / timeUnit;
                    }
            }
            img = Assets.TearImg.get(1);   
        }
        
        if(game.getKeyManager().Enter)
        {
            enterDoor = true;
        }
        // Crawl
        /*if(game.getKeyManager().down)
        {
            
        }*/
    }
    
    public boolean getEnterDoor()
    {
        return enterDoor;
    }
    
    public void setEnterDoor(boolean enterDoor)
    {
        this.enterDoor = enterDoor;
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public void setY(int dis)
    {
        y = y + dis;
    }
    
    public void setX(int dis)
    {
        x = dis;
    }
    
    public void setRightRoom(boolean rightRoom)
    {
        this.rightRoom = rightRoom;
    }
    
    public boolean getRightRoom()
    {
        return rightRoom;
    }
    
    
    @Override
    public void render(Graphics g) 
    {
        g.drawImage(img[walkFrame], (int)x, (int)y, 100, 57, null);
    }
}
