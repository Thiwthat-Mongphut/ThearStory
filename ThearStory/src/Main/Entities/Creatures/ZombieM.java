/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
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
    protected int walkFrame = 0;
    private float vX, vY, speedUP = 1;
    private boolean righRoom, UP, DOWN;
    
    public ZombieM(GamePanel game, float x, float y) {
        super(game, x, y);
        img = Assets.ZombieImg.get(0);
    }

    @Override
    public void tick() {
        // UPorDown
        if(vY == y)
        {
            if(vX >= x)
            {
 
            }
            else if(vX <= x)
            {

            }
        }
        // End
        
        // Down
        else if(vY > y)
        {
            // Top Room
            if(y == 75)
            {
                // DOOR TOP 1 Left
                if(x - 85 <= x - 255 && !righRoom)
                {
                    if(x > 85)
                    {
                        x -= speedUP;
                    }
                    else if(x < 85)
                    {
                        x += speedUP;
                    }
                    if(x == 75 + 10)
                    {
                        DOWN = true;
                    }
                }
                // DOOR TOP 2 Left
                else if(x - 85 > x - 255 && !righRoom)
                {
                    if(x > 255)
                    {
                        x -= speedUP;
                    }
                    else if(x < 255)
                    {
                        x += speedUP;
                    }
                    if(x == 255)
                    {
                        DOWN = true;
                    }
                }
                // DOOR TOP 1 Right
                else if(x - 660 <= x - 755 && righRoom)
                {
                    if(x > 660)
                    {
                        x -= speedUP;
                    }
                    else if(x < 660)
                    {
                        x += speedUP;
                    }
                    if(x == 660)
                    {
                        DOWN = true;
                    }
                }
                // DOOR TOP 2 Right
                else if(x - 660 > x - 755 && righRoom)
                {
                    if(x > 755)
                    {
                        x -= speedUP;
                    }
                    else if(x < 755)
                    {
                        x += speedUP;
                    }
                    if(x == 755)
                    {
                        DOWN = true;
                    }
                }
            }
            // Top End
            
            // Mid Room
            else if(y == 235)
            {
                // DOOR Mid 1 Left
                if(x - 10 <= x - 180 && !righRoom)
                {
                    if(x > 10)
                    {
                        x -= speedUP;
                    }
                    if(x == 10)
                    {
                        DOWN = true;
                    }
                }
                // DOOR Mid 2 Left
                else if(x - 10 > x - 180 && !righRoom)
                {
                    if(x > 180)
                    {
                        x -= speedUP;
                    }
                    else if(x < 180)
                    {
                        x += speedUP;
                    }
                    if(x == 180)
                    {
                        DOWN = true;
                    }
                }
                // DOOR Mid 3 Right
                else if(x - 490 <= x - 585 && righRoom)
                {
                    if(x > 490)
                    {
                        x -= speedUP;;
                    }
                    else if(x < 490)
                    {
                        x += speedUP;
                    }
                    if(x == 490)
                    {
                        DOWN = true;
                    }
                }
                // DOOR Mid 4 Right
                else if(x - 490 > x - 585 && righRoom)
                {
                    if(x > 585)
                    {
                        x -= speedUP;
                    }
                    else if(x < 585)
                    {
                        x += speedUP;
                    }
                    if(x == 585)
                    {
                        DOWN = true;
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
                // Downstairs Room 
                if(x - 10 <= x - 180 && x - 480 <= x - 180 )
                {
                    
                }
            }
            //Downstairs End
            
            // Mid
            else if(y == 235)
            {
                
            }
            // Mid End
        }
        // UP End
    }
        
    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int)x, (int)y, 100, 57, null);
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
