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
    private long lastTime, timeUnit = 300000000;
    
    // Variable ladder
    protected boolean checkLadder, checkLadderUp, checkLadderDown;
    protected float xLadder, yLadder;

    public PlayerSS(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.TearImg.get(1);
    }

    @Override
    public void tick() 
    {
       if(x <= 0)
       {
           x = 0;
       }
       if(x >= 300)
       {
           x = 300;
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
        
        
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(img[walkFrame], (int)x, (int)y, null);
    }
    
}
