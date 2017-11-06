package Main.States;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;

public class MainState extends State{

    private int[] butWeight = {180,178,175,173,170,168,165,163,160,
                                163,165,168,170,173,175,178},
                  butHeight = {78,77,76,75,74,73,72,71,69,71,72,73,74,75,76,77};
    private int frame = 0;
    
    private long lastTime;
    
    public MainState(GamePanel game){
        super(game);
        lastTime = System.nanoTime();
    }
    
    @Override
    public void tick() {
        int mouseX = game.getMouseManager().getMouseX();
        int mouseY = game.getMouseManager().getMouseY();
        // if start button is clicked, change to tutorial state
        if(mouseX >= 225 && mouseX <= 370 && mouseY >= 365 && mouseY <= 430 
                && game.getMouseManager().isLeftPressed()){
///////////////////////// เปลี่ยน State /////////////////////////////////
            game.gameState = new RunMiniGame(game);
            State.setState(game.gameState);
        }
        
        if((System.nanoTime() - lastTime) / 10000000 > 1){
            if(frame == 15)
                frame = 0;
            else
                frame++;
            lastTime = System.nanoTime();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, null);
        g.drawImage(Assets.startIcon, 215+((butWeight[0]-butWeight[frame])/2),
                360+((butHeight[0]-butHeight[frame])/2),
                butWeight[frame],butHeight[frame],null);
    }
    
}
