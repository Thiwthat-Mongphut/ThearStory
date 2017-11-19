package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ZombieM extends Creature{
    
    protected GamePanel game;
    protected BufferedImage[] img;
    protected int walkFrame;
    private float vX, vY, speedUP = 1, limitLeft, limiRight;
    private boolean rightRoom, leftRoomZ = true, lock = false, protectPlayer = false;
    private long timeUnit = 300000000, lastTime;
    
    public ZombieM(GamePanel game, float x, float y) {
        super(x, y);
        this.game = game;
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.ZombieImg.get(1);
        walkFrame = 0;
    }

    @Override
    public void tick() 
    {
        if(leftRoomZ && !rightRoom || !leftRoomZ && rightRoom)
        {
            goToPlayer();
        }
        else if(!leftRoomZ && !rightRoom || leftRoomZ && rightRoom )
        {
            goToDownStair();
        }
    }
    
    public void goToDownStair()
    {
        
        if(y == 66 || y == 226)
        {
            // ซ้ายบนและกลาง
            if(leftRoomZ)
            {
                limitLeft = 0;
                limiRight = 340;
                if(x < limiRight && !lock)
                {
                    x += speedUP;
                    walkRight();
                    if(x == limiRight)
                        lock = true;
                }
                else if(x > limitLeft && lock)
                {
                    x -= speedUP;
                    walkLeft();
                    if(x == limitLeft)
                        lock = false;
                }
            }
            // ขวาบนและกลาง
            else
            {
                limitLeft = 415;
                limiRight = 780;
                if(x < limiRight && !lock)
                {
                    x += speedUP;
                    walkRight();
                    if(x == limiRight)
                        lock = true;
                }
                else if(x > limitLeft && lock)
                {
                    x -= speedUP;
                    walkLeft();
                    if(x == limitLeft)
                        lock = false;
                }
            }
        }
        // End ชั้นกลางแและบน
        
        //ชั้นล่าง
        else if(y == 386)
        {
            limitLeft = 0;
            limiRight = 780;
            if(x < limiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == limiRight)
                    lock = true;
            }
            else if(x > limitLeft && lock)
            {
                x -= speedUP;
                walkLeft();
                if(x == limitLeft)
                    lock = false;
            }
            
            if(x >= 400)
                leftRoomZ = false;
            
            else
                leftRoomZ = true;
        }
        
        // ลงบรรได
        if(y < 386)
        {
            if(y == 66)
            {
                if(x == 75 || x == 245 || x == 575 || x == 645)
                {
                    y += 160;
                    protectPlayer = true;
                }
            }
            
            else if(y == 226)
            {
                if(x == 0 || x == 170 || x == 480 ||x == 0 || x == 650)
                {
                    y += 160;
                    protectPlayer = true;
                }
            }
        }
    }
    
    
    public void goToPlayer()
    {
        if(rightRoom && y < 386)
        {
            limitLeft = 415;
            limiRight = 780;
            if(x < limiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == limiRight)
                    lock = true;
            }
            else if(x > limitLeft && lock)
            {
                x -= speedUP;
                walkLeft();
                if(x == limitLeft)
                    lock = false;
            }
        }
        else if(!rightRoom && y < 386)
        {
            limitLeft = 0;
            limiRight = 340;
            if(x < limiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == limiRight)
                    lock = true;
            }
            else if(x > limitLeft && lock)
            {
                x -= speedUP;
                walkLeft();
                if(x == limitLeft)
                    lock = false;
            }
        }
        else if(y == 386)
        {
            limitLeft = 0;
            limiRight = 780;
            if(x < limiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == limiRight)
                    lock = true;
            }
            else if(x > limitLeft && lock)
            {
                x -= speedUP;
                walkLeft();
                if(x == limitLeft)
                    lock = false;
            }
            if(x >= 400)
                leftRoomZ = false;
            
            else
                leftRoomZ = true;
        }
        if(y < vY - 9 && y == 66) // เตี้ยล่างสุด ซอมบนสุด
        {
            if(x == 75 || x == 245 || x == 575 || x == 745)
                {
                    y += 160;
                    protectPlayer = true;
                }
        }
        
        else if(y < vY - 9 && y == 226) // เตี้ยล่าง ซอมกลาง
        {
            if(x == 0 || x == 170 || x == 480 || x == 650)
                {
                    y += 160;
                    protectPlayer = true;
                }
        }
        
        else if(y > vY - 9 && y == 226) // เตี้ยบน ซอมกลาง
        {
            if(x == 75 || x == 245 || x == 575 || x == 745)
                {
                    y -= 160;
                    protectPlayer = true;
                }
        }
        
        else if(y == vY - 9)
        {
            if(x >= 400)
                leftRoomZ = false;
            
            else
                leftRoomZ = true;
        }
        
        else if(y > vY - 9 && y == 386) // เตี้ยบน ซอมล่างสุด
        {
            if(x == 0 || x == 170 || x == 480 || x == 650)
                {
                    y -= 160;
                    protectPlayer = true;
                }
        }
    }
    
    public void walkLeft()
    {
        if(System.nanoTime() / timeUnit - lastTime >= 1)
        {
            if(walkFrame >= 9)
            {
                walkFrame = 0;
                lastTime = System.nanoTime() / timeUnit;
            }
            else
            {
                walkFrame++;
                lastTime = System.nanoTime() / timeUnit;
            }
        }
        img = Assets.ZombieImg.get(0);   
    }
    
    public void walkRight()
    {
        if(System.nanoTime() / timeUnit - lastTime >= 1)
        {
            if(walkFrame >= 9)
            {
                walkFrame = 0;
                lastTime = System.nanoTime() / timeUnit;
            }
            else
            {
                walkFrame++;
                lastTime = System.nanoTime() / timeUnit;
            }
        }
        img = Assets.ZombieImg.get(1);   
    }
    
    public int getWidth(){
        return img[walkFrame].getWidth();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(img[walkFrame], (int)x, (int)y, null);
    }

    public void setvX(float vX) {
        this.vX = vX;
    }

    public void setvY(float vY) {
        this.vY = vY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    

    public void setRightRoom(boolean rightRoom) {
        this.rightRoom = rightRoom;
    }
    
    public boolean getProtectPlayer(){
        return protectPlayer;
    }
          
    public void setProtectPlayer(boolean ProtectPlayer){
        this.protectPlayer = ProtectPlayer;
    }
}
