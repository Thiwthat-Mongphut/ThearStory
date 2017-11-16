package Main.Objects;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class KeyForWin extends Entity{
    
    private GamePanel game;
    private BufferedImage[] img;

    public KeyForWin(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
        img = Assets.Obj.get(1);
    }

    @Override
    public void tick() {
  
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img[0], (int)x, (int) y, null);
    }
    
}
