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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static int point = 2;
    private static int lastScore;
    private static int oldScore;
    private static int speed = 7;
    private long lastTime;
    private long timeUnit = 100000000;
    private int hardValue = 1;
    private boolean start = false;
    
    // Sound
    private static Clip music;
    
    // Items
    private Items items;
    
    public RunMiniGame(GamePanel game, boolean resume) {
        super(game);
        
        if(!resume){
            score = 0;
            speed = 7;
            map = 0;
            oldScore = 0;
            lastScore = 0;
            start = false;
        }
        else{
            hardValue++;
            oldScore += 900;
            score += 900;
        }
            
        
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
        if(!start && game.getKeyManager().pressed && System.nanoTime() / timeUnit / 10 - lastTime / 10 >= 1)
            start = true;
        if(start){
            if(items.collisionCheck(player.getX() + 15, player.getY() + 19, playerWidth - 25, 25)){
                player.reduceHealth(1);
            }
            if(!player.getStatus()){
                music.stop();
                // high score
                try {
                    BufferedReader br = new BufferedReader(new FileReader("score.txt"));
                    String y = br.readLine();
                    br.close();
                    FileWriter writer = new FileWriter("score.txt");
                    if(Integer.parseInt(y) < score){
                        writer.write(String.valueOf(score));
                    }
                    else{
                        writer.write(y);
                    }
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(RunMiniGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                game.gameState = new GameOverInterface(game);
                State.setState(game.gameState);
            }
            else{
                if(System.nanoTime() / timeUnit - lastTime >= 1){
                    score += point;
                    if(score - lastScore >= 500){
                        lastScore = score;
                        point++;
                        background[map].setX(0);
                        if(map == 2)
                            map = 0;
                        else
                            map++;
                        if(speed < 11)
                            speed++;
                        player.increaseJumpPower();
                        items.moveMap();
                    }
                    lastTime = System.nanoTime() / timeUnit;
                }

                if(score - oldScore >= 1500 * hardValue){
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
        }        
    }
    
    @Override
    public void render(Graphics g) {
        background[map].render(g);
        street.render(g);
        player.render(g);
        items.render(g);
        if(map == 2)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.DARK_GRAY);
        g.setFont(Assets.gothicFont);
        g.drawString("Score " + String.valueOf(score), 650, 50);
        
        //g.setFont(Assets.gothicFontBig);
        if(!start){
            //g.setColor(new Color(230, 126, 23));
            g.drawImage(Assets.runTutorial_1, 148,150, null);
            g.drawImage(Assets.runTutorial_2, 498,150, null);
            g.setColor(Color.DARK_GRAY);
            g.drawString("press any keys to start", 280, 320);
        }
            
    }
    
}
