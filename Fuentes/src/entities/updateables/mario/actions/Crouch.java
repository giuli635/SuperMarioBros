package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import entities.updateables.mario.Mario;
import game.Game;
import utils.BasePrioritizable;
import utils.Direction;
import utils.KeyStatus;

public class Crouch extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 200;
    protected boolean crouched;
    protected Mario mario;

    public Crouch(Mario m) {
        crouched = false;
        priority = DEFAULT_PRIORITY;
        mario = m;
    }

    @Override
    public void execute() {
        boolean sPressed = Game.instance().getKeyStatus(KeyEvent.VK_S) == KeyStatus.PRESSED;
        if (sPressed && (!mario.isFalling() || crouched)) {
            mario.setSprite(Mario.MARIO_CROUCHING);
            mario.setMovementDirection(Direction.NONE);
            mario.setOverriteSprite(true);
            crouched = true;
        } else {
            unCrouch(mario);
        }
    }

    public void unCrouch(Mario mario) {
        mario.setOverriteSprite(false);
        crouched = false;
    }
}
