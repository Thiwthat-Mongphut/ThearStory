package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Street;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.RunPlayer;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import javax.sound.sampled.Clip;

public class RunMiniGame extends State{
    
    private RunPlayer player;
    
    private final float walkY = 345;
    private final float downY = 375;
    
    // BG
    private Background[] background;
    private int map = 0;
    private Street street;

    // Game Value
    private int score = 0;
    private int lastScore;
    private int speed = 5;
    private long lastTime;
    private long timeUnit = 100000000;
    
    // Sound
    private static Clip music;
    
    public RunMiniGame(GamePanel game) {
        super(game);
        player = new RunPlayer(game, 50, walkY, walkY, downY);
        street = new Street(0, 440);
        lastTime = System.nanoTime() / timeUnit;
        
        // Set Backgrounds
        background = new Background[3];
        background[0] = new miniGameBG(Assets.BG[0],0 ,0, 587);
        background[1] = new miniGameBG(Assets.BG[1],0 ,0, 587);
        background[2] = new miniGameBG(Assets.BG[2],0 ,0, 880);
        
        // Play BG Music
        music = Assets.runGameMusic;
        music.start();
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    @Override
    public void tick() {
        if(System.nanoTime() / timeUnit - lastTime >= 1){
            score += 2;
            if(score - lastScore >= 500){
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
            System.out.println("Score: " + score);
        }
        
        background[map].tick();
        background[map].move(-1,0);
        street.move(-speed, 0);
        street.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        background[map].render(g);
        street.render(g);
        player.render(g);
    }
    
}
