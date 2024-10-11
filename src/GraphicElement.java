import java.awt.Point;

import javax.swing.ImageIcon;

public interface GraphicElement {
    public Entity getEntity();
    public void translate(int dx, int dy);
    public Point getPosition();
    public void setPosition(int x, int y);
    public ImageIcon getSprite();
    public void setSprite(ImageIcon s);
}
