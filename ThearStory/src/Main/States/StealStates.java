package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Street;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.PlayerSS;
import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import javax.sound.sampled.Clip;


public class StealStates extends State
{
    private PlayerSS player;
    
    private final float X = 300;
    private final float Y = 390;
    
    //BG
    private Background[] background;
    private Street [] street = new Street[3];
    private int hight = 100;
    
    // Game Value
    private int lastScore, score, speed = 6;
    private long timeUnit = 100000000, lastTime;
    
    StealStates(GamePanel game)
    {
        super(game);
        player = new PlayerSS(game, X, Y);
        
        // Set street
        int height;
        for(int i = 0; i < 3; i++)
        {
            height = (hight * ( i + 1 )) + 30 * i;
            street[i] = new Street(0, height);
        }
    }

    @Override
    public void tick() 
    {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0; i < 3; i++)
        {
            street[i].render(g);
        }
        player.render(g);
    }
}
