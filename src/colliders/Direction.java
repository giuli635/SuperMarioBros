package colliders;
public enum Direction {
    DOWN,
    LEFT,
    NONE,
    RIGHT,
    UP;

    public static Direction opposite(Direction d1) {
        return Direction.values()[-(d1.ordinal() - 2) + 2];
    }

    public static Direction sum(Direction d1, Direction d2) {
        return Direction.values()[((d1.ordinal() - 2) + (d2.ordinal() - 2)) + 2];
    }

    public static Direction horizontalDirectionFromSign(int d) {
        return Direction.values()[(int) Math.signum(d) + 2];
    }

    public static int signFromDirection(Direction d1) {
        return (int) Math.signum(d1.ordinal() - 2);
    }

    public static Direction verticalDirectionFromSign(int d) {
        return Direction.values()[((int) Math.signum(d) * 2) + 2];
    }
}
