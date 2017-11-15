package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Doors extends Entity{
    private GamePanel game;
    private BufferedImage[] img;
    private boolean UPorDOWN;

    public Doors(GamePanel game, float x, float y, boolean UPorDOWN) 
    {
        super(x, y);
        this.game = game;
        this.UPorDOWN = UPorDOWN;
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
    
    public boolean getUPorDOWN()
    {
        return UPorDOWN;
    }

    @Override
    public void render(Graphics g) 
    {

    }   
}
