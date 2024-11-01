package graphics;
import java.awt.Point;

import javax.swing.JComponent;

public interface GraphicElement {
    public void translate(int dx, int dy);
    public Point getPosition();
    public void setPosition(int x, int y);
    public JComponent getComponent();
    public void draw();
    public void setAdded(boolean status);
    public boolean added();
    public void add();
    public void remove();
    public void reload();
}
