public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void alterX(int diff) {
        x += diff;
    }

    public void alterY(int diff) {
        y += diff;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
