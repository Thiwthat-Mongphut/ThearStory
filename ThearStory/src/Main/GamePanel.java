package Main;

import Main.Graphics.Assets;
import Main.Input.KeyManager;
import Main.Input.MouseManager;
import Main.States.MainState;
import Main.States.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GamePanel implements Runnable{

    private Display display;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    
    private boolean running = false;
    private Thread  thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    // States
    public State gameState;
    
    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    public GamePanel(){
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void init(){
        display = new Display(WIDTH, HEIGHT);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        // load images
        Assets.init();
        // Set first state
        gameState = new MainState(this);
        State.setState(gameState);

    }

    private void tick(){
        keyManager.tick();
        
        if(State.getState() != null)
            State.getState().tick();
    }
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear screen
        g.clearRect(0, 0, WIDTH,HEIGHT);
        
        // Draw here
        if(State.getState() != null)
            State.getState().render(g);
        // End draw
        
        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                delta--;
            }
            
        }
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }    
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
    }

}
