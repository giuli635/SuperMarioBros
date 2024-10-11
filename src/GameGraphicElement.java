import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameGraphicElement implements GraphicElement {
    protected Entity entity;
    protected JLabel label;
    protected ImageIcon sprite;

    public GameGraphicElement(Entity e, ImageIcon s) {
        entity = e;
        sprite = s;
        label = new JLabel("hola mundo");
    }
    
    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void translate(int dx, int dy) {
        Point previousLocation = label.getLocation();
        label.setLocation((int) previousLocation.getX() + dx, (int) previousLocation.getY() + dy);
    }

    @Override
    public Point getPosition() {
        return label.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        label.setLocation(x, y);
    }

    @Override
    public ImageIcon getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(ImageIcon s) {
        sprite = s;
        label.setIcon(s);
    }
}
