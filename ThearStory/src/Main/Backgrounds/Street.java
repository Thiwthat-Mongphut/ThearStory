package Main.Backgrounds;

import Main.Graphics.Assets;
import java.awt.Graphics;

public class Street extends Background{

    private int[] x = {0, 458, 916};
    private int frame = 0;
    private int distance = 0;
    
    public Street(float x, float y) {
        super(Assets.Tiles.get(0), x, y);
    }

    @Override
    public void tick() {
        if(distance >= 458){
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
            distance = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i < x.length;i++){
                g.drawImage(img, x[i], 430, null);
            }
    }

    @Override
    public void move(float x, float y) {
        distance += -x;
        for(int i = 0;i < this.x.length;i++){
            this.x[i] += x;
        }
    }
    
}
