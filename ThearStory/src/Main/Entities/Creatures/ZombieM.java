package Main.Entities.Creatures;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ZombieM extends Zombie{

    
    protected BufferedImage[] img;
    protected int walkFrame;
    private float vX, vY, speedUP = 1, limitLeft, LimiRight;
    private boolean rightRoom, LeftRoomZ = true, lock = false, ProtectPlayer = false;
    private long timeUnit = 300000000, lastTime;
    
    public ZombieM(GamePanel game, float x, float y) {
        super(game, x, y);
        lastTime = System.nanoTime() / 1000000000;
        img = Assets.ZombieImg.get(1);
        walkFrame = 0;
    }

    @Override
    public void tick() 
    {
        if(LeftRoomZ && !rightRoom || !LeftRoomZ && rightRoom)
        {
            gotoPlayer();
        }
        else if(!LeftRoomZ && !rightRoom || LeftRoomZ && rightRoom )
        {
            gotoDownStair();
        }
    }
    
    public void gotoDownStair()
    {
        
        if(y == 66 || y == 226)
        {
            // ซ้ายบนและกลาง
            if(LeftRoomZ)
            {
                limitLeft = 0;
                LimiRight = 340;
                if(x < LimiRight && !lock)
                {
                    x += speedUP;
                    walkRight();
                    if(x == LimiRight)
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
                LimiRight = 780;
                if(x < LimiRight && !lock)
                {
                    x += speedUP;
                    walkRight();
                    if(x == LimiRight)
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
            LimiRight = 780;
            if(x < LimiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == LimiRight)
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
                LeftRoomZ = false;
            
            else
                LeftRoomZ = true;
        }
        
        // ลงบรรได
        if(y < 386)
        {
            if(y == 66)
            {
                if(x == 75 || x == 245 || x == 575 || x == 645)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
            
            else if(y == 226)
            {
                if(x == 0 || x == 170 || x == 480 ||x == 0 || x == 650)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
            }
        }
    }
    
    
    public void gotoPlayer()
    {
        if(rightRoom && y < 386)
        {
            limitLeft = 415;
            LimiRight = 780;
            if(x < LimiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == LimiRight)
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
            LimiRight = 340;
            if(x < LimiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == LimiRight)
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
            LimiRight = 780;
            if(x < LimiRight && !lock)
            {
                x += speedUP;
                walkRight();
                if(x == LimiRight)
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
        if(y < vY - 9 && y == 66) // เตี้ยล่างสุด ซอมบนสุด
        {
            if(x == 75 || x == 245 || x == 575 || x == 745)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
        }
        
        else if(y < vY - 9 && y == 226) // เตี้ยล่าง ซอมกลาง
        {
            if(x == 0 || x == 170 || x == 480 || x == 650)
                {
                    y += 160;
                    ProtectPlayer = true;
                }
        }
        
        else if(y > vY - 9 && y == 226) // เตี้ยบน ซอมกลาง
        {
            if(x == 75 || x == 245 || x == 575 || x == 745)
                {
                    y -= 160;
                    ProtectPlayer = true;
                }
        }
        
        else if(y == vY - 9)
        {
            if(x >= 400)
                LeftRoomZ = false;
            
            else
                LeftRoomZ = true;
        }
        
        else if(y > vY - 9 && y == 386) // เตี้ยบน ซอมล่างสุด
        {
            if(x == 0 || x == 170 || x == 480 || x == 650)
                {
                    y -= 160;
                    ProtectPlayer = true;
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

    @Override
    public void setvX(float vX) {
        this.vX = vX;
    }

    @Override
    public void setvY(float vY) {
        this.vY = vY;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
    

    @Override
    public void setRightRoom(boolean rightRoom) {
        this.rightRoom = rightRoom;
    }
    
    public boolean getProtectPlayer(){
        return ProtectPlayer;
    }
          
    public void setProtectPlayer(boolean ProtectPlayer){
        this.ProtectPlayer = ProtectPlayer;
    }
}
