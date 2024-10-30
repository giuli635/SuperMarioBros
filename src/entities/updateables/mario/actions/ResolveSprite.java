package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;
import graphics.GameGraphicElement;
import utils.Direction;

public class ResolveSprite extends BaseMarioAction {
    public static final int DEFAULT_PRIORITY = 0;

    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int walkingSprite;

    public ResolveSprite() {
        priority = DEFAULT_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        GameGraphicElement graphicElement = m.getGraphicElement();
        Direction movementDirection = m.getMovementDirection();
        boolean flipped = graphicElement.isFlipped();
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

        if (m.isInvulnerable()) {
            if (System.currentTimeMillis() % 20 == 0) {
                graphicElement.removeSprite();
            }
        }

        if (movementDirection == Direction.LEFT || movementDirection == Direction.NONE && flipped) {
            graphicElement.flipSprite();
        }
    }

    protected String getWalkingSprites() {
        walkingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        walkingSprite %= 2;
        animationFrameCounter++;
        return Mario.MARIO_WALKING[walkingSprite];
    }
}
