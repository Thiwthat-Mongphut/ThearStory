package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Cloud;
import Main.Backgrounds.Mountain;
import Main.Backgrounds.Sky;
import Main.Backgrounds.Street;
import Main.Backgrounds.Sunset;
import Main.Entities.Creatures.RunPlayer;
import Main.GamePanel;
import java.awt.Graphics;

public class RunMiniGame extends State{
    
    private RunPlayer player;
    private Street street;
    private final float walkY = 345;
    private final float downY = 375;
    
    // BG
    private Background[] background;
    private Cloud cloud;
    private int map = 0;

    // Game Value
    private int score = 0;
    private int lastScore;
    private int speed = 3;
    private long lastTime;
    private long timeUnit = 100000000;
    
    public RunMiniGame(GamePanel game) {
        super(game);
        player = new RunPlayer(game, 50, walkY, walkY, downY);
        street = new Street(0, 440);
        lastTime = System.nanoTime() / timeUnit;
        
        // Set Backgrounds
        cloud = new Cloud(0,0);
        background = new Background[3];
        background[0] = new Sky(0,0);
        background[1] = new Mountain(0,0);
        background[2] = new Sunset(0,0);
    }
    
    @Override
    public void tick() {
        if(System.nanoTime() / timeUnit - lastTime >= 1){
            score += 2;
            if(score - lastScore >= 300){
                lastScore = score;
                background[map].setX(0);
                if(map == 2)
                    map = 0;
                else
                    map++;
                if(speed <= 5)
                    speed++;
            }
            lastTime = System.nanoTime() / timeUnit;
            System.out.println(score);
        }
        
        background[map].tick();
        background[map].move(-1,0);
        cloud.tick();
        cloud.move(-speed, 0);
        street.move(-speed, 0);
        street.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        background[map].render(g);
        cloud.render(g);
        street.render(g);
        player.render(g);
    }
    
}
