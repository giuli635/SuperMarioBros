import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Mario extends GameEntity {
    protected Point position;
    protected int speedX; 
    protected int lifes;
    protected boolean loaded;

    public Mario() {
        super();
        speedX = 10;
        position = collider.getPosition();
        graphicElement.setSprite(new ImageIcon("sprites/mario.png"));
    }

    public Entity clone() {
        return new Mario();
    }

    public void update() {
        int[] keysToListen = new int[]{KeyEvent.VK_D, KeyEvent.VK_A};
        int i = 0;
        while (!Game.instance().getKeyPressed(keysToListen[i])) {
            i++;
        }
        graphicElement.translate(((int) Math.pow(-1, i)) * speedX, 0);
    }
}
