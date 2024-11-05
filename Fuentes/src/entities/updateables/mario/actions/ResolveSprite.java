package entities.updateables.mario.actions;

import entities.updateables.MovementAnimator;
import entities.updateables.mario.Mario;
import graphics.GameGraphicElement;
import utils.BasePrioritizable;
import utils.Direction;

public class ResolveSprite extends BasePrioritizable implements MarioAction {
    public static final int DEFAULT_PRIORITY = 0;
    protected static final int FRAMES_PER_SPRITE = 10;
    Mario mario;

    MovementAnimator walkingSpritesAnimator;

    public ResolveSprite(Mario m) {
        mario = m;
        priority = DEFAULT_PRIORITY;
        walkingSpritesAnimator = new MovementAnimator(
            Mario.MARIO_WALKING, FRAMES_PER_SPRITE, mario
        );
    }

    @Override
    public void execute() {
        GameGraphicElement graphicElement = mario.getGraphicElement();
        Direction movementDirection = mario.getMovementDirection();

        if (!mario.overriteSprite()){
            if (mario.isFalling()) {
                mario.setSprite(Mario.MARIO_JUMP);
            } else if (mario.getSpeedX() == 0) {
                mario.setSprite(Mario.MARIO_STILL);
            } else if (Math.abs(mario.getAccelerationX()) == 2 * HorizontalMovement.DEFAULT_DECELERATIONX){
                mario.setSprite(Mario.MARIO_STOPPING);
            } else {
                walkingSpritesAnimator.animate();
            }
        }

        if (movementDirection == Direction.LEFT && !graphicElement.isFlipped()) {
            graphicElement.flipSprite();
        } else if (movementDirection == Direction.RIGHT && graphicElement.isFlipped()) {
            graphicElement.flipSprite();
        }
    }
}
