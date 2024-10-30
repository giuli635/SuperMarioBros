package entities.updateables.mario.actions;

import entities.updateables.mario.Mario;
import utils.Direction;

public class HorizontalMovement extends BaseMarioAction {
    public static final int DEFAULT_PRIORITY = 100;

    public static final int MAX_SPEEDX = 6;
    public static final float DEFAULT_ACCELERATIONX = 0.15f;
    public static final float DEFAULT_DECELERATIONX = 0.18f;
    public static final int MIN_SPEEDX = 2;

    public HorizontalMovement() {
        priority = DEFAULT_PRIORITY;
    }

    @Override
    public void execute(Mario m) {
        float speedX = m.getSpeedX();

        Direction currentDirection = Direction.horizontalDirectionFromSign((int) speedX);
        
        if (!m.isFalling()) {
            handleGroundHorizontalMovement(m, currentDirection);
        } else {
            handleAirHorizontalMovement(m, currentDirection);
        }
    }

    protected void handleAirHorizontalMovement(Mario m, Direction currentDirection) {
        float speedX = m.getSpeedX();
        Direction movementDirection = m.getMovementDirection();

        if (speedX < MIN_SPEEDX)  {
            speedX = MIN_SPEEDX;
        }

        if (movementDirection == currentDirection || currentDirection == Direction.NONE) {
            speedX = Direction.signFromDirection(movementDirection) * Math.abs(speedX);
        } else {
            speedX = 0;
        }

        m.setSpeedX(speedX);
    }

    protected void handleGroundHorizontalMovement(Mario m, Direction currentDirection) {
        float speedX = m.getSpeedX();
        float accelerationX = 0;
        Direction movementDirection = m.getMovementDirection();

        if (movementDirection != Direction.NONE) {
            if (currentDirection == Direction.NONE || movementDirection == currentDirection) {
                if (speedX == 0) {
                    speedX = Direction.signFromDirection(movementDirection) * MIN_SPEEDX;
                } else {
                    if (Math.abs(speedX) < MAX_SPEEDX) {
                        accelerationX = Direction.signFromDirection(movementDirection) * DEFAULT_ACCELERATIONX;
                    }
                }
            } else {
                if (Math.floor(Math.abs(speedX)) < 2 * DEFAULT_DECELERATIONX) {
                    speedX = 0;
                } else {
                    accelerationX = 2 * -Direction.signFromDirection(currentDirection) * DEFAULT_DECELERATIONX;
                }
            }
        } else {
            if (Math.floor(Math.abs(speedX)) < DEFAULT_DECELERATIONX) {
                speedX = 0;
            } else {
                accelerationX = -Direction.signFromDirection(currentDirection) * DEFAULT_DECELERATIONX;
            }
        }

        m.setSpeedX(speedX + accelerationX);
        m.setAccelerationX(accelerationX);
    }
}
