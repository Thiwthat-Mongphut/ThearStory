package Main.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
    public static ArrayList<BufferedImage[]> TearImg;
    public static BufferedImage[] Tiles = new BufferedImage[50];
    public static BufferedImage menuBG, startIcon, dogSkin, interFaceBg, s1;
    
    // Size and Number of Thear Frame 
    private static int numFrame[] = {3,3};
    private static int frameWidth[] = {130,130};
    private static int frameHeight[] = {87,87};

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
        
        // main menu
        interFaceBg = ImageLoader.loadImage("/interFace/Bg3.png");
        s1 =  ImageLoader.loadImage("/interFace/letgo.png");
        menuBG = ImageLoader.loadImage("/BG/Menu.png");
        startIcon = ImageLoader.loadImage("/SFX/StartIcon.png");
        dogSkin = ImageLoader.loadImage("/BG/Skin.png");
        }
}

