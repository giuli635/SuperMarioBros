package entities.mario.actions;

import java.awt.event.KeyEvent;
import entities.mario.Mario;
import game.Game;
import game.KeyStatus;

public class VerticalMovement extends BaseMarioAction {
    public static final int DEFAULT_VERTICAL_PRIORITY = 200;
    protected float accelerationYWithW = 1f;
    protected int minSpeedY = -8;
    protected int initialSpeedY = 12;
    protected float accelerationY = 0.2f;

    public VerticalMovement() {
        priority = DEFAULT_VERTICAL_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        float speedY = m.getSpeedY();
        float gravity = m.getGravity();

        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED) {
            if (m.isFalling()) {
                if (speedY > 0) {
                    speedY += accelerationYWithW - gravity;
                } else if (speedY > minSpeedY){
                    speedY -= gravity;
                }
            } else {
                speedY = initialSpeedY;
            }
        } else {
            if (speedY > 0) {
                speedY += accelerationY - gravity;
            } else if (speedY > minSpeedY){
                speedY -= gravity;
            }
        }
        
        m.setSpeedY(speedY);
    }
}
