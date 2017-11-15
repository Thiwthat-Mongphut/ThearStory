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

    public static BufferedImage[] Tiles = new BufferedImage[50];
    public static BufferedImage menuBG, startIcon, dogSkin, interFaceBg, gameOver
            ,menuButton,menuButton2,closeButton,closeButton2,letsGoButton,letsGoButton2;

    public static ArrayList<BufferedImage[]> Obj;
    public static BufferedImage[] bird;
    public static BufferedImage[] BG;
    
    // Sounds
    public static Clip runGameMusic;
    
    // Font
    public static Font gothicFont = new Font("SHOWCARD GOTHIC", Font.PLAIN, 20);
    
    // Size and Number of Thear Sprite
    private static int numFrame[] = {3,3,1};
    private static int frameWidth[] = {130,130,130};
    private static int frameHeight[] = {87,87,55};

    // Size and Number of Obj Sprite
    private static int objFrame = 6;
    private static int objFrameWidth[][] = {{114,143,132,144,104,100},
                                            {140,120,140,114,129},
                                            {179,158,102,144,137},
                                            {458},
                                            {820},
                                            {47,43,70,70}
                                            };
    private static int objFrameHeight[][] = {{122,104,131,110,63,79},
                                            {94,80,115,94,74},
                                            {105,100,109,111,92},
                                            {50},
                                            {20},
                                            {235,82,50,50}
                                            };
    private static int rowHeight[] = {131,116,112,50,20,235};
    
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
        menuButton = ImageLoader.loadImage("/interFace/menu_button.png");
        menuButton2 = ImageLoader.loadImage("/interFace/menu_button2.png");
        closeButton = ImageLoader.loadImage("/interFace/close_button.png");
        closeButton2 = ImageLoader.loadImage("/interFace/close_button2.png");
        gameOver = ImageLoader.loadImage("/interFace/gameOver.png");
        
        // Bird Crop
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/birdSprite.png"));
        bird = new BufferedImage[4];
        for(int i = 0;i < 4;i++){
            bird[i] = sheet.crop(i * 41, 0, 41, 35);
        }
        
        // Backgrounds
        BG = new BufferedImage[3];
        BG[0] = ImageLoader.loadImage("/BG/runBG0.png");
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
            curHeight += rowHeight[i];
            curWidth = 0;
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

