package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import entities.updateables.mario.Mario;
import game.Game;
import game.SoundManager;
import utils.BasePrioritizable;
import utils.KeyStatus;

public class VerticalMovement extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 400;

    public static final float ACCELERATION_WITH_W = 1f;
    public static final int MIN_SPEEDY = -8;
    public static final int INITIAL_SPEEDY = 12;
    public static final float ACCELERATIONY = 0.2f;

    protected Mario mario;

    public VerticalMovement(Mario m) {
        priority = DEFAULT_PRIORITY;
        mario = m;
    }

    @Override
    public void execute() {
        float speedY = mario.getSpeedY();

        if (Game.instance().getKeyStatus(KeyEvent.VK_W) == KeyStatus.PRESSED) {
            if (mario.isFalling()) {
                if (speedY > 0) {
                    speedY += ACCELERATION_WITH_W - Mario.GRAVITY;
                } else if (speedY > MIN_SPEEDY){
                    speedY -= Mario.GRAVITY;
                }
            } else {
                speedY = INITIAL_SPEEDY;
                SoundManager.instance().playSound("jumpSmall.wav");
            }
        } else {
            if (speedY > 0) {
                speedY += ACCELERATIONY - Mario.GRAVITY;
            } else if (speedY > MIN_SPEEDY){
                speedY -= Mario.GRAVITY;
            }
        }
        
        mario.setSpeedY(speedY);
    }
}
