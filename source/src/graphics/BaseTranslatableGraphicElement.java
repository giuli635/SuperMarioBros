package graphics;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;

import game.SingletonGraphicEngine;

public abstract class BaseTranslatableGraphicElement extends BaseGraphicElement implements TranslatableGraphicElement {
    protected JLabel label;
    protected Rectangle bounds;

    public BaseTranslatableGraphicElement() {
        label = new JLabel();
        bounds = label.getBounds();
    }

    @Override
    public JComponent getComponent() {
        return label;
    }

    @Override
    public void add() {
        SingletonGraphicEngine.instance().add(this);
    }

    @Override
    public void remove() {
        SingletonGraphicEngine.instance().remove(this);
    }

    @Override
    public void translate(int dx, int dy) {
        SingletonGraphicEngine.instance().addToRedraw(this);
        bounds.translate(dx, dy);
    }

    @Override
    public Point getPosition() {
        return bounds.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        SingletonGraphicEngine.instance().addToRedraw(this);
        bounds.setLocation(x, y);
    }
    
    @Override
    public void redraw() {
        Rectangle boundsToDraw = new Rectangle(bounds);
        boundsToDraw.setLocation(
            (int) bounds.getX(),
            (int) (SingletonGraphicEngine.instance().getPanelSize().getHeight() - bounds.getY())
        );
        label.setBounds(boundsToDraw);
    }
}
