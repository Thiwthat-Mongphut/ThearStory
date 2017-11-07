package Main.States;

import Main.Entities.Creatures.RunPlayer;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RunMiniGame extends State{
    
    private RunPlayer player;
    private BufferedImage img;
    
    // Moving Tile
    private int[] x = {0, 366, 732};
    private int frame = 0;
    private int distance = 0;
    private int speed = 2;
    
    public RunMiniGame(GamePanel game) {
        super(game);
        player = new RunPlayer(game, 100, 353);
        img = Assets.Tiles.get(0);
    }
    
    @Override
    public void tick() {
        player.tick();
        distance += speed;
        
        for(int i = 0;i < x.length;i++){
            x[i] -= speed;
        }

            
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
        if(distance >= 366){
            if(frame == 0){
                x[frame] = x[2] + 366;
                frame++;
            }
                
            else if(frame == 1){
                x[frame] = x[0] + 366;
                frame++;
            }
            else if(frame == 2){
                x[frame] = x[1] + 366;
                frame = 0;
            }
            distance = 0;
            for(int i = 0;i < x.length;i++){
                g.drawImage(img, x[i], 440, null);
            }
        }
        else{
            for(int i = 0;i < x.length;i++){
                g.drawImage(img, x[i], 440, null);
            }
        }
            
            
    }
    
}
