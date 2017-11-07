package Main.Backgrounds;

import Main.Graphics.Assets;
import java.awt.Graphics;

public class Sunset extends Background{
    
    private int[] setX = {0, 740, 1480};
    private int arrayOfLeftImg = 0;
    
    public Sunset(float x, float y) {
        super(Assets.BG[3], x, y);
        
    }

    @Override
    public void tick() {
       if(setX[arrayOfLeftImg] <= -740){
           if(arrayOfLeftImg == 0){
               setX[0] = setX[2] + 740;
               arrayOfLeftImg++;
           }
               
            else if(arrayOfLeftImg == 1){
                setX[1] = setX[0] + 740;
                arrayOfLeftImg++;
            }
               
            else if(arrayOfLeftImg == 2){
                setX[2] = setX[1] + 740;
                arrayOfLeftImg = 0;
            }
       }
            
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i < setX.length;i++)
            g.drawImage(img, (int) setX[i], (int) y, null);
    }

    @Override
    public void move(float x, float y) {
        for(int i = 0;i < setX.length;i++){
            setX[i] += x;
        }
        this.y += y;
    }
    

    
}
