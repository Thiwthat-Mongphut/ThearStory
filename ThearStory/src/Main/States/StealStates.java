package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Brick;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.PlayerSS;
import Main.Entities.Creatures.ZombieF;
import Main.Entities.Creatures.ZombieM;
import Main.GamePanel;
import Main.Graphics.Assets;
import Main.Objects.Doors;
import Main.Objects.KeyForWin;
import Main.Objects.Walls;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.Clip;
import static sun.audio.AudioPlayer.player;


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
    private long timeUnit = 1000000000 ,lastTime, lastTimeP;
    
    // Sound
    private Clip backgroundMusic;
    
    // Variable Door
    private Doors door;
    private static ArrayList<Doors>numDoor;
    protected boolean checkDoorUp, checkDoorDown, checkDoorCross;
    protected int xDoor, yDoor = 380, num;
    protected int ND = 16; 
    
    //Key
    private KeyForWin key;
    private static ArrayList<KeyForWin>NumKey;
    private static ArrayList<Integer>RandomP;
    private Random dice;
    //private int [] YkeysAll = {420, 260, 100};
    private int Ykey, Xkey, RightorLeft, win = 0, RandomK = 4, getRK;
    private boolean rightRoomKey;
    
    // Zombie Value
    private float vX, vY, checkY;
    
    // ZombieM
    private ZombieM zombieM;
    private float zmX = 0, zmY = 66;
    
    // ZombieF
    private ZombieF zombieF;
    private int pos, posY, posX;
    private int[] positionX = {100, 600};
    private int[] positionY = {227, 67};
    private Random rand = new Random();
    
    private int a = 0;
    
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
        numDoor = new ArrayList<Doors>();
                
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
            numDoor.add(door);
            door = new Doors(game, xDoor, yDoor - 160, false, true, false, 3);
            numDoor.add(door);
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
            numDoor.add(door);
            door = new Doors(game, xDoor, yDoor - 160, false, true, false, 3);
            numDoor.add(door);
            xDoor += 170;
        }
        
        //Keys
        dice = new Random();
        RandomP = new ArrayList<Integer>();
        NumKey = new ArrayList<KeyForWin>();
        RandomP.add(0);
        RandomP.add(1);
        RandomP.add(2);
        RandomP.add(3);
        for(int i = 0; i < 3; i++)
        {
            getRK = dice.nextInt(RandomK);
            RandomK--;
            Ykey = RandomP.get(getRK);
            
            if(Ykey == 0)
            {
                Xkey = dice.nextInt(300);
                Ykey = 260;
            }
            else if(Ykey == 1)
            {
                Xkey = dice.nextInt(300);
                Xkey += 450;
                Ykey = 260;
            }
            else if(Ykey == 2)
            {
                Xkey = dice.nextInt(300);
                Ykey = 100;
            }
            else if(Ykey == 3)
            {
                Xkey = dice.nextInt(300);
                Xkey += 450;
                Ykey = 100;
            }
            
            key = new KeyForWin(game, Xkey, Ykey);
            NumKey.add(key);
            RandomP.remove(getRK);
        } 
        
        zombieM = new ZombieM(game, zmX, zmY);      
        
        // ZombieF
        pos = rand.nextInt(2);
        posY = positionY[pos];
        pos = rand.nextInt(2);
        posX = positionX[pos];
        
        zombieF = new ZombieF(game, posX, posY, (float)0.5);
        
        // BG Music
        backgroundMusic = Assets.stealthGameMusic;
        backgroundMusic.setFramePosition(0);
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundMusic.start();
        
        lastTime = System.nanoTime() / timeUnit;
        lastTimeP = System.nanoTime() / timeUnit;
    }

    @Override
    public void tick() 
    {
        if((System.nanoTime() - lastTimeP) / 1000000000 >= 1){
            zombieM.setProtectPlayer(false);
            zombieF.setProtectPlayer(false);
            lastTimeP = System.nanoTime();
        }
        player.setEnterDoor(false);
        player.tick();
        
        zombieM.setRightRoom(player.getRightRoom());
        zombieM.setvX(player.getX());
        zombieM.setvY(player.getY());
        zombieM.tick();
        
        zombieF.tick();
        
        // Crosse Room
        if(player.getY() == 395)
        {
            if(player.getX() <= 270)
            {
                player.setRightRoom(false);
            }
            else if(player.getX() >= 300)
            {
                player.setRightRoom(true);
            }
        }
        
        // Go up or Go down Door
        for(int i = 0; i < ND; i++)
        {
            // ต.น. X
            if(player.getX() + 35 >= numDoor.get(i).getX() && player.getX() + 35 <= numDoor.get(i).getX() + 50)
            {
                // ต.น. Y
                if(player.getY() >= numDoor.get(i).getY() && player.getY() <= numDoor.get(i).getY() + 50)
                {
                    // Press E
                    if(player.getEnterDoor())
                    {
                        // UP
                        if(numDoor.get(i).getUP() && System.nanoTime() / timeUnit - lastTime >= 1)
                        {
                            player.setY(-160);
                            lastTime = System.nanoTime() / timeUnit;
                        }
                        
                        // DOWN
                        else if(numDoor.get(i).getDOWN() && System.nanoTime() / timeUnit - lastTime >= 1)
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
            // get position y player to ZombieF
            zombieF.getPositionPlayer(player.getY());
        }
        // End Loop Door
        
        // Hit Blocks
        if((player.getX() <= zombieM.getX() + zombieM.getWidth() && player.getX() >= zombieM.getX()) ||
                (player.getX() + 130 >= zombieM.getX() && player.getX() + 130 <= zombieM.getX()  + zombieM.getWidth()))
        {
            if(player.getY() - 9 == zombieM.getY() && !zombieM.getProtectPlayer())
            {
                backgroundMusic.stop();
                game.gameState = new GameOverInterface(game);
                State.setState(game.gameState);
            }
        }
        if((player.getX() <= zombieF.getX() + zombieF.getWidth() && player.getX() >= zombieF.getX()) ||
                (player.getX() + 130 >= zombieF.getX() && player.getX() + 130 <= zombieF.getX()  + zombieF.getWidth()))
        {
            if(player.getY() - 8 == zombieF.getY() && !zombieF.getProtectPlayer())
            {
                backgroundMusic.stop();
                game.gameState = new GameOverInterface(game);
                State.setState(game.gameState);
            }
        }
        if(zombieF.getX() >= player.getX() && zombieF.getX() + zombieF.getWidth() <= player.getX() + 130){
            if(player.getY() - 8 == zombieF.getY() && !zombieF.getProtectPlayer())
            {
                backgroundMusic.stop();
                game.gameState = new GameOverInterface(game);
                State.setState(game.gameState);
            }
        }
        if(zombieM.getX() >= player.getX() && zombieM.getX() + zombieM.getWidth() <= player.getX() + 130){
            if(player.getY() - 9 == zombieM.getY() && !zombieM.getProtectPlayer())
            {
                backgroundMusic.stop();
                game.gameState = new GameOverInterface(game);
                State.setState(game.gameState);
            }
        }
        // End Hit Blocks
        
        
        // Key
        for(int i = 0; i < NumKey.size(); i++)
        {
            if((player.getX() <= NumKey.get(i).getX()+ NumKey.get(0).getWidth() && player.getX() >= NumKey.get(i).getX()) ||
                    (player.getX() + 130 >= NumKey.get(i).getX() && player.getX() + 130 <= NumKey.get(i).getX()  + NumKey.get(0).getWidth()))
            {
                if(player.getY() + 25  == NumKey.get(i).getY())
                {
                    win++;
                    NumKey.remove(i);
                }
            }
            else if(NumKey.get(i).getX() >= player.getX() && NumKey.get(i).getX() + NumKey.get(0).getWidth() <= player.getX() + 130){
                if(player.getY() + 25  == NumKey.get(i).getY())
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
            backgroundMusic.stop();
            game.gameState = new RunMiniGame(game, true);
            State.setState(game.gameState);
        }
    }

    @Override
    public void render(Graphics g) 
    {
        g.drawImage(Assets.SSBackGround, 0, 0, null);
        
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
            numDoor.get(i).render(g);
        }
        for(int i = 0; i < NumKey.size(); i++)
        {
            NumKey.get(i).render(g);
        }
        zombieM.render(g);
        player.render(g);
        
        zombieF.render(g);
    }
}
