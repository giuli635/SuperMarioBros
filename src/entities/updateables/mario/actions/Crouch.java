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

    public Crouch() {
        crouched = false;
        priority = DEFAULT_PRIORITY;
    }

    @Override
    public void execute(Mario mario) {
        if (Game.instance().getKeyStatus(KeyEvent.VK_S) == KeyStatus.PRESSED && (!mario.isFalling() || crouched)) {
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
