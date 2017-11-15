package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Brick;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.PlayerSS;
import Main.GamePanel;
import Main.Graphics.Assets;
import Main.Objects.Doors;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.sound.sampled.Clip;


public class StealStates extends State
{
    private PlayerSS player;
    
    private final float X = 300;
    private final float Y = 395;
    
    //BG
    private Background[] background;
    private Brick [] brick = new Brick[3];
    private int hight = 130;
    
    // Game Value
    private int lastScore, score, speed = 6;
    private long timeUnit = 100000000, lastTime;
    
    // Variable Door
    private Doors door;
    private Doors door1;
    private static ArrayList<StealStates>NumDoor;
    protected boolean checkDoor, checkDoorUp, checkDoorDown;
    protected float xDoor, yDoor;
    protected int ND = 13;
    
    StealStates(GamePanel game)
    {
        super(game);
        player = new PlayerSS(game, X, Y);
        
        // Set Brick
        int height;
        for(int i = 0; i < 3; i++)
        {
            height = (hight * ( i + 1 )) + 30 * i;
            brick[i] = new Brick(0, height);
        }
        
        //Door
        NumDoor = new ArrayList<StealStates>();
        //for(int i = 0; i < ND; i++)
        //{
            door = new Doors(game, 0, 395, true);
            door1 = new Doors(game, 0, 395 -160, true);
        //}
    }

    @Override
    public void tick() 
    {
        player.tick();
        
        System.out.println("Player "+"X:" + player.getX() + "Y:" + player.getY());
        System.out.println(player.getEnterDoor());
        System.out.println("Door "+"X:" + door.getX() + "Y:" + door.getY());
        // Go up or Go down Door
        if(player.getX() >= door.getX() && player.getX() <= door.getX() + 50)
        {
            if(player.getY() >= door.getY() && player.getY() <= door.getY() + 50)
            {
                if(player.getEnterDoor())
                {
                    if(door.getUPorDOWN())
                        player = new PlayerSS(game, player.getX(), player.getY() - 160);
                    else
                        player = new PlayerSS(game, player.getX(), player.getY() + 160);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) 
    {
        for(int i = 0; i < 3; i++)
        {
            brick[i].render(g);
        }
        player.render(g);
    }
}
