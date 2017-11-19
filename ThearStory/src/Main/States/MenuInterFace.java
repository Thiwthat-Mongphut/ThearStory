
package Main.States;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class MenuInterFace extends State {
    
    private static int highScore = 0;
    private boolean check = true;
    public MenuInterFace(GamePanel game) {
        super(game);
    }

    @Override
    public void tick() {
        int mouseX = game.getMouseManager().getMouseX();
        int mouseY = game.getMouseManager().getMouseY();
        if(mouseX >= 485 && mouseX <= 805 && mouseY >= 350 && mouseY <= 450){
            check = false;
            if(game.getMouseManager().isLeftPressed()){
               game.gameState = new RunMiniGame(game, false); // go to miniGame
               State.setState(game.gameState);
            }
        }
        else{
            check = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.interFaceBg, 0, 0, null);
        if(check){
            g.drawImage(Assets.letsGoButton, 485, 350, null);
        }
        else{
            g.drawImage(Assets.letsGoButton2, 465, 330, null);
        }
        
        g.setColor(Color.WHITE);
        g.setFont(Assets.gothicFontBig);
        if(RunMiniGame.score > highScore)
            highScore = RunMiniGame.score;
        g.drawString(String.valueOf(highScore), 120, 430);
    }
    
}
