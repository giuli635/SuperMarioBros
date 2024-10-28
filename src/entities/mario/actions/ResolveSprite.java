package entities.mario.actions;

import colliders.Direction;
import entities.mario.Mario;
import graphics.GameGraphicElement;

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
                graphicElement.setSprite(Mario.MARIO_JUMP);
            } else if (m.getSpeedX() == 0) {
                graphicElement.setSprite(Mario.MARIO_STILL);
            } else if (Math.abs(m.getAccelerationX()) == 2 * HorizontalMovement.DEFAULT_DECELERATIONX){
                graphicElement.setSprite(Mario.MARIO_STOPPING);
            } else {
                graphicElement.setSprite(getWalkingSprites());
            }
        }

        m.getCollider().setSize(
            graphicElement.getCurrentSprite().getIconWidth(),
            graphicElement.getCurrentSprite().getIconHeight()
        );

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
