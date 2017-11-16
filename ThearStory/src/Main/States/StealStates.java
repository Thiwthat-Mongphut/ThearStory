package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Brick;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.PlayerSS;
import Main.Entities.Creatures.ZombieM;
import Main.GamePanel;
import Main.Objects.Doors;
import Main.Objects.KeyForWin;
import Main.Objects.Walls;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.Clip;


public class StealStates extends State
{
    // Player
    private PlayerSS player;
    private final float X = 250;
    private final float Y = 395;
    
    //BG
    private Background[] background;
    private Brick [] brick = new Brick[3];
    private Walls [] wall = new Walls[2];
    private int hightWall = 0;
    private int hight = 130;
    
    // Game Value
    private int lastScore, score;
    private long timeUnit = 1000000000 ,lastTime;
    
    // Variable Door
    private Doors door;
    private static ArrayList<Doors>NumDoor;
    protected boolean checkDoorUp, checkDoorDown, checkDoorCrosse;
    protected int xDoor, yDoor = 380, num;
    protected int ND = 16; 
    
    //Key
    private KeyForWin key;
    private static ArrayList<KeyForWin>NumKey;
    private Random dice;
    private int [] YkeysAll = {395, 235, 75};
    private int Ykey, Xkey, RightorLeft, win = 0;
    private boolean rightroomKey;
    
    // Zombie Value
    private float vX, vY, checkY;
    
    // ZombieM
    private ZombieM zombieM;
    private float zmX = 0, zmY = 66;
    
    StealStates(GamePanel game)
    {
        super(game);
        player = new PlayerSS(game, X, Y, false);

        // Set Brick
        int height;
        for(int i = 0; i < 3; i++)
        {
            height = (hight * ( i + 1 )) + 30 * i;
            brick[i] = new Brick(0, height);
        }
        
        //set Wall
        for(int i = 0; i < 2; i++)
        {
            wall[i] = new Walls(game, 370, hightWall, i);
            hightWall += 230;
        }
        
        //Door
        NumDoor = new ArrayList<Doors>();
                
        // Right Room
        xDoor = 0;
        for(int i = 0; i < 4; i++)
        {
            if(i == 2)
            {
                yDoor = yDoor - 160;
                xDoor = 75;
            }
            door = new Doors(game, xDoor, yDoor, true, false, false, 2);
            NumDoor.add(door);
            door = new Doors(game, xDoor, yDoor - 160, false, true, false, 3);
            NumDoor.add(door);
            xDoor += 170;
        }
        // Left Room
        yDoor += 160;
        xDoor = 480;
        for(int i = 0; i < 4; i++)
        {
            if(i == 2)
            {
                yDoor = yDoor - 160;
                xDoor = 575;
            }
            door = new Doors(game, xDoor, yDoor, true, false, false, 2);
            NumDoor.add(door);
            door = new Doors(game, xDoor, yDoor - 160, false, true, false, 3);
            NumDoor.add(door);
            xDoor += 170;
        }
        
        //Keys
        dice = new Random();
        NumKey = new ArrayList<KeyForWin>();
        for(int i = 0; i < 3; i++)
        {
            Ykey = dice.nextInt(3);
            Ykey = YkeysAll[Ykey];
            if(Ykey == 395)
            {
                Xkey = dice.nextInt(720);
            }
            else
            {
                // Room Key Right or Left
                RightorLeft = dice.nextInt(2);
                if(RightorLeft == 0)
                {
                    rightroomKey = true;
                    Xkey = dice.nextInt(270);
                }
                else
                {
                    rightroomKey = false;
                    Xkey = dice.nextInt(720);
                    
                    if(Xkey <= 310)
                        Xkey += 310;
                    
                    if(Xkey >= 310 && Xkey <= 410)
                        Xkey += 50;
                }
            }
            key = new KeyForWin(game, Xkey, Ykey);
            NumKey.add(key);
        } 
        
        zombieM = new ZombieM(game, zmX, zmY);      
    }

    @Override
    public void tick() 
    {
        player.setEnterDoor(false);
        player.tick();
        
        zombieM.setvX(player.getX());
        zombieM.setvY(player.getY());
        zombieM.tick();
        
        System.out.println("Player "+"X:" + player.getX() + "Y:" + player.getY());
        System.out.println("ZombieM "+"X:" + zombieM.GetX() + "Y:" + zombieM.GetY());
        
        // Crosse Room
        if(player.getY() == 395)
        {
            if(player.getX() <= 270)
            {
                player.setrighRoom(true);
            }
            else if(player.getX() >= 300)
            {
                player.setrighRoom(false);
            }
        }
        
        // Go up or Go down Door
        for(int i = 0; i < ND; i++)
        {
            // ต.น. X
            if(player.getX() + 35 >= NumDoor.get(i).getX() && player.getX() + 35 <= NumDoor.get(i).getX() + 50)
            {
                // ต.น. Y
                if(player.getY() >= NumDoor.get(i).getY() && player.getY() <= NumDoor.get(i).getY() + 50)
                {
                    // Press E
                    if(player.getEnterDoor())
                    {
                        // UP
                        if(NumDoor.get(i).getUP() && System.nanoTime() / timeUnit - lastTime >= 1)
                        {
                            player.setY(-160);
                            lastTime = System.nanoTime() / timeUnit;
                        }
                        
                        // DOWN
                        else if(NumDoor.get(i).getDOWN() && System.nanoTime() / timeUnit - lastTime >= 1)
                        {
                            
                            player.setY(160);
                            lastTime = System.nanoTime() / timeUnit;
                        }
                    }
                    //End Enter
                }
                // End if y
            }
            // End if x
        }
        // End Loop Door
        
        // Key
        for(int i = 0; i < NumKey.size(); i++)
        {
            // รอแก้ความกว้าง key
            if(player.getX() >= NumKey.get(i).getX() && player.getX() <= NumKey.get(i).getX() + 20)
            {
                if(player.getY() == NumKey.get(i).getY())
                {
                    win++;
                    NumKey.remove(i);
                }
            }
        }
        // End Loop Key
        
        //Game End
        if(win == 3)
        {
            game.gameState = new MainState(game);
            State.setState(game.gameState);
        }
    }

    @Override
    public void render(Graphics g) 
    {
        for(int i = 0; i < 3; i++)
        {
            brick[i].render(g);
        }
        for(int i = 0; i < 2; i++)
        {
            wall[i].render(g);
        }
        for(int i = 0; i < ND; i++)
        {
            NumDoor.get(i).render(g);
        }
        for(int i = 0; i < NumKey.size(); i++)
        {
            NumKey.get(i).render(g);
        }
        zombieM.render(g);
        player.render(g);
    }
}
