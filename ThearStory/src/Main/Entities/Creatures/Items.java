package Main.Entities.Creatures;

import Main.Entities.Entity;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
// random
import java.util.Random;

public class Items extends Entity {
    
    private Random rand = new Random();
    private int randObj;
    
    private BufferedImage obj[];
    private int[] setX = {480, 550, 640, 690};
    private int[] x = new int[4];
    private int[] y = new int[4];
    private int count = 0;
    
    private BufferedImage[][] bird;
    private int xBird;
    private int yBird;
    private int birdFrame = 0;
    private boolean haveBird = false;
    private int[] setYBird = {325, 270};

    public Items(float x, float y) {
        super(x, y);
        // Objects
        obj = new BufferedImage[4];
        for(int i = 0;i < 4;i++){
            randObj = rand.nextInt(4);
            if(i == 0)
                this.x[i] = 640;
            else
                this.x[i] = this.x[i-1] + setX[randObj];
            
            randObj = rand.nextInt(6);
            if(randObj == 0){
                obj[i] = Assets.Obj.get(0)[0];
                this.y[i] = 430 - Assets.Obj.get(0)[0].getHeight();
            }
            else if(randObj == 1){
                obj[i] = Assets.Obj.get(0)[1];
                this.y[i] = 430 - Assets.Obj.get(0)[1].getHeight();
            }
            else if(randObj == 2){
                obj[i] = Assets.Obj.get(0)[2];
                this.y[i] = 430 - Assets.Obj.get(0)[2].getHeight();
            }
            else if(randObj == 3){
                obj[i] = Assets.Obj.get(0)[3];
                this.y[i] = 430 - Assets.Obj.get(0)[3].getHeight();
            }
            else if(randObj == 4){
                obj[i] = Assets.Obj.get(0)[4];
                this.y[i] = 430 - Assets.Obj.get(0)[4].getHeight();
            }
            else if(randObj == 5){
                obj[i] = Assets.Obj.get(0)[5];
                this.y[i] = 430 - Assets.Obj.get(0)[5].getHeight();
            }
        }
        // Bird
        bird = new BufferedImage[1][];
        bird[0] = Assets.bird;
    }

    @Override
    public void tick() {
        if(x[count] <= -200){
            if((int) (Math.random() * 10) > 7 && !haveBird){
                haveBird = true;
                randObj = rand.nextInt(4);
                
                // move left object to next of bird
                obj[count] = Assets.Obj.get(0)[randObj];
                this.y[count] = 430 - Assets.Obj.get(0)[randObj].getHeight();
                
                if(count == 0){
                    xBird = x[3] + setX[randObj];
                    x[count] = x[3] + setX[randObj] * 2;
                    count++;
                }
                else if(count == 1){
                    xBird = x[0] + setX[randObj];
                    x[count] = x[0] + setX[randObj] * 2;
                    count++;
                }
                else if(count == 2){
                    xBird = x[1] + setX[randObj];
                    x[count] = x[1] + setX[randObj] * 2;
                    count++;
                }
                else if(count == 3){
                    xBird = x[2] + setX[randObj];
                    x[count] = x[2] + setX[randObj] * 2;
                    count = 0;
                }
                
                randObj = rand.nextInt(2);
                if(randObj == 0)
                    yBird = setYBird[0];
                else
                    yBird = setYBird[1];
            }
            else{
                // change items
                randObj = rand.nextInt(7);

                if(randObj == 0){
                    obj[count] = Assets.Obj.get(0)[0];
                    this.y[count] = 430 - Assets.Obj.get(0)[0].getHeight();
                }
                else if(randObj == 1){
                    obj[count] = Assets.Obj.get(0)[1];
                    this.y[count] = 430 - Assets.Obj.get(0)[1].getHeight();
                }
                else if(randObj == 2){
                    obj[count] = Assets.Obj.get(0)[2];
                    this.y[count] = 430 - Assets.Obj.get(0)[2].getHeight();
                }
                else if(randObj == 3){
                    obj[count] = Assets.Obj.get(0)[3];
                    this.y[count] = 430 - Assets.Obj.get(0)[3].getHeight();
                }
                else if(randObj == 4){
                    obj[count] = Assets.Obj.get(0)[4];
                    this.y[count] = 430 - Assets.Obj.get(0)[4].getHeight();
                }
                else if(randObj == 5){
                    obj[count] = Assets.Obj.get(0)[5];
                    this.y[count] = 430 - Assets.Obj.get(0)[5].getHeight();
                }

                randObj = rand.nextInt(3);
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
        
        if(xBird <= -41)
            haveBird = false;
        else{
            if(birdFrame == 3)
                birdFrame = 0;
            else
                birdFrame++;
        }
            
    }
    

    @Override
    public void render(Graphics g) {
        for(int i = 0;i< x.length;i++){
            g.drawImage(obj[i], x[i], y[i], null);
        }
        
        if(xBird >= -41 && haveBird)
            g.drawImage(bird[0][birdFrame], xBird, yBird, null);
    }
    
    public void move(int speed){
        for(int i = 0;i < this.x.length;i++){
            x[i] += speed;
        }
        
        if(xBird >= -41 && haveBird){
            xBird += speed;
        }
            
    }

}
