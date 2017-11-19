package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RunPlayer extends Creature{
    
    private GamePanel game;
    
    private BufferedImage[] img;
    private int walkFrame;
    private long lastTime;
    private long timeUnit = 300000000;
    
    // Jump Variables
    private int jumpWidth = 0;
    private int jumpHeight = 230;
    private boolean jumpStatus = false;
    private int gravity = 3;
    private int jumpPower = 5;
    private boolean startJump = false;
    
    private final float walkY;
    private final float downY;
    private boolean stand = false;
    
    private int count = 0;
    
    public RunPlayer(GamePanel game, float x, float y, float walkY, float downY) {
        super(x, y);
        this.game = game;
        img = Assets.TearImg.get(1);
        this.walkY = walkY;
        this.downY = downY;
        health = 1;
        jumpPower = 5;
        gravity = 4;
        lastTime = System.nanoTime() / timeUnit;
    }
    
    @Override
    public void tick() {
        // Return Image to Walk
        if(stand){
            img = Assets.TearImg.get(1);
            walkFrame = 0;
            y = walkY;
            stand = false;
        }
        // Change Walk Frame
        else if(System.nanoTime() / timeUnit - lastTime >= 1 
                && !startJump && !stand){
            if(walkFrame >= 2){
                walkFrame = 0;
                lastTime = System.nanoTime() / timeUnit;
            }
            else{
                walkFrame++;
                lastTime = System.nanoTime() / timeUnit;
            }
        }
        
        // Jump Algorithm
        if(game.getKeyManager().up){
            if (jumpStatus != true && jumpWidth <= 0){
                jumpStatus = true;
                startJump = true;
                gravity = 4;
                count = 0;
            } 
        }
        
        if(startJump){
            if(jumpStatus){
                if(jumpWidth < jumpHeight){
                    if(count >= 10){
                        if(gravity > 0){
                            gravity--;
                            count = 0;
                        }
                    }
                    else
                        count++;  
                    jumpWidth += jumpPower + gravity;
                    y -= jumpPower + gravity;
                }
                else if(jumpWidth >= jumpHeight){
                    jumpWidth += jumpPower + gravity;
                    y -= jumpPower + gravity;
                    count = 0;
                    jumpStatus = false;
                }
            }
            else if(!jumpStatus){
                if (jumpWidth > 0) {
                    if(count >= 10){
                        if(gravity <= 7){
                            gravity++;
                            count = 0;
                        }
                            
                    }
                    else
                        count++;
                    jumpWidth -= jumpPower + gravity;
                    y += jumpPower + gravity;
                }
                else if (jumpWidth <= 0) {
                    y = walkY;
                    startJump = false;
                }
            }
        }
        
        // Down
        if(game.getKeyManager().down && !jumpStatus){
            if(startJump){
                if(gravity <= 12)
                    gravity++;
            }
            else if(!startJump){
                img = Assets.TearImg.get(2);
                walkFrame = 0;
                y = downY;
                stand = true;
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int) x, (int) y, null);
    }
    
    public void increaseJumpPower(){
        if(jumpPower < 8)
            jumpPower += 1;
    }
    
    public void setJumpPower(int i){
        jumpPower = i;
    }
    
    public void reduceHealth(int i){
        health -= i;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public float getWidth(){
        return img[walkFrame].getWidth();
    }
    
    public float getHeight(){
        return img[walkFrame].getHeight();
    }
     
    public boolean getStatus(){
        if(health <= 0)
            return false;
        return true;
    }
    
}
