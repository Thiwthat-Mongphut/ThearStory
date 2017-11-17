package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Doors extends Entity{
    private GamePanel game;
    private BufferedImage[] img;
    private boolean checkDoorUp, checkDoorDown, checkDoorCrosse;
    private int DoorFram;

    public Doors(GamePanel game, float x, float y, boolean checkDoorUp , boolean checkDoorDown, boolean checkDoorCrosse, int DoorFram) 
    {
        super(x, y);
        this.game = game;
        this.checkDoorUp = checkDoorUp;
        this.checkDoorDown = checkDoorDown;
        this.checkDoorCrosse = checkDoorCrosse;
        this.DoorFram = DoorFram;
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
    
    public boolean getCROSSE()
    {
        return checkDoorCrosse;
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(img[DoorFram], (int)x, (int) y, null);
    }   
}
