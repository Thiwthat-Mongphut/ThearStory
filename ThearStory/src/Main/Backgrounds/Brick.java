package Main.Backgrounds;

import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick extends Background{
    
    private int[] x = {0, 458, 916};
    private int frame = 0;

    public Brick(float x, float y) {
        super(Assets.Tiles.get(1), x, y);
    }

    @Override
    public void tick() {
                if(x[frame] <= -458){
            if(frame == 0){
                x[frame] = x[2] + 458;
                frame++;
            }
                
            else if(frame == 1){
                x[frame] = x[0] + 458;
                frame++;
            }
            else if(frame == 2){
                x[frame] = x[1] + 458;
                frame = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i < x.length;i++){
                g.drawImage(img, x[i],(int)y, null);
            }
    }

    @Override
    public void move(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
