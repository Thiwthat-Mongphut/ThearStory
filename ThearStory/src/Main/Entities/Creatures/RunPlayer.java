package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;

public class RunPlayer extends Player{
    
    private boolean Stand = false;
    
    public RunPlayer(GamePanel game, float x, float y) {
        super(game, x, y);
        img = Assets.TearImg.get(1);
        lastTime = System.nanoTime() / 200000000;
    }
    
    public void tick() {
        if(Stand){
            img = Assets.TearImg.get(1);
            walkFrame = 0;
            y = 353;
            Stand = false;
        }
        // Change Walk Frame
        else if(System.nanoTime() / 200000000 - lastTime >= 1 
                && !startJump && !Stand){
            if(walkFrame >= 2){
                walkFrame = 0;
                lastTime = System.nanoTime() / 200000000;
            }
            else{
                walkFrame++;
                lastTime = System.nanoTime() / 200000000;
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
                    jumpWidth -= jumpPower + gravity;
                    y += jumpPower + gravity;
                }
                else if (jumpWidth <= 0) {
                    gravity = 3;
                    startJump = false;
                }
            }
        }
        
        // Down
        if(game.getKeyManager().down){
            if(startJump && !jumpStatus){
                gravity++;
            }
            else if(!startJump){
                img = Assets.TearImg.get(2);
                walkFrame = 0;
                y = 385;
                Stand = true;
            }
        }

    }
    
}
