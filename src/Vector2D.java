import java.awt.Point;

public class Vector2D {
    protected Point start;
    protected Point end;
    protected double xComponent;
    protected double yComponent;

    public Vector2D (Point s, Point e) {
        start = s;
        end = e;
        xComponent = start.getX() - end.getX();
        yComponent = start.getY() - end.getY();
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

    public double getXComponent() {
        return xComponent;
    }

    public double getYComponent() {
        return yComponent;
    }
}

