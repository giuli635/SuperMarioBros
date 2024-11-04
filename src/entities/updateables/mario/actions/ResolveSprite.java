package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;
import game.Game;
import graphics.GameGraphicElement;
import utils.BasePrioritizable;
import utils.Direction;

public class ResolveSprite extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 0;
    protected static final int FRAMES_PER_SPRITE = 10;

    protected int walkingSprite;

    public ResolveSprite() {
        walkingSprite = 0;
        priority = DEFAULT_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        GameGraphicElement graphicElement = m.getGraphicElement();
        Direction movementDirection = m.getMovementDirection();

        if (!m.overriteSprite()){
            if (m.isFalling()) {
                m.setSprite(Mario.MARIO_JUMP);
            } else if (m.getSpeedX() == 0) {
                m.setSprite(Mario.MARIO_STILL);
            } else if (Math.abs(m.getAccelerationX()) == 2 * HorizontalMovement.DEFAULT_DECELERATIONX){
                m.setSprite(Mario.MARIO_STOPPING);
            } else {
                m.setSprite(getWalkingSprites());
            }
        }

        if (movementDirection == Direction.LEFT && !graphicElement.isFlipped()) {
            graphicElement.flipSprite();
        } else if (movementDirection == Direction.RIGHT && graphicElement.isFlipped()) {
            graphicElement.flipSprite();
        }
    }

    protected String getWalkingSprites() {
        walkingSprite += (Game.instance().getFrames() % FRAMES_PER_SPRITE) == 0 ? 1 : 0;
        walkingSprite %= Mario.MARIO_WALKING.length;
        return Mario.MARIO_WALKING[walkingSprite];
    }
}
