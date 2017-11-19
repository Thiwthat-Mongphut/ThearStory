package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Walls extends Entity{

    private GamePanel game;
    
    private BufferedImage[] img;
    private int frame;
    
    public Walls(GamePanel game, float x, float y, int frame) {
        super(x, y);
        img = Assets.Obj.get(5);
        this.frame = frame;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img[frame], (int)x, (int) y, null);
    }
    
}
