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
        img = Assets.ZombieImg.get(0);
        walkFrame = 0;
    }

    @Override
    public void tick() {
        // UPorDown
        /*if(vY == y)
        {
            if(vX >= x)
            {
 
            }
            else if(vX <= x)
            {

            }
        }*/
        // End
        
        // Down
        if(vY > y)
        {
            // Top Room
            if(y == 75)
            {
                // DOOR TOP 1 Left
                if(x - 75 <= x - 245 && !righRoom)
                {
                    if(x > 75)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 75)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 75)
                    {
                        y += 160;
                    }
                }
                // DOOR TOP 2 Left
                else if(x - 75 > x - 245 && !righRoom)
                {
                    if(x > 245)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 245)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 245)
                    {
                        y += 160;
                    }
                }
                // DOOR TOP 1 Right
                else if(x - 650 <= x - 745 && righRoom)
                {
                    if(x > 650)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 650)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 650)
                    {
                        y += 160;
                    }
                }
                // DOOR TOP 2 Right
                else if(x - 650 > x - 745 && righRoom)
                {
                    if(x > 745)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 745)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 745)
                    {
                        y += 160;
                    }
                }
            }
            // Top End
            
            // Mid Room
            else if(y == 235)
            {
                // DOOR Mid 1 Left
                if(x - 0 <= x - 170 && !righRoom)
                {
                    if(x > 0)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 0)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 0)
                    {
                        y += 160;
                    }
                }
                // DOOR Mid 2 Left
                else if(x > x - 170 && !righRoom)
                {
                    if(x > 170)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 170)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 170)
                    {
                        y += 160;
                    }
                }
                // DOOR Mid 3 Right
                else if(x - 480 <= x - 575 && righRoom)
                {
                    if(x > 480)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 480)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 480)
                    {
                        y += 160;
                    }
                }
                // DOOR Mid 4 Right
                else if(x - 480 > x - 575 && righRoom)
                {
                    if(x > 575)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 575)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 575)
                    {
                        y += 160;
                    }
                }
            }
            //Mid End
        }
        // Down End
        
        // UP
        else if(vY < y)
        {
            // Downstairs
            if(y == 395)
            {
                // Downstairs Door 1
                if(x <= x - 170)
                {
                    if(x > 0)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 0)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 0)
                    {
                        y -= 160;
                    }
                }
                // Downstairs Door 2
                else if(x - 170 <= x - 470)
                {
                    if(x > 170)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 170)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 170)
                    {
                        y -= 160;
                    }
                }
                // Downstairs Door 3
                else if(x - 470 <= x - 575)
                {
                    if(x > 470)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 470)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 480)
                    {
                        y -= 160;
                    }
                }
                // Downstairs Door 4
                else
                {
                    if(x > 575)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 575)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 575)
                    {
                        y -= 160;
                    }
                }
            }
            //Downstairs End
            
            // Mid Room
            else if(y == 235)
            {
                // DOOR Mid 1 Left
                if(x - 0 <= x - 170 && !righRoom)
                {
                    if(x > 0)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 0)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 0)
                    {
                        y -= 160;
                    }
                }
                // DOOR Mid 2 Left
                else if(x > x - 170 && !righRoom)
                {
                    if(x > 170)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 170)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 170)
                    {
                        y -= 160;
                    }
                }
                // DOOR Mid 3 Right
                else if(x - 480 <= x - 575 && righRoom)
                {
                    if(x > 480)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 480)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 480)
                    {
                        y -= 160;
                    }
                }
                // DOOR Mid 4 Right
                else if(x - 480 > x - 575 && righRoom)
                {
                    if(x > 575)
                    {
                        x -= speedUP;
                        walkLeft();
                    }
                    else if(x < 575)
                    {
                        x += speedUP;
                        walkRight();
                    }
                    if(x == 575)
                    {
                        y -= 160;
                    }
                }
            }
            //Mid End
        }
        // UP End
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
