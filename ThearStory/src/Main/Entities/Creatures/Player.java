package Main.Entities.Creatures;


import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    protected GamePanel game;
    protected BufferedImage[] img;
    protected int walkFrame;
    protected long lastTime;
    private long timeUnit = 300000000;
    
    // Jump Variables
    protected int jumpWidth = 0;
    protected int jumpHeight = 250;
    protected boolean jumpStatus = false;
    protected int gravity = 3;
    protected int jumpPower = 7;
    protected boolean startJump = false;
    
    public Player(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
        lastTime = System.nanoTime() / 1000000000;
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
        if(game.getKeyManager().left){
            x -= speed;
            if(System.nanoTime() / timeUnit - lastTime >= 1 && !startJump){
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
            if(System.nanoTime() / timeUnit - lastTime >= 1 && !startJump){
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
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int) x, (int) y, null);
    }
    
}
