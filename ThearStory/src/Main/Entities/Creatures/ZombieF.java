package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ZombieF extends Creature{
    
    protected GamePanel game;
    protected BufferedImage[] img;
    protected PlayerSS player;
    
    protected int walkFrame = 0;
    private long lastTime, timeUnit = 100000000;
    
    private float walkSpeed;
    
    private boolean firstWalk = true, ProtectPlayer = false;
    private float posYPlayer;
    
    public ZombieF(GamePanel game, float x, float y, float walkSpeed){
        super(x, y);
        this.game = game;
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.ZombieImg.get(3); // เดินขวา
        this.walkSpeed = walkSpeed;
    }

    @Override
    public void tick() {
        if(System.nanoTime() / timeUnit - lastTime >= 1){ // Zombie move
            if(walkFrame >= 9)
                walkFrame = 0;
            else
                walkFrame++;
            lastTime = System.nanoTime() / timeUnit;
        }
        
        if(x < 330){
            if(firstWalk){
                x += walkSpeed;
                if(x >= 325){ // ชนขวา left room
                    firstWalk = false;
                    x -= walkSpeed;

                    img = Assets.ZombieImg.get(2); // เดินซ้าย
                }
            }
            
            else if(!firstWalk){
                x -= walkSpeed;
                if(x <= 0){  // ชนซ้าย left room
                    firstWalk = true;
                    x -= walkSpeed;  
                    
                    img = Assets.ZombieImg.get(3); // เดินขวา
                }
            }
            
            // Door
            if(posYPlayer > y && y == 67){ // เตี้ยอยู่ล่าง
                // ซอมบี้เดินลง
                if(x == 75)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
                else if(x == 245)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
            else if(posYPlayer > y && y == 227){ // เตี้ยอยู่ล่างสุด
                // ซอมบี้เดินลง
                if(x == 0)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
                else if(x == 170)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
            else if(posYPlayer < y && y == 227){ // เตี้ยอยู่บน
                // ซอมบี้เดินขึ้น
                if(x == 75)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
                else if(x == 245)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
            }
            else if(posYPlayer < y && y == 387){
                // ซอมบี้เดินขึ้น
                if(x == 0)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
                else if(x == 170)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }                 
            }
        }
        if(x >  330){
            if(firstWalk){
                x += walkSpeed;
                if(x >= 780){ // ชนขวา right room
                    firstWalk = false;
                    x -= walkSpeed;
                    
                    img = Assets.ZombieImg.get(2); // เดินซ้าย
                }
            
            }
            else if(!firstWalk){
                x -= walkSpeed;
                if(x <= 415){  // ชนซ้าย right room
                    firstWalk = true;
                    x -= walkSpeed;      
                    
                    img = Assets.ZombieImg.get(3); // เดินขวา
                }
            }
            // Door
            if(posYPlayer > y && y == 67){ // เตี้ยอยู่ล่าง
                // ซอมบี้เดินลง
                if(x == 575)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
                else if(x == 745)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
            else if(posYPlayer > y && y == 227){ // เตี้ยอยู่ล่างสุด
                if(x == 480)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
                else if(x == 650)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
            else if(posYPlayer < y && y == 227){ // เตี้ยอยู่บน
                // ซอมบี้เดินขึ้น
                if(x == 575)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
                else if(x == 745)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }  
            }
            else if(posYPlayer < y && y == 387){
                // ซอมบี้เดินขึ้น
                if(x == 480)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
                else if(x == 650)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }               
            }    
        } 
    }
    
    public float getPositionPlayer(float pos){
        posYPlayer = pos;
        return posYPlayer;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
     public int getWidth(){
        return img[walkFrame].getWidth();
    }
     
    public boolean getProtectPlayer(){
        return ProtectPlayer;
    }
          
    public void setProtectPlayer(boolean ProtectPlayer){
        this.ProtectPlayer = ProtectPlayer;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int)x, (int)y, null);
    }
    
}
