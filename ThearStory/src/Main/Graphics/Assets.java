/* 
    Picture Sources
    - http://www.gameart2d.com/
    Sound Source
    - Little Idia: https://www.bensound.com
    ********************Thank you so much***************************
*/

package Main.Graphics;

import java.awt.Font;
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
    public static ArrayList<BufferedImage[]> ZombieImg;
    public static BufferedImage[] Tiles = new BufferedImage[50];
    public static BufferedImage menuBG, startIcon, dogSkin, interFaceBg, gameOver
            ,menuButton,menuButton2,closeButton,closeButton2,letsGoButton,letsGoButton2
            ,restartButton,restartButton2;

    public static ArrayList<BufferedImage[]> Obj;
    public static BufferedImage[] bird;
    public static BufferedImage[] BG;
    public static BufferedImage SSBackGround;
    
    // Sounds
    public static Clip runGameMusic;
    public static Clip stealthGameMusic;
    public static Clip zombieMSound;
    public static Clip zombieFSound;
    
    // Font
    public static Font gothicFont = new Font("SHOWCARD GOTHIC", Font.PLAIN, 20);
    public static Font gothicFontBig = new Font("SHOWCARD GOTHIC", Font.PLAIN, 45);
    
    // Size and Number of Thear Sprite
    private static int numFrame[] = {3,3,1};
    private static int frameWidth[] = {130,130,130};
    private static int frameHeight[] = {86,86,55};

    // Size and Number of Obj Sprite
    private static int objFrame = 7;
    private static int objFrameWidth[][] = {{114,143,132,144,104,100},
                                            {140,120,140,114,129},
                                            {179,158,102,144,137},
                                            {458},
                                            {820},
                                            {47,43,50,50},
                                            {45}
                                            };
    private static int objFrameHeight[][] = {{122,104,131,110,63,79},
                                            {94,80,115,94,74},
                                            {105,100,109,111,92},
                                            {50},
                                            {20},
                                            {235,82,70,70},
                                            {30}
                                            };
    private static int rowHeight[] = {131,116,112,50,20,235,30};
    
    // Size of Zombit Sprite
    private static int zombieFrame[] = {10,10,10,10};
    private static int zombieWidth[][] = {{39,39,38,38,42,44,46,46,43,39},
                                          {39,43,46,46,44,42,38,38,39,39},
                                          {42,42,41,42,42,42,42,42,42,42},
                                          {42,42,42,42,42,42,42,42,42,42}
                                        };
    private static int zombieHeight[] = {65,65,66,66};
    
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
        
        // main menu
        interFaceBg = ImageLoader.loadImage("/interFace/Bg.png");
        letsGoButton = ImageLoader.loadImage("/interFace/lets_go.png");
        letsGoButton2 = ImageLoader.loadImage("/interFace/lets_go2.png");
        restartButton = ImageLoader.loadImage("/interFace/restart.png");
        restartButton2 = ImageLoader.loadImage("/interFace/restart2.png");
        menuButton = ImageLoader.loadImage("/interFace/menu.png");
        menuButton2 = ImageLoader.loadImage("/interFace/menu2.png");
        closeButton = ImageLoader.loadImage("/interFace/close.png");
        closeButton2 = ImageLoader.loadImage("/interFace/close2.png");
        gameOver = ImageLoader.loadImage("/interFace/gameOver.png");
        
        // Bird Crop
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/birdSprite.png"));
        bird = new BufferedImage[4];
        for(int i = 0;i < 4;i++){
            bird[i] = sheet.crop(i * 41, 0, 41, 35);
        }
        
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/ZombieSprite.png"));
        ZombieImg = new ArrayList<BufferedImage[]>();
        curWidth = 0;
        curHeight = 0;
        for(int i = 0;i < zombieFrame.length;i++){
            BufferedImage[] groupImg = new BufferedImage[zombieFrame[i]];
            for(int j = 0;j < zombieFrame[i];j++){
                groupImg[j] = sheet.crop(curWidth, curHeight, zombieWidth[i][j],
                        zombieHeight[i]);
                curWidth += zombieWidth[i][j];
            }
            ZombieImg.add(groupImg);
            curHeight += zombieHeight[i];
            curWidth = 0;
        }
        
        // Backgrounds
        BG = new BufferedImage[3];
        BG[0] = ImageLoader.loadImage("/BG/runBG0.png");
        BG[1] = ImageLoader.loadImage("/BG/runBG1.png");
        BG[2] = ImageLoader.loadImage("/BG/runBG2.png");
        SSBackGround = ImageLoader.loadImage("/BG/wall.png");
        
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
            curHeight += rowHeight[i];
            curWidth = 0;
        }
        
        // Load Sounds
       try {
            runGameMusic = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
              Assets.class.getResourceAsStream("/Music/LittleIdea.wav"));
            runGameMusic.open(inputStream);
            
            stealthGameMusic = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(
              Assets.class.getResourceAsStream("/Music/Pink-panther-theme.wav"));
            stealthGameMusic.open(inputStream);
            
            zombieMSound = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(
              Assets.class.getResourceAsStream("/Music/ZombieM.wav"));
            zombieMSound.open(inputStream);
            
            zombieFSound = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(
              Assets.class.getResourceAsStream("/Music/ZombieF.wav"));
            zombieFSound.open(inputStream);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

