package Main.Backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MiniGameBG extends Background{
    
    private int[] x;
    private int width;
    private int frame = 0;
    
    public MiniGameBG(BufferedImage img, float x, float y, int width){
        super(img, x, y);
        this.x = new int[3];
        this.x[0] = 0;
        this.x[1] = width;
        this.x[2] = width*2;
        this.width = width;
    }

    @Override
    public void tick() {
        if(x[frame] <= -width){
            if(frame == 0){
                x[frame] = x[2] + width;
                frame++;
            }   
            else if(frame == 1){
                x[frame] = x[0] + width;
                frame++;
            }
            else if(frame == 2){
                x[frame] = x[1] + width;
                frame = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i < x.length;i++){
                g.drawImage(img, x[i], 0, null);
            }
    }

    @Override
    public void move(float x, float y) {
        for(int i = 0;i < this.x.length;i++){
            this.x[i] += x;
        }
    }
}
