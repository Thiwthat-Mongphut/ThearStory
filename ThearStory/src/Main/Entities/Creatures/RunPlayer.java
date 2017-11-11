package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;

public class RunPlayer extends Player{
    
    private final float walkY;
    private final float downY;
    private boolean Stand = false;
    private long timeUnit = 200000000;
    
    private int count = 0;
    
    public RunPlayer(GamePanel game, float x, float y, float walkY, float downY) {
        super(game, x, y);
        img = Assets.TearImg.get(1);
        this.walkY = walkY;
        this.downY = downY;
        lastTime = System.nanoTime() / timeUnit;
    }
    
    public void tick() {
        // Return Image to Walk
        if(Stand){
            img = Assets.TearImg.get(1);
            walkFrame = 0;
            y = walkY;
            Stand = false;
        }
        // Change Walk Frame
        else if(System.nanoTime() / timeUnit - lastTime >= 1 
                && !startJump && !Stand){
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
                    gravity = 3;
                    count = 0;
                    startJump = false;
                }
            }
        }
        
        // Down
        if(game.getKeyManager().down){
            if(startJump){
                if(gravity <= 12)
                    gravity++;
            }
            else if(!startJump){
                img = Assets.TearImg.get(2);
                walkFrame = 0;
                y = downY;
                Stand = true;
            }
        }
    }
    
    public void increaseJumpPower(){
        if(jumpPower < 10)
            jumpPower++;
    }
    
}
