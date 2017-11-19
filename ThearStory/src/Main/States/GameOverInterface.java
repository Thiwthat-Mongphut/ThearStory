
package Main.States;


import Main.GamePanel;
import Main.Graphics.Assets;
import Main.States.RunMiniGame;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameOverInterface extends State {
     private boolean check = true ;
     private boolean check2 = true ;
     private boolean check3 = true;
    public GameOverInterface(GamePanel game) {
        super(game);
        //check = true;
    }

    @Override
    public void tick() {
        int mouseX = game.getMouseManager().getMouseX();
        int mouseY = game.getMouseManager().getMouseY();
        if(mouseX >= 290 && mouseX <= 530 && mouseY >= 345 && mouseY <= 450){
           check = false;
           if(game.getMouseManager().isLeftPressed()){
               try {
                   game.gameState = new MenuInterFace(game);
               } catch (IOException ex) {
                   Logger.getLogger(GameOverInterface.class.getName()).log(Level.SEVERE, null, ex);
               }
               State.setState(game.gameState);
           }
        }
        else{
            check = true;
        }
        if(mouseX >= 580 && mouseX <= 770 && mouseY >= 345 && mouseY <= 450){
            check2 = false;
            if(game.getMouseManager().isLeftPressed()){
                System.exit(0);
            }
        }
        else{
            check2 = true;
        }
        if(mouseX >= 50 && mouseX <= 240 && mouseY >= 345 && mouseY <= 450){
            check3 = false;
            if(game.getMouseManager().isLeftPressed()){
                 game.gameState = new RunMiniGame(game, 6, 0, 0); // go to miniGame
                State.setState(game.gameState);
            }
            
        }
        else{
            check3 = true;
        }
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.interFaceBg, 0, 0, null);
        g.drawImage(Assets.gameOver, 0, 0, null);
        if(check){
            g.drawImage(Assets.menuButton, 290, 345, null);
        }
        else{
            g.drawImage(Assets.menuButton2, 270, 325, null);
        }
        if(check2){
            g.drawImage(Assets.closeButton, 580, 345, null);
        }
        else{
            g.drawImage(Assets.closeButton2, 560, 325, null);
        }
        if(check3){
            g.drawImage(Assets.restartButton, 50, 345, null);
        }
        else{
            g.drawImage(Assets.restartButton2, 30, 325, null);
        }
        g.setColor(Color.WHITE);
        g.setFont(Assets.gothicFontBig);
        g.drawString(String.valueOf(RunMiniGame.score), 320, 305);
    }
    
}
