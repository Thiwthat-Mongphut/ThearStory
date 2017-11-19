package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Doors extends Entity{
    
    private GamePanel game;
    private BufferedImage[] img;
    private boolean checkDoorUp, checkDoorDown, checkDoorCross;
    private int doorFrame;

    public Doors(GamePanel game, float x, float y, boolean checkDoorUp , boolean checkDoorDown, boolean checkDoorCross, int doorFrame) 
    {
        super(x, y);
        this.game = game;
        this.checkDoorUp = checkDoorUp;
        this.checkDoorDown = checkDoorDown;
        this.checkDoorCross = checkDoorCross;
        this.doorFrame = doorFrame;
        img = Assets.Obj.get(5);
        
    }

    @Override
    public void tick() 
    {

    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public boolean getUP()
    {
        return checkDoorUp;
    }
    
    public boolean getDOWN()
    {
        return checkDoorDown;
    }
    
    public boolean getCROSS()
    {
        return checkDoorCross;
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(img[doorFrame], (int)x, (int) y, null);
    }   
}
