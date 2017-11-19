package Main.States;

import Main.GamePanel;
import java.awt.Graphics;

public abstract class State {
    
    protected GamePanel game;
    
    private static State currentState = null;
    
    public State(GamePanel game){
        this.game = game;
    }
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
}
