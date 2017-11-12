
package Main.States;

import Main.GamePanel;
import Main.Graphics.Assets;
import java.awt.Graphics;

public class FirstInterFace extends State {

    public FirstInterFace(GamePanel game) {
        super(game);
    }

    @Override
    public void tick() {
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.interFaceBg, 0, 0, null);
        g.drawImage(Assets.s1, 550, 400, null);
    }
    
}
