
package Main.States;


import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;


public class GameOverInterface extends State {
     private boolean check = true ;
     private boolean check2 = true ;
    public GameOverInterface(GamePanel game) {
        super(game);
        //check = true;
    }

    @Override
    public void tick() {
        int mouseX = game.getMouseManager().getMouseX();
        int mouseY = game.getMouseManager().getMouseY();
        if(mouseX >= 30 && mouseX <= 400 && mouseY >= 350 && mouseY <= 450){
           check = false;
           if(game.getMouseManager().isLeftPressed()){
               game.gameState = new MenuInterFace(game);
               State.setState(game.gameState);
           }
        }
        else{
            check = true;
        }
        if(mouseX >= 440 && mouseX <= 770 && mouseY >= 350 && mouseY <= 450){
            check2 = false;
            if(game.getMouseManager().isLeftPressed()){
                System.exit(0);
            }
        }
        else{
            check2 = true;
        }
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.interFaceBg, 0, 0, null);
        g.drawImage(Assets.gameOver, 0, 0, null);
        if(check){
            g.drawImage(Assets.menuButton, 0, 350, null);
        }
        else{
            g.drawImage(Assets.menuButton2, 0, 330, null);
        }
        if(check2){
            g.drawImage(Assets.closeButton, 410, 350, null);
        }
        else{
            g.drawImage(Assets.closeButton2, 410, 330, null);
        }
    }
    
}
