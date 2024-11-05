package graphics;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;

import game.GraphicEngine;

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
        GraphicEngine.instance().add(this);
    }

    @Override
    public void remove() {
        GraphicEngine.instance().remove(this);
    }

    @Override
    public void translate(int dx, int dy) {
        GraphicEngine.instance().addToRedraw(this);
        bounds.translate(dx, dy);
    }

    @Override
    public Point getPosition() {
        return bounds.getLocation();
    }

    @Override
    public void setPosition(int x, int y) {
        GraphicEngine.instance().addToRedraw(this);
        bounds.setLocation(x, y);
    }
    
    @Override
    public void redraw() {
        Rectangle boundsToDraw = new Rectangle(bounds);
        boundsToDraw.setLocation(
            (int) bounds.getX(),
            (int) (GraphicEngine.instance().getPanelSize().getHeight() - bounds.getY())
        );
        label.setBounds(boundsToDraw);
    }
}
