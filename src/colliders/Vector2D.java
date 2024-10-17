package colliders;
import java.awt.Point;

public class Vector2D {
    protected Point start;
    protected Point end;
    protected double xComponent;
    protected double yComponent;

    public Vector2D (Point s, Point e) {
        start = s;
        end = e;
        calculateComponents();
    }

    protected void calculateComponents() {
        xComponent = -(start.getX() - end.getX());
        yComponent = -(start.getY() - end.getY());
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Vector2D sum(Vector2D v) {
        return new Vector2D(start, v.getEnd());
    }

    public void grow(int dx, int dy) {
        end.translate(dx, dy);
        calculateComponents();
    }

    public double getXComponent() {
        return xComponent;
    }

    public double getYComponent() {
        return yComponent;
    }

    protected Vector2D clone() {
        return new Vector2D(start, end);
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + ", x=" + xComponent + ", y=" + yComponent + "]";
    }
}

