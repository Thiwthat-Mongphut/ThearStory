package Main.Entities.Creatures;

import Main.Entities.Entity;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
// random
import java.util.Random;

public class Items extends Entity {
    
    private Random rand = new Random();
    private int randObj;   
    private BufferedImage obj[];
    
    private int[] setX = {360, 400, 440, 480};
    private int[] x = new int[4];
    private int[] y = new int[4];
    private int count = 0;

    public Items(float x, float y) {
        super(x, y);
        obj = new BufferedImage[4];
        for(int i = 0;i < 4;i++){
            randObj = rand.nextInt(4);
            if(i == 0)
                this.x[i] = 640;
            else
                this.x[i] = this.x[i-1] + setX[randObj];
            
            if(randObj == 0){ // tree
                obj[i] = Assets.Obj.get(0)[0];
                this.y[i] = 430 - Assets.Obj.get(0)[0].getHeight();
            }
            else if(randObj == 1){ // chair
                obj[i] = Assets.Obj.get(0)[1];
                this.y[i] = 430 - Assets.Obj.get(0)[1].getHeight();
            }
            else if(randObj == 2){ // rock
                obj[i] = Assets.Obj.get(0)[2];
                this.y[i] = 430 - Assets.Obj.get(0)[2].getHeight();
            }
            else if(randObj == 3){ // UFO
                obj[i] = Assets.Obj.get(0)[3];
                this.y[i] = 370 - Assets.Obj.get(0)[3].getHeight();
            }
        }
    }

    @Override
    public void tick() {
        if(x[count] <= -300){
            // change items
            randObj = rand.nextInt(4);

            if(randObj == 0){ // tree
                obj[count] = Assets.Obj.get(0)[0];
                this.y[count] = 430 - Assets.Obj.get(0)[0].getHeight();
            }
            else if(randObj == 1){ // chair
                obj[count] = Assets.Obj.get(0)[1];
                this.y[count] = 430 - Assets.Obj.get(0)[1].getHeight();
            }
            else if(randObj == 2){ // rock
                obj[count] = Assets.Obj.get(0)[2];
                this.y[count] = 430 - Assets.Obj.get(0)[2].getHeight();
            }
            else if(randObj == 3){ // UFO
                obj[count] = Assets.Obj.get(0)[3];
                this.y[count] = 370 - Assets.Obj.get(0)[3].getHeight();
            }
            
            if(count == 0){
                x[count] = x[3] + setX[randObj];
                count++;
            }
                
            else if(count == 1){
                x[count] = x[0] + setX[randObj];
                count++;
            }
            else if(count == 2){
                x[count] = x[1] + setX[randObj];
                count++;
            }
            else if(count == 3){
                x[count] = x[2] + setX[randObj];
                count = 0;
        }
            
    }
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i< x.length;i++){
            g.drawImage(obj[i], x[i], y[i], null);
        }
    }
    
    public void move(int speed){
        for(int i = 0;i < this.x.length;i++){
            x[i] += speed;
        }
    }

}
