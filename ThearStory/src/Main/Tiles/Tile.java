package Main.Tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static final int WIDTH = 32, HEIGHT = 32;
    
    // Create all tile
    public static Tile[] tiles = new Tile[50];
    
    // CLASS
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public int getID(){
        return id;
    }
}
