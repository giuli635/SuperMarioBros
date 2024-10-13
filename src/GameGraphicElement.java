import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameGraphicElement implements GraphicElement {
    protected Entity entity;
    protected JLabel label;
    protected ImageIcon sprite;

    public GameGraphicElement(Entity e, ImageIcon s) {
        entity = e;
        sprite = s;
        label = new JLabel();
        setSprite(s);
    }
    
    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void translate(int dx, int dy) {
        Rectangle newRect = label.getBounds();
        newRect.translate(dx, dy);
        label.setBounds(newRect);
    }

    @Override
    public Point getPosition() {
        return label.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        Rectangle newRect = label.getBounds();
        newRect.setLocation(x, y);
        label.setBounds(newRect);
    }

    @Override
    public ImageIcon getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(ImageIcon s) {
        sprite = s;
        Rectangle newRect = label.getBounds();
        newRect.setSize(s.getIconWidth(), s.getIconHeight());
        label.setBounds(newRect);
        label.setIcon(s);
    }

    @Override
    public JLabel getLabel() {
        return label;
    }
}
