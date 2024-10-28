package entities.mario.actions;

import java.awt.event.KeyEvent;

import colliders.Direction;
import entities.mario.Mario;
import game.Game;
import game.KeyStatus;

public class ResolveHorizontalMovementDirection extends BaseMarioAction {
    public static final int DEFAULT_PRIORITY = 300;

    public ResolveHorizontalMovementDirection() {
        priority = DEFAULT_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        Direction movementDirection = Direction.NONE;

        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            movementDirection = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            movementDirection = Direction.sum(movementDirection, Direction.LEFT);
        }

        m.setMovementDirection(movementDirection);
    }
}
