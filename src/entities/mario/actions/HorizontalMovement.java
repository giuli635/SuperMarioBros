package entities.mario.actions;

import java.awt.event.KeyEvent;

import colliders.Direction;
import entities.mario.Mario;
import game.Game;
import game.KeyStatus;
import graphics.GameGraphicElement;

public class HorizontalMovement extends BaseMarioAction {
    public static final int DEFAULT_HORIZONTAL_PRIORITY = 0;

    protected float maxSpeedX = 6;
    protected float accelerationX = 0.1f;
    protected float decelerationX = 0.18f;
    protected float minSpeedX = 2;

    protected int animationFrameCounter = 0;
    protected int framesPerSprite = 10;
    protected int walkingSprite;

    public HorizontalMovement() {
        priority = DEFAULT_HORIZONTAL_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        float speedX = m.getSpeedX();

        Direction currentDirection = Direction.horizontalDirectionFromSign((int) speedX);
        Direction movementDirection = Direction.NONE;
        String newSprite;
        
        if (Game.instance().getKeyStatus(KeyEvent.VK_D) == KeyStatus.PRESSED) {
            movementDirection = Direction.RIGHT;
        }

        if (Game.instance().getKeyStatus(KeyEvent.VK_A) == KeyStatus.PRESSED) {
            movementDirection = Direction.sum(movementDirection, Direction.LEFT);
        }
        if (!m.isFalling()) {
            newSprite = handleGroundHorizontalMovement(m, movementDirection, currentDirection);
        } else {
            newSprite = handleAirHorizontalMovement(m, movementDirection, currentDirection);
        }

        resolveSpriteDirection(m, movementDirection, newSprite);
    }

    protected String handleAirHorizontalMovement(Mario m, Direction movementDirection, Direction currentDirection) {
        float speedX = m.getSpeedX();

        if (speedX < minSpeedX)  {
            speedX = minSpeedX;
        }

        if (movementDirection == currentDirection || currentDirection == Direction.NONE) {
            speedX = Direction.signFromDirection(movementDirection) * Math.abs(speedX);
        } else {
            speedX = 0;
        }

        m.setSpeedX(speedX);

        return Mario.MARIO_JUMP;
    }

    protected String handleGroundHorizontalMovement(Mario m, Direction movementDirection, Direction currentDirection) {
        String newSprite = Mario.MARIO_STILL;
        float speedX = m.getSpeedX();

        if (movementDirection != Direction.NONE) {
            if (currentDirection == Direction.NONE || movementDirection == currentDirection) {
                if (speedX == 0) {
                    speedX = Direction.signFromDirection(movementDirection) * minSpeedX;
                } else {
                    if (Math.abs(speedX) < maxSpeedX) {
                        speedX += Direction.signFromDirection(movementDirection) * accelerationX;
                    }
                }

                newSprite = setWalkingSprites();
            } else {
                if (Math.floor(Math.abs(speedX)) < 2 * decelerationX) {
                    speedX = 0;
                } else {
                    speedX -= 2 * Direction.signFromDirection(currentDirection) * decelerationX;
                    newSprite = Mario.MARIO_STOPPING;
                }
            }
        } else {
            if (Math.floor(Math.abs(speedX)) < decelerationX) {
                speedX = 0;
            } else {
                speedX -= Direction.signFromDirection(currentDirection) * decelerationX;
                newSprite = setWalkingSprites();
            }
        }

        m.setSpeedX(speedX);

        return newSprite;
    }

    protected String setWalkingSprites() {
        walkingSprite += (animationFrameCounter %= framesPerSprite) == 0 ? 1 : 0;
        walkingSprite %= 2;
        animationFrameCounter++;
        return Mario.MARIO_WALKING[walkingSprite];
    }

    protected void resolveSpriteDirection(Mario m, Direction movementDirection, String newSprite) {
        GameGraphicElement graphicElement = m.getGraphicElement();
        boolean flipped = graphicElement.isFlipped();
        graphicElement.setSprite(newSprite);
        if (movementDirection == Direction.LEFT || movementDirection == Direction.NONE && flipped) {
            graphicElement.flipSprite();
        }
    }
}
