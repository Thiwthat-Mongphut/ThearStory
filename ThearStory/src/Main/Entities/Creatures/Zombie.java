package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Objects.Doors;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Zombie extends Creature{
    
    protected GamePanel game;

    public Zombie(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        
    }
    
    public abstract void setvX(float vX);
    
    public abstract void setvY(float vY);
    
    public abstract float GetX();
    
    public abstract float GetY();
    
    public abstract void setrighRoom(boolean righRoom);


    @Override
    public void render(Graphics g) {

    }
    
}
