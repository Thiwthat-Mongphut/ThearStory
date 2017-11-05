package Main.Entities.Creatures;


import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private GamePanel game;
    private BufferedImage[] img;
    private int walkFrame;
    private long lastTime;
    
    // Jump Variables
    private int jumpWidth = 0;
    private int jumpHeight = 100;
    private boolean jumpStatus = false;
    private int gravity = 2;
    private int jumpPower = 5;
    private boolean startJump = false;
    
    public Player(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
        img = Assets.TearImg.get(1);
        lastTime = System.nanoTime();
    }

    @Override
    public void tick() {
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
                else if (jumpWidth <= 1) {
                    jumpWidth -= jumpPower + gravity;
                    y += jumpPower + gravity;
                    startJump = false;
                }
            }
        }
        
        if(game.getKeyManager().left){
            x -= speed;
            if((System.nanoTime() - lastTime) / 100000000 > 1 && !startJump){
                if(walkFrame >= 2){
                    walkFrame = 0;
                    lastTime = System.nanoTime();
                }
            else{
                walkFrame++;
                lastTime = System.nanoTime();
                }
            }
            
            img = Assets.TearImg.get(0);   
        }
            
        if(game.getKeyManager().right){
            x += speed;
            if((System.nanoTime() - lastTime) / 100000000 > 1 && !startJump){
                if(walkFrame >= 2){
                    walkFrame = 0;
                    lastTime = System.nanoTime();
                    }
                else{
                    walkFrame++;
                    lastTime = System.nanoTime();
                    }
            }
            img = Assets.TearImg.get(1);   
        }
            
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int) x, (int) y, null);
    }
    
}
