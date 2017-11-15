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
        if(keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE]){
            up = true;
        }
        else
            up = false;
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        Enter = keys[KeyEvent.VK_E];
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
