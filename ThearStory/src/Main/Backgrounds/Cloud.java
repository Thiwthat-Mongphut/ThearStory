package Main.Backgrounds;

import Main.Graphics.Assets;
import java.awt.Graphics;

public class Cloud extends Background{

    public Cloud(float x, float y) {
        super(Assets.BG[0], x, y);
    }

    @Override
    public void tick() {
        if(x < -(img.getWidth()))
            x = 640;
        else if(x > 640)
            x = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, null);
    }

    @Override
    public void move(float x, float y) {
       this.x += x;
       this.y += y;
    }
}
