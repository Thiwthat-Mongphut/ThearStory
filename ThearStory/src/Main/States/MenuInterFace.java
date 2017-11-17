
package Main.States;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class MenuInterFace extends State {
    private boolean check = true;
    public MenuInterFace(GamePanel game) {
        super(game);
    }

    @Override
    public void tick() {
        int mouseX = game.getMouseManager().getMouseX();
        int mouseY = game.getMouseManager().getMouseY();
        if(mouseX >= 450 && mouseX <= 770 && mouseY >= 350 && mouseY <= 450){
            check = false;
            if(game.getMouseManager().isLeftPressed()){
               game.gameState = new RunMiniGame(game, 6, 0, 0); // go to miniGame
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
            g.drawImage(Assets.letsGoButton, 450, 350, null);
        }
        else{
            g.drawImage(Assets.letsGoButton2, 430, 330, null);
        }
        
        g.setColor(Color.WHITE);
        g.setFont(Assets.gothicFontBig);
        g.drawString(String.valueOf(RunMiniGame.score), 80, 430);
    }
    
}
