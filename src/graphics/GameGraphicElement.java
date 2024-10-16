package graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import entities.Entity;

public class GameGraphicElement implements GraphicElement {
    protected Entity entity;
    protected JLabel label;
    protected ImageIcon sprite;
    protected Rectangle bounds;
    protected boolean toUpdate;

    public GameGraphicElement(Entity e, ImageIcon s) {
        entity = e;
        sprite = s;
        label = new JLabel();
        bounds = label.getBounds();
        setSprite(s);
    }
    
    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void translate(int dx, int dy) {
        if ((bounds.getX() + dx) >= 0 && (bounds.getY() + dy) >= 0) {
            toUpdate = true;
            bounds.translate(dx, dy);
        }
    }

    @Override
    public Point getPosition() {
        return label.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        toUpdate = true;
        bounds.setLocation(x, y);
    }

    @Override
    public ImageIcon getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(ImageIcon s) {
        sprite = s;
        toUpdate = true;
        bounds.setSize(s.getIconWidth(), s.getIconHeight());
    }

    public void draw() {
        if (toUpdate) {
            label.setBounds(bounds);
            label.setIcon(sprite);
            toUpdate = false;
        }
    }

    @Override
    public JLabel getLabel() {
        return label;
    }
}
