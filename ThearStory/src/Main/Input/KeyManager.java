package Main.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right, Enter;
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    public void tick(){
        if(keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_W]){
            up = true;
        }
        else
            up = false;
        if(keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]){
            down = true;
        }
        else
            down = false;
        if(keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]){
            left = true;
        }
        else
            left = false;
        if(keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]){
            right = true;
        }
        else
            right = false;
        if(keys[KeyEvent.VK_E] || keys[KeyEvent.VK_SPACE]){
            Enter = true;
        }
        else
            Enter = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }
    
}
