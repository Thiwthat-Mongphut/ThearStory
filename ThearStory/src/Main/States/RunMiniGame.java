package Main.States;

import Main.Backgrounds.Background;
import Main.Backgrounds.Street;
import Main.Backgrounds.miniGameBG;
import Main.Entities.Creatures.RunPlayer;
import Main.GamePanel;
import Main.Graphics.Assets;
import Main.Objects.Items;
import java.awt.Color;
import java.awt.Graphics;
import javax.sound.sampled.Clip;

public class RunMiniGame extends State{
    
    private RunPlayer player;
    private float playerWidth;
    private float playerHeight;
    
    private final float walkY = 343;
    private final float downY = 375;
    
    // BG
    private Background[] background;
    private static int map = 0;
    private Street street= new Street(0, 430);;

    // Game Value
    public static int score;
    private static int lastScore;
    private static int oldScore;
    private static int speed = 8;
    private long lastTime;
    private long timeUnit = 100000000;
    
    // Sound
    private static Clip music;
    
    // Items
    private Items items;
    
    public RunMiniGame(GamePanel game) {
        super(game);
        player = new RunPlayer(game, 50, walkY, walkY, downY);
        playerWidth = player.getWidth();
        playerHeight = player.getHeight();

        lastTime = System.nanoTime() / timeUnit;
        
        items = new Items(0, 0);
        
        // Set Backgrounds
        background = new Background[3];
        background[0] = new miniGameBG(Assets.BG[0],0 ,0, 587);
        background[1] = new miniGameBG(Assets.BG[1],0 ,0, 640);
        background[2] = new miniGameBG(Assets.BG[2],0 ,0, 968);
        
        // BG Music
        music = Assets.runGameMusic;
        music.setFramePosition(0);
        music.loop(Clip.LOOP_CONTINUOUSLY);
        music.start();
    }
    
    public RunMiniGame(GamePanel game, int speed, int score, int map) {
        super(game);
        this.score = score;
        this.speed = speed;
        this.map = map;
        oldScore = 0;
        player = new RunPlayer(game, 50, walkY, walkY, downY);
        playerWidth = player.getWidth();
        playerHeight = player.getHeight();

        lastTime = System.nanoTime() / timeUnit;
        
        items = new Items(0, 0);
        
        // Set Backgrounds
        background = new Background[3];
        background[0] = new miniGameBG(Assets.BG[0],0 ,0, 587);
        background[1] = new miniGameBG(Assets.BG[1],0 ,0, 640);
        background[2] = new miniGameBG(Assets.BG[2],0 ,0, 968);
        
        // BG Music
        music = Assets.runGameMusic;
        music.setFramePosition(0);
        music.loop(Clip.LOOP_CONTINUOUSLY);
        music.start();
    }
    
    @Override
    public void tick() { 
        if(items.collisionCheck(player.getX() + 15, player.getY() + 19, playerWidth - 25, 25)){
            music.stop();
            game.gameState = new GameOverInterface(game);
            State.setState(game.gameState);
        }
        
        if(System.nanoTime() / timeUnit - lastTime >= 1){
            score += 2;
            if(score - lastScore >= 750){
                lastScore = score;
                background[map].setX(0);
                if(map == 2)
                    map = 0;
                else
                    map++;
                if(speed < 11)
                    speed += 2;
                player.increaseJumpPower();
                items.moveMap();
            }
            lastTime = System.nanoTime() / timeUnit;
        }
        
        if(score - oldScore >= 1500){
            oldScore = score;
            music.stop();
            game.gameState = new StealStates(game);
            State.setState(game.gameState);
        }
        
        background[map].tick();
        background[map].move(-1,0);
        street.move(-speed, 0);
        street.tick();
        player.tick();
        playerWidth = player.getWidth();
        playerHeight = player.getHeight();
        
        items.tick();
        items.move(-speed);
    }

    @Override
    public void render(Graphics g) {
        background[map].render(g);
        street.render(g);
        player.render(g);
        items.render(g);
        g.setColor(Color.BLACK);
        g.setFont(Assets.gothicFont);
        g.drawString("Score " + String.valueOf(score), 650, 50);
    }
    
}
