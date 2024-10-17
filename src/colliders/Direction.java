package colliders;
public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public static Direction opposite(Direction d1) {
        switch (d1) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            default:
                return UP;
        }
    }
}
