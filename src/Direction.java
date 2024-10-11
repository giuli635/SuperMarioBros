public enum Direction {
    LEFT,
    RIGTH,
    UP,
    DOWN;

    public static Direction opposite(Direction d1) {
        switch (d1) {
            case LEFT:
                return RIGTH;
            case RIGTH:
                return LEFT;
            case UP:
                return DOWN;
            default:
                return UP;
        }
    }
}
