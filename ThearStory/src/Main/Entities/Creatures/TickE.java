package Main.Entities.Creatures;

import Main.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class TickE extends Creature{
    
    protected GamePanel game;
    protected BufferedImage[] img;
    
    TickE(GamePanel game, float x, float y){
        super(x, y);
    }

    @Override
    public void tick() {
    
    }

    @Override
    public void render(Graphics g) {
 
    }
    
}
