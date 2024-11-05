package entities.updateables.mario.actions;

import java.awt.event.KeyEvent;

import entities.updateables.mario.Mario;
import game.Game;
import utils.BasePrioritizable;
import utils.Direction;
import utils.KeyStatus;

public class ResolveHorizontalMovementDirection extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 300;
    protected Mario mario;

    public ResolveHorizontalMovementDirection(Mario m) {
        priority = DEFAULT_PRIORITY;
        mario = m;
    }

    @Override
    public void execute() {
        Direction movementDirection = Direction.NONE;

        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            movementDirection = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            movementDirection = Direction.sum(movementDirection, Direction.LEFT);
        }

        mario.setMovementDirection(movementDirection);
    }
}
