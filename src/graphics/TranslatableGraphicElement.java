package graphics;

import java.awt.Point;

public interface TranslatableGraphicElement extends GraphicElement {
    public void translate(int dx, int dy);
    public Point getPosition();
    public void setPosition(int x, int y);
}
