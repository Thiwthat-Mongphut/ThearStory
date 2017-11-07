package Main.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
    public static ArrayList<BufferedImage[]> TearImg;
    public static ArrayList<BufferedImage[]> Obj;
    public static ArrayList<BufferedImage> Tiles;
    public static BufferedImage[] BG;
    public static BufferedImage menuBG, startIcon;
    
    // Size and Number of Thear Sprite
    private static int numFrame[] = {3,3,1};
    private static int frameWidth[] = {130,130,130};
    private static int frameHeight[] = {87,87,55};
    
    // Size and Number of Obj Sprite
    private static int objFrame = 1;
    private static int objFrameWidth[][] = {{31,29,28,36}};
    private static int objFrameHeight[][] = {{34,17,19,22}};
    
    // Size and Number of Tiles
    private static int tileFrame = 1;
    private static int tileFrameWidth[] = {458};
    private static int tileFrameHeight[] = {50};
    
    public static void init(){
        SpriteSheet sheet;
        // Thear Crop
        sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/TearSprite.png"));
        int curHeight = 0;
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
        
        // Background;
        BG = new BufferedImage[4];
        BG[0] = ImageLoader.loadImage("/BG/clouds.gif");
        BG[1] = ImageLoader.loadImage("/BG/sky.gif");
        BG[2] = ImageLoader.loadImage("/BG/mountains.gif");
        BG[3] = ImageLoader.loadImage("/BG/sunset.gif");
        
        // main menu
        menuBG = ImageLoader.loadImage("/BG/Menu.png");
        startIcon = ImageLoader.loadImage("/SFX/StartIcon.png");
       
        
        // Objects
        sheet = new SpriteSheet(ImageLoader.loadImage("/Objects/ObjectsSprite.png"));
        curHeight = 0;
        int curWidth = 0;
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
        
        // Tiles
        sheet = new SpriteSheet(ImageLoader.loadImage("/Tilesets/Tiles.png"));
        curWidth = 0;
        Tiles = new ArrayList<BufferedImage>();
        for(int i=0; i<tileFrame; i++){
            Tiles.add(sheet.crop(curWidth, 0, 
                    tileFrameWidth[i], tileFrameHeight[i]));
            curWidth += tileFrameWidth[i];
        }
        
    }
}

