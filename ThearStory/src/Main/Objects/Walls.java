package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Walls extends Entity{

    private GamePanel game;
    
    private BufferedImage[] img;
    private int Fram;
    
    public Walls(GamePanel game, float x, float y, int Fram) {
        super(x, y);
        img = Assets.Obj.get(5);
        this.Fram = Fram;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img[Fram], (int)x, (int) y, null);
    }
    
}
