package entities.mario.actions;

import java.awt.event.KeyEvent;

import colliders.Direction;
import entities.mario.Mario;
import game.Game;
import game.KeyStatus;

public class Crouch extends BaseMarioAction {
    public static final int DEFAULT_CROUCH_PRIORITY = 200;
    protected boolean crouched;

    public Crouch() {
        crouched = false;
        priority = DEFAULT_CROUCH_PRIORITY;
    }

    @Override
    public void execute(Mario mario) {
        if (Game.instance().getKeyStatus(KeyEvent.VK_S) == KeyStatus.PRESSED && (!mario.isFalling() || crouched)) {
            mario.getGraphicElement().setSprite(Mario.MARIO_CROUCHING);
            mario.setMovementDirection(Direction.NONE);
            mario.setOverriteSprite(true);
            crouched = true;
        } else {
            mario.setOverriteSprite(false);
            crouched = false;
        }
    }
}
