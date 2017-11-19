
package Main.States;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuInterFace extends State {
    private boolean check = true;
    private String scorecr;
    public MenuInterFace(GamePanel game) throws IOException {
        super(game);
        try {
            BufferedReader br = new BufferedReader(new FileReader("score.txt"));
            scorecr = br.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuInterFace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        g.drawString(scorecr, 80, 430);
    }
    
}
