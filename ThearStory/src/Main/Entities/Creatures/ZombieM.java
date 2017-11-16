/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import static Main.Graphics.Assets.ZombieImg;
import Main.Objects.Doors;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author Korn
 */
public class ZombieM extends Zombie{

    
    protected BufferedImage[] img;
    protected int walkFrame;
    private float vX, vY, speedUP = 1;
    private boolean righRoom;
    private long timeUnit = 300000000, lastTime;
    
    public ZombieM(GamePanel game, float x, float y) {
        super(game, x, y);
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.ZombieImg.get(1);
        walkFrame = 0;
    }

    @Override
    public void tick() 
    {   // map wide
        if(x <= 0)
            x = 0;
        else if(x >= 720)
            x = 720;
        // map wide
        
        if(vY > y)
        {
            if(vX > x)
            walkRight();
        }
    }
    
    public void walkLeft()
    {
        if(System.nanoTime() / timeUnit - lastTime >= 1)
        {
            if(walkFrame >= 9)
            {
                walkFrame = 0;
                lastTime = System.nanoTime() / timeUnit;
            }
            else
            {
                walkFrame++;
                lastTime = System.nanoTime() / timeUnit;
            }
        }
        img = Assets.ZombieImg.get(0);   
    }
    
    public void walkRight()
    {
        if(System.nanoTime() / timeUnit - lastTime >= 1)
        {
            if(walkFrame >= 9)
            {
                walkFrame = 0;
                lastTime = System.nanoTime() / timeUnit;
            }
            else
            {
                walkFrame++;
                lastTime = System.nanoTime() / timeUnit;
            }
        }
        img = Assets.ZombieImg.get(1);   
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int)x, (int)y, null);
    }

    @Override
    public void setvX(float vX) {
        this.vX = vX;
    }

    @Override
    public void setvY(float vY) {
        this.vY = vY;
    }

    @Override
    public float GetX() {
        return x;
    }

    @Override
    public float GetY() {
        return y;
    }

    @Override
    public void setrighRoom(boolean righRoom) {
        this.righRoom = righRoom;
    }
}
