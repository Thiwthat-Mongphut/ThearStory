package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;

public class RunPlayer extends Player{
    
    public RunPlayer(GamePanel game, float x, float y) {
        super(game, x, y);
        img = Assets.TearImg.get(1);
    }
    
    public void tick() {
        if(System.nanoTime() / 1000000000 - lastTime >= 1){
            if(walkFrame >= 2){
                walkFrame = 0;
                lastTime = System.nanoTime() / 1000000000;
            }
            else{
                walkFrame++;
                lastTime = System.nanoTime() / 1000000000;
            }
        }
        
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
                    startJump = false;
                }
            }
        }
            
    }
}
