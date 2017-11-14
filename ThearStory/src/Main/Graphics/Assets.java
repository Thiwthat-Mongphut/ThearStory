/* 
    Picture Sources
    - bevouliin.com
    - MR.SUJIT YADAV from unluckystudio.com
    - http://www.gameart2d.com/free-platformer-game-tileset.html
    Sound Source
    - Little Idia: https://www.bensound.com
    ********************Thank you so much***************************
*/

package Main.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Assets {
    
    private static int curWidth;
    private static int curHeight;
    
    // Pictures
    public static ArrayList<BufferedImage[]> TearImg;
    public static ArrayList<BufferedImage[]> Obj;
    public static ArrayList<BufferedImage> Tiles;
    public static BufferedImage[] bird;
    public static BufferedImage[] BG;
    public static BufferedImage menuBG, startIcon;
    
    // Sounds
    public static Clip runGameMusic;
    
    // Size and Number of Thear Sprite
    private static int numFrame[] = {3,3,1};
    private static int frameWidth[] = {130,130,130};
    private static int frameHeight[] = {87,87,55};

    // Size and Number of Obj Sprite
    private static int objFrame = 1;
    private static int objFrameWidth[][] = {{94,92,141,107,101,75,15,18}};
    private static int objFrameHeight[][] = {{100,90,85,107,75,35,13,14}};
    
    // Size and Number of Tiles
    private static int tileFrame = 2;
    private static int tileFrameWidth[] = {458, 820};
    private static int tileFrameHeight[] = {50, 20};
    
    public static void init(){
        SpriteSheet sheet;
        // Thear Crop
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/TearSprite.png"));
        curHeight = 0;
        TearImg = new ArrayList<BufferedImage[]>();
        for(int i=0; i<numFrame.length; i++){
            BufferedImage[] groupImg = new BufferedImage[numFrame[i]];
            for(int j=0; j<numFrame[i]; j++){
                groupImg[j] = sheet.crop(j * frameWidth[i], curHeight, frameWidth[i],
                        frameHeight[i]);
            }
            TearImg.add(groupImg);
            curHeight += frameHeight[i];
        }
        
        // Bird Crop
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/birdSprite.png"));
        bird = new BufferedImage[4];
        for(int i = 0;i < 4;i++){
            bird[i] = sheet.crop(i * 41, 0, 41, 35);
        }
        
        // Backgrounds
        BG = new BufferedImage[3];
        BG[0] = ImageLoader.loadImage("/BG/runBG0.jpg");
        BG[1] = ImageLoader.loadImage("/BG/runBG1.png");
        BG[2] = ImageLoader.loadImage("/BG/runBG2.png");
        
        // Load Some Image
        menuBG = ImageLoader.loadImage("/BG/Menu.png");
        startIcon = ImageLoader.loadImage("/SFX/StartIcon.png");
        
        // Load Objects
        sheet = new SpriteSheet(ImageLoader.loadImage("/Objects/ObjectsSprite.png"));
        curHeight = 0;
        curWidth = 0;
        Obj = new ArrayList<BufferedImage[]>();
        for(int i=0; i<objFrame; i++){
            BufferedImage[] groupImg = new BufferedImage[objFrameWidth[i].length];
            for(int j=0; j<objFrameWidth[i].length; j++){
                groupImg[j] = sheet.crop(curWidth, curHeight, 
                        objFrameWidth[i][j], objFrameHeight[i][j]);
                curWidth += objFrameWidth[i][j];
            }
            Obj.add(groupImg);
            curHeight += objFrameHeight[i][0];
            curWidth = 0;
        }
        
        // Load Tiles
        sheet = new SpriteSheet(ImageLoader.loadImage("/Tilesets/Tiles.png"));
        curHeight = 0;
        Tiles = new ArrayList<BufferedImage>();
        for(int i=0; i<tileFrame; i++){
            Tiles.add(sheet.crop(0, curHeight, 
                    tileFrameWidth[i], tileFrameHeight[i]));
            curHeight += tileFrameHeight[i];
        }

        // Load Sounds
        try {
            runGameMusic = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
              Assets.class.getResourceAsStream("/Music/LittleIdea.wav"));
            runGameMusic.open(inputStream);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

